;
; CS1022 Introduction to Computing II 2018/2019
; Chess Clock
;

; Solution uses code by J Dukes from material for CSU11022

T0IR		EQU	0xE0004000
T0TCR		EQU	0xE0004004
T0TC		EQU	0xE0004008
T0MR0		EQU	0xE0004018
T0MCR		EQU	0xE0004014
	
T1IR		EQU	0xE0008000	;corresponding addresses for TIMER1 added
T1TCR		EQU	0xE0008004
T1TC		EQU	0xE0008008
T1MR0		EQU	0xE0008018
T1MCR		EQU	0xE0008014

PINSEL4		EQU	0xE002C010

FIO2DIR1	EQU	0x3FFFC041
FIO2PIN1	EQU	0x3FFFC055

EXTINT		EQU	0xE01FC140
EXTMODE		EQU	0xE01FC148
EXTPOLAR	EQU	0xE01FC14C

VICIntSelect	EQU	0xFFFFF00C
VICIntEnable	EQU	0xFFFFF010
VICVectAddr0	EQU	0xFFFFF100
VICVectPri0	EQU	0xFFFFF200
VICVectAddr	EQU	0xFFFFFF00

VICVectT0	EQU	4
VICVectT1	EQU	5		; vector 5 to configure TIMER1 for VIC
VICVectEINT0	EQU	14

Irq_Stack_Size	EQU	0x80

Mode_USR        EQU     0x10
Mode_IRQ        EQU     0x12
I_Bit           EQU     0x80            ; when I bit is set, IRQ is disabled
F_Bit           EQU     0x40            ; when F bit is set, FIQ is disabled

TRUE		EQU	1
FALSE		EQU	0

	AREA	RESET, CODE, READONLY
	ENTRY

	; Exception Vectors

	B	Reset_Handler	; 0x00000000
	B	Undef_Handler	; 0x00000004
	B	SWI_Handler	; 0x00000008
	B	PAbt_Handler	; 0x0000000C
	B	DAbt_Handler	; 0x00000010
	NOP			; 0x00000014
	B	IRQ_Handler	; 0x00000018
	B	FIQ_Handler	; 0x0000001C

;
; Reset Exception Handler
;
Reset_Handler

	;
	; Initialize Stack Pointers (SP) for each mode we are using
	;

	; Stack Top
	LDR	R0, =0x40010000

	; Enter irq mode and set initial SP
	MSR     CPSR_c, #Mode_IRQ:OR:I_Bit:OR:F_Bit
	MOV     SP, R0
	SUB     R0, R0, #Irq_Stack_Size

	; Enter user mode and set initial SP
	MSR     CPSR_c, #Mode_USR
	MOV	SP, R0
	
	; Initialise variables stored in RAM
	LDR	R4, =timerLength
	LDR	R5, =10000000
	STR	R5, [R4]		; timerLength = 10,000,000 (10seconds)
	
	LDR	R4, =timer0
	LDR	R5, =TRUE
	STR	R5, [R4]		; boolean timer0 = true

	;
	; Configure TIMER0 and TIMER1
	;
	
	; Stop and reset TIMER0 and TIMER1 using Timer Control Register
	LDR	R5, =T0TCR
	LDR	R6, =0x2
	STRB	R6, [R5]
	
	LDR	R5, =T1TCR
	LDR	R6, =0x2
	STRB	R6, [R5]

	; Clear any previous TIMER0/TIMER1 interrupts
	LDR	R5, =T0IR
	LDR	R6, =0xFF
	STRB	R6, [R5]
	
	LDR	R5, =T1IR
	LDR	R6, =0xFF
	STRB	R6, [R5]

	; Set match register for timerLength secs
	LDR	R4, =T0MR0
	LDR	R5, =timerLength
	LDR	R5, [R5]
	STR	R5, [R4]
	
	LDR	R4, =T1MR0
	LDR	R5, =timerLength
	LDR	R5, [R5]
	STR	R5, [R4]

	; IRQ on match using Match Control Register
	LDR	R4, =T0MCR
	LDR	R5, =0x03
	STRH	R5, [R4]
	
	LDR	R4, =T1MCR
	LDR	R5, =0x03
	STRH	R5, [R4]
	
	;
	; Configure P2.10 for EINT0
	;

	; Enable P2.10 for EINT0
	LDR	R4, =PINSEL4
	LDR	R5, [R4]		; read current value
	BIC	R5, #(0x03 << 20)	; clear bits 21:20
	ORR	R5, #(0x01 << 20)	; set bits 21:20 to 01
	STR	R5, [R4]		; write new value

	; Set edge-sensitive mode for EINT0
	LDR	R4, =EXTMODE
	LDR	R5, [R4]		; read
	ORR	R5, #1			; modify
	STRB	R5, [R4]		; write

	; Set rising-edge mode for EINT0
	LDR	R4, =EXTPOLAR
	LDR	R5, [R4]		; read
	BIC	R5, #1			; modify
	STRB	R5, [R4]		; write

	; Reset EINT0
	LDR	R4, =EXTINT
	MOV	R5, #1
	STRB	R5, [R4]
	
	;
	; Configure VIC for TIMER0 interrupts
	;

	; Useful VIC vector numbers and masks for following code
	LDR	R3, =VICVectT0		; vector 4
	LDR	R4, =(1 << VICVectT0) 	; bit mask for vector 4

	; VICIntSelect - Clear bit 4 of VICIntSelect register to cause
	; channel 4 (TIMER0) to raise IRQs (not FIQs)
	LDR	R5, =VICIntSelect	; addr = VICVectSelect;
	LDR	R6, [R5]		; tmp = Memory.Word(addr);
	BIC	R6, R6, R4		; Clear bit for Vector 0x04
	STR	R6, [R5]		; Memory.Word(addr) = tmp;

	; Set Priority for VIC channel 4 (TIMER0) to lowest (15) by setting
	; VICVectPri4 to 15
	LDR	R5, =VICVectPri0	; addr = VICVectPri0;
	MOV	R6, #15			; pri = 15;
	STR	R6, [R5, R3, LSL #2]	; Memory.Word(addr + vector * 4) = pri;

	; Set Handler routine address for VIC channel 4 (TIMER0) to address of
	; our handler routine (TimerHandler)
	LDR	R5, =VICVectAddr0	; addr = VICVectAddr0;
	LDR	R6, =Timer_Handler0	; handler = address of TimerHandler;
	STR	R6, [R5, R3, LSL #2]	; Memory.Word(addr + vector * 4) = handler

	; Enable VIC channel 4 (TIMER0) by writing a 1 to bit 4 of VICIntEnable
	LDR	R5, =VICIntEnable	; addr = VICIntEnable;
	STR	R4, [R5]		; enable interrupts for vector 0x4

	;
	; Configure VIC for TIMER1 interrupts
	;

	; Useful VIC vector numbers and masks for following code
	LDR	R3, =VICVectT1		; vector 5
	LDR	R4, =(1 << VICVectT1) 	; bit mask for vector 5

	; VICIntSelect - Clear bit 4 of VICIntSelect register to cause
	; channel 5 (TIMER1) to raise IRQs (not FIQs)
	LDR	R5, =VICIntSelect	; addr = VICVectSelect;
	LDR	R6, [R5]		; tmp = Memory.Word(addr);
	BIC	R6, R6, R4		; Clear bit for Vector 0x04
	STR	R6, [R5]		; Memory.Word(addr) = tmp;

	; Set Priority for VIC channel 5 (TIMER1) to lowest (15)
	LDR	R5, =VICVectPri0	; addr = VICVectPri0;
	MOV	R6, #15			; pri = 15;
	STR	R6, [R5, R3, LSL #2]	; Memory.Word(addr + vector * 4) = pri;

	; Set Handler routine address for VIC channel 5 (TIMER1) to address of
	; our handler routine (TimerHandler)
	LDR	R5, =VICVectAddr0	; addr = VICVectAddr0;
	LDR	R6, =Timer_Handler1	; handler = address of TimerHandler;
	STR	R6, [R5, R3, LSL #2]	; Memory.Word(addr + vector * 4) = handler

	; Enable VIC channel 5 (TIMER1) by writing a 1 to bit 4 of VICIntEnable
	LDR	R5, =VICIntEnable	; addr = VICIntEnable;
	STR	R4, [R5]		; enable interrupts for vector 0x4

	;
	; Configure VIC for EINT0 interrupts
	;

	; Useful VIC vector numbers and masks for following code
	LDR	R4, =VICVectEINT0		; vector 14
	LDR	R5, =(1 << VICVectEINT0) 	; bit mask for vector 14

	; VICIntSelect - Clear bit 4 of VICIntSelect register to cause
	; channel 14 (EINT0) to raise IRQs (not FIQs)
	LDR	R6, =VICIntSelect	; addr = VICVectSelect;
	LDR	R7, [R6]		; tmp = Memory.Word(addr);
	BIC	R7, R7, R5		; Clear bit for Vector 14
	STR	R7, [R6]		; Memory.Word(addr) = tmp;

	; Set Priority for VIC channel 14 (EINT0) to lowest (15)
	LDR	R6, =VICVectPri0	; addr = VICVectPri0;
	MOV	R7, #15			; pri = 15;
	STR	R7, [R6, R4, LSL #2]	; Memory.Word(addr + vector * 4) = pri;

	; Set Handler routine address for VIC channel 4 (TIMER0) to address of
	; our handler routine (TimerHandler)
	LDR	R6, =VICVectAddr0	; addr = VICVectAddr0;
	LDR	R7, =Button_Handler	; handler = address of TimerHandler;
	STR	R7, [R6, R4, LSL #2]	; Memory.Word(addr + vector * 4) = handler

	; Enable VIC channel 14 (EINT0) by writing a 1 to bit 4 of VICIntEnable
	LDR	R6, =VICIntEnable	; addr = VICIntEnable;
	STR	R5, [R6]		; enable interrupts for vector 14

stop	B	stop


;
; TOP LEVEL EXCEPTION HANDLERS
;

;
; Software Interrupt Exception Handler
;
Undef_Handler
	B	Undef_Handler

;
; Software Interrupt Exception Handler
;
SWI_Handler
	B	SWI_Handler

;
; Prefetch Abort Exception Handler
;
PAbt_Handler
	B	PAbt_Handler

;
; Data Abort Exception Handler
;
DAbt_Handler
	B	DAbt_Handler

;
; Interrupt ReQuest (IRQ) Exception Handler (top level - all devices)
;
IRQ_Handler
	SUB	lr, lr, #4	; for IRQs, LR is always 4 more than the
				; real return address
	STMFD	sp!, {r0-r3,lr}	; save r0-r3 and lr

	LDR	r0, =VICVectAddr; address of VIC Vector Address memory-
				; mapped register

	MOV	lr, pc		; can’t use BL here because we are branching
	LDR	pc, [r0]	; to a different subroutine dependant on device
				; raising the IRQ - this is a manual BL !!

	LDMFD	sp!, {r0-r3, pc}^ ; restore r0-r3, lr and CPSR

;
; Fast Interrupt reQuest Exception Handler
;
FIQ_Handler
	B	FIQ_Handler

;
; EINT0 IRQ Handler (device-specific handler called by top-level IRQ_Handler)
;
Button_Handler

	STMFD	sp!, {R4-R7, lr}
	
	; Reset EINT0 interrupt by writing 1 to EXTINT register
	LDR	R4, =EXTINT
	MOV	R5, #1
	STRB	R5, [R4]
	
	; Main code that starts/stops appropriate timer
	
	LDR	R6, =timer0		; load boolean timer0
	LDR	R6, [R6]		; (initalised to true)
	
	CMP	R6, #TRUE		; if(timer0 == true)
	BNE	elseStat		; {

	LDR	R5, =T1TCR		;
	LDR	R6, =0x2		;  stop timer1
	STRB	R6, [R5]		;
	
	LDR	R4, =T0TCR		;
	LDR	R5, =0x01		;  start timer0
	STRB	R5, [R4]		; 
	
	LDR	R7, =FALSE		;  
	LDR	R6, =timer0		;
	STR	R7, [R6]		;  timer0 = false
	B	continue		; }
	
elseStat
	LDR	R5, =T0TCR		; else {
	LDR	R6, =0x2		;  stop timer0
	STRB	R6, [R5]		;
	
	LDR	R4, =T1TCR		;
	LDR	R5, =0x01		;  start timer1
	STRB	R5, [R4]		; 
	
	LDR	R7, =TRUE		;
	LDR	R6, =timer0		;
	STR	R7, [R6]		;  timer0 = true
	
continue
	; Clear source of interrupt
	LDR	R4, =VICVectAddr	; addr = VICVectAddr
	MOV	R5, #0			; tmp = 0;
	STR	R5, [R4]		; Memory.Word(addr) = tmp;
	
	LDMFD	sp!, {R4-R7, pc}
	
;
; TIMER0 IRQ Handler (device-specific handler called by top-level IRQ_Handler)
;
Timer_Handler0

	STMFD	sp!, {R4-R6, lr}

	; Reset TIMER0 interrupt by writing 0xFF to T0IR
	LDR	R4, =T0IR
	MOV	R5, #0xFF
	STRB	R5, [R4]

	;
	; If TIMER0 expires - LED in P2.10 lights
	;
	
	; Stop and reset TIMER0 using Timer Control Register
	LDR	R5, =T0TCR
	LDR	R6, =0x3
	STRB	R6, [R5]
	
	; Stop and reset TIMER1 using Timer Control Register
	LDR	R5, =T1TCR
	LDR	R6, =0x3
	STRB	R6, [R5]
	
	; Enable P2.10 for GPIO
	LDR	R5, =PINSEL4		; load address of PINSEL4
	LDR	R6, [R5]		; read current PINSEL4 value
	BIC	R6, #(0x3 << 20)	; modify bits 20 and 21 to 00
	STR	R6, [R5]		; write new PINSEL4 value
	
	; Reconfigure P2.10 for output
	LDR	R5, =FIO2DIR1		; load address of FIO2DIR1
	NOP
	LDRB	R6, [R5]		; read current FIO2DIR1 value
	ORR	R6, #(0x1 << 2)		; modify bit 2 to 1 for output, leaving other bits unmodified
	STRB	R6, [R5]		; write new FIO2DIR1
		
	; Turn on LED
	LDR	R4, =0x04		; setup bit mask for P2.10 bit in FIO2PIN1
	LDR	R5, =FIO2PIN1		;
	LDRB	R6, [R5]		; read FIO2PIN1
	ORR	R6, R6, R4		; set bit 2 (turn on LED)		
	STRB	R6, [R5]

	; Clear source of interrupt by writing 0 to VICVectAddr
	LDR	R4, =VICVectAddr
	MOV	R5, #0
	STR	R5, [R4]

	LDMFD	sp!, {R4-R6, pc}

;
; TIMER1 IRQ Handler (device-specific handler called by top-level IRQ_Handler)
;
Timer_Handler1

	STMFD	sp!, {R4-R6, lr}

	; Reset TIMER0 interrupt by writing 0xFF to T0IR
	LDR	R4, =T0IR
	MOV	R5, #0xFF
	STRB	R5, [R4]

	;
	; If TIMER1 expires - LED in P2.11 lights
	;
	
	; Stop and reset TIMER1 using Timer Control Register
	LDR	R5, =T1TCR
	LDR	R6, =0x3
	STRB	R6, [R5]
	
	; Stop and reset TIMER0 using Timer Control Register
	LDR	R5, =T0TCR
	LDR	R6, =0x3
	STRB	R6, [R5]
	
	; Enable P2.11 for GPIO
	LDR	R5, =PINSEL4		; load address of PINSEL4
	LDR	R6, [R5]		; read current PINSEL4 value
	BIC	R6, #(0x3 << 22)	; modify bits 22 and 23 to 00
	STR	R6, [R5]		; write new PINSEL4 value
	
	; Reconfigure P2.11 for output
	LDR	R5, =FIO2DIR1		; load address of FIO2DIR1
	NOP
	LDRB	R6, [R5]		; read current FIO2DIR1 value
	ORR	R6, #(0x1 << 3)		; modify bit 3 to 1 for output, leaving other bits unmodified
	STRB	R6, [R5]		; write new FIO2DIR1
		
	; Turn on LED
	LDR	R4, =0x08		; setup bit mask for P2.11 bit in FIO2PIN1
	LDR	R5, =FIO2PIN1		;
	LDRB	R6, [R5]		; read FIO2PIN1
	ORR	R6, R6, R4		; set bit 3 (turn on LED)		
	STRB	R6, [R5]

	; Clear source of interrupt by writing 0 to VICVectAddr
	LDR	R4, =VICVectAddr
	MOV	R5, #0
	STR	R5, [R4]

	LDMFD	sp!, {R4-R6, pc}


	AREA	TestData, DATA, READWRITE
		
timerLength	SPACE	4
timer0		SPACE	4

	END
