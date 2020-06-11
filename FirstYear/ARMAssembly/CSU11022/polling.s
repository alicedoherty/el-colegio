;
; CS1022 Introduction to Computing II 2019/2020
; Polling Example
;

; Pin Control Block registers
PINSEL4		EQU	0xE002C010

; GPIO registers
FIO2DIR1	EQU	0x3FFFC041
FIO2PIN1	EQU	0x3FFFC055

; TIMER0 registers 
T0TCR		EQU	0xE0004004
T0MR0		EQU	0xE0004018
T0MCR		EQU	0xE0004014
T0TC		EQU	0xE0004008
	
TRUE		EQU	1
FALSE		EQU	0
	
MIN		EQU	5000000
MAX		EQU	8000000

	AREA	RESET, CODE, READONLY
	ENTRY
	
	LDR	R8, =TRUE	; winner = true

	; Enable P2.10 for GPIO
	LDR	R4, =PINSEL4	; load address of PINSEL4
	LDR	R5, [R4]	; read current PINSEL4 value
	BIC	R5, #(0x3 << 20); modify bits 20 and 21 to 00
	STR	R5, [R4]	; write new PINSEL4 value

	; Set P2.10 for input
	LDR	R4, =FIO2DIR1	; load address of FIO2DIR1

	NOP			; on "real" hardware, we cannot place
				; an instruction at address 0x00000014
	LDRB	R5, [R4]	; read current FIO2DIR1 value
	BIC	R5, #(0x1 << 2)	; modify bit 2 to 0 for input, leaving other bits unmodified
	STRB	R5, [R4]	; write new FIO2DIR1
	
	BL	waitBtnDn	; waits until currentState is PRESSED and lastState is NOT_PRESSED

	; Stop and reset TIMER0
	LDR	R5, =T0TCR
	LDR	R6, =0x2
	STRB	R6, [R5]
	
	; Set TOTC to zero
	LDR	R5, =T0TC
	LDR	R6, =0x0
	STR	R6, [R5]
	
	; Start TIMER0 using the Timer Control Register
	LDR	R4, =T0TCR
	LDR	R5, =0x01
	STRB	R5, [R4]
	
	BL	waitBtnDn
	
	; Stop TIMER0 using Timer Control Register
	;   Set bit 0 of TCR to 0 to stop TIMER
	LDR	R5, =T0TCR
	LDR	R6, =0x0	
	STRB	R6, [R5]
	;BIC	R5, #0x1
	;STRB	R5, [R5]
	
	
	LDR	R9, =T0TC
	LDR	R9, [R9]	; elaspsed_time = T0TC
	
	LDR	R10, =MIN
	LDR	R11, =MAX
	
	CMP	R9, R10		; if 
	BLO	ifStat		; (elaspsed_time < min_time
	CMP	R9, R11		;   || elaspsed_time > max_time)
	BLS	eIf1		; {
ifStat				; 
	LDR 	R8, =FALSE	;  winner = FALSE
eIf1				; }	
	CMP	R8, #FALSE	; if(!winner)
	BNE	eIf2
	
	; Reconfigure P2.10 for output
	LDR	R5, =FIO2DIR1	; load address of FIO2DIR1
	NOP
	LDRB	R6, [R5]	; read current FIO2DIR1 value
	ORR	R6, #(0x1 << 2)	; modify bit 2 to 1 for output, leaving other bits unmodified
	STRB	R6, [R5]	; write new FIO2DIR1
	
	; Turn on LED
	LDR	R4, =0x04		; setup bit mask for P2.10 bit in FIO2PIN1
	LDR	R5, =FIO2PIN1		;
	LDRB	R6, [R5]		; read FIO2PIN1
	ORR	R6, R6, R4		; set bit 2 (turn on LED)
	;BIC	R6, R6, R4		
	STRB	R6, [R5]
	
eIf2
	
STOP 	B	STOP

;
; waitBtnDn
; waits/blocks until button has been pressed down (edge detection)
; from Lab 4A pdf
;

waitBtnDn
	PUSH	{lr, R4-R8}
	LDR	R4, =FIO2PIN1
	
	LDR	R5, [R4]	; currentState = FIOPIN1
	
	; Loops until currentState is PRESSED and lastState is NOT_PRESSED
	
whBtnDn				; do {
	MOV	R8, R5		;  lastState = currentState
	LDRB	R5, [R4]	;  currentState = FIO2PIN1
	TST	R5, #0x04	; } while (currentState & 0x04 != 0x00
	BNE	whBtnDn		;
	TST	R8, #0x04	;          || lastState & 0x04 == 0x00)
	BEQ	whBtnDn		;
	POP	{pc, R4-R8}
	
	END