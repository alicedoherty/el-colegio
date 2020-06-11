;
; CS1022 Introduction to Computing II 2018/2019
; Lab 3 - Floating-Point
;

	AREA	RESET, CODE, READONLY
	ENTRY

;
; Test Data
;
FP_A	EQU	0x41C40000
FP_B	EQU	0x41960000

	; initialize system stack pointer (SP)
	LDR	SP, =0x40010000

	;
	; test your subroutines here
	;

	;LDR	R0, =0x3fc00000		; 1.5
	;LDR	R1, =0x3fe00000 	; 1.75
	;BL	fpadd
	
	LDR	R0, =0x3fc00000		; 1.5
	LDR	R1, =0x41280000		; 10.5
	BL	fpadd
  
stop	B	stop


;
; fpfrac
; decodes an IEEE 754 floating point value to the signed (2's complement)
; fraction
; parameters:
;	r0 - ieee 754 float
; return:
;	r0 - fraction (signed 2's complement word)
;
fpfrac
	PUSH 	{R4, lr}
	LDR	R4, =0x7FFFFF		; load mask 0x7FFFFF
	AND	R0, R0, R4		; clear bits 32-24
	POP	{R4, pc}

;
; fpexp
; decodes an IEEE 754 floating point value to the signed (2's complement)
; exponent
; parameters:
;	r0 - ieee 754 float
; return:
;	r0 - exponent (signed 2's complement word)
;
fpexp
	PUSH	{R4, lr}
	LDR	R4, =0x7F800000		; load mask 0x7F800000
	AND	R0, R0, R4		; clear bits 32 and 23-1
	MOV	R0, R0, LSR #23		; logical shift right 23 bits
	SUB	R0, R0, #127		; subtract constant bias (127) to decode
	POP	{R4, pc}

;
; fpencode
; encodes an IEEE 754 value using a specified fraction and exponent
; parameters:
;	r0 - fraction (signed 2's complement word)
;	r1 - exponent (signed 2's complement word)
; result:
;	r0 - ieee 754 float
;
fpencode
	PUSH 	{lr}
	ADD	R1, R1, #127		; add constant bias to exponent
	MOV	R1, R1, LSL #23		; logical shift left 23 bits
	ADD	R0, R0, R1		; add fraction and exponent
	POP	{pc}
;
; fpadd
; adds two IEEE-754 values and returns result as new IEEE-754 value
; parameters:
;       r0 - first IEEE-754 value
;	r1 - second IEEE-754 value
; result:
;	r0 - new IEEE-754 value
;
fpadd
	PUSH	{R4-R11, lr}
	MOV	R4, R0			; save first value in R4
	MOV	R5, R1			; save second value in R5
	
	; get fractions
	MOV	R0, R4	
	BL	fpfrac
	MOV	R8, R0			; save first fraction in R8
	MOV	R0, R5		
	BL	fpfrac
	MOV	R9, R0			; save second fraction in R9
	;ORR	R8, R8, #0x800000	; set bit 23 frac1
	;ORR	R9, R9, #0x800000	; set bit 23 frac2
	
	; check signs of values
	MOVS	R10, R4, LSL #1		; sign saves in carry flag
	BCS	negSign
	BCC	checkSecond
negSign
	NEG	R8, R8			; frac converted to negative 2s Comp
checkSecond	
	MOVS	R11, R5, LSL #1		; sign saved in carry flag
	BCS	negSign2
	BCC	endSignCheck
negSign2
	NEG	R9, R9			; frac converted to negative 2s Comp
endSignCheck	
	
	; set hidden bits
	
	ORR	R8, R8, #0x800000	; set bit 23 frac1
	ORR	R9, R9, #0x800000	; set bit 23 frac2

	; compare exponents
	MOV	R0, R4			; move first val into R0 to pass into fpexp
	BL	fpexp
	MOV	R6, R0			; save first exp in R6
	
	MOV	R0, R5			; move second val into R0 to pass into fpexp
	BL 	fpexp
	MOV	R7, R0			; save second exp in R7
	
	CMP	R6, R7			; if (exp1 < exp2)
	BLO	shiftExp1		;  shift frac1
	BHI	shiftExp2		; else if (exp2 < exp1)
	B	endExp			;  shift frac2
					; else do nothing
shiftExp1	
	CMP	R6, R7			; while(exp1 < exp2)
	BHS	endExp			; {
	MOV	R8, R8, LSR #1		;  shift right by 1 bit
	ADD	R6, R6, #1		;  exp1++
	B	shiftExp1		; }
	
shiftExp2
	CMP	R7, R6			; while(exp2 < exp1)
	BHS	endExp			; {
	MOV	R9, R9, LSR #1		;  shift right by 1 bit
	ADD	R7, R7, #1		;  exp2++
	B	shiftExp2		; }
endExp
	
	; add fractions and normalise
	ADD	R8, R8, R9		; frac1 + frac2
	;LDR	R12, =0xFF800000
	;BIC	R8, R8, R12		; clear bits 23-31

normalise
	CMP	R8, #0x1000000		; if(frac >= 0x1000000)
	BHS	shiftRight
	CMP	R8, #0x800000		; if(frac < 0x800000)
	BLO	shiftLeft
	B	endNormalise
	
	; shift right and increment exponent
shiftRight
	MOV	R8, R8, LSR #1		; shift frac to right
	ADD	R6, R6, #1		; exp++
	B	normalise
	
	; shift left and decrememnt exponent
shiftLeft
	MOV	R8, R8, LSL #1		; shift frac to left
	SUB	R6, R6, #1		; exp--
	B	normalise

endNormalise

	; reencode result
	BIC	R8, R8, #0x800000	; clear bit 23 (hidden bit)
	MOV	R0, R8			; move frac to R0
	MOV	R1, R6			; move exp to R1
	BL	fpencode
	
	POP	{R4-R11, pc}
	
	END
