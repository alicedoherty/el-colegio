;
; CSU11021 Introduction to Computing I 2019/2020
; Basic ARM Assembly Language
;

	AREA	RESET, CODE, READONLY
	ENTRY

; Write your solution for all parts (i) to (iv) below.

; Tip: Right-click on any instruction and select 'Run to cursor' to
; "fast forward" the processor to that instruction.

; (i) 3x+y

	LDR	R1, =2	; x = 2
	LDR	R2, =3	; y = 3
	
	LDR	R3, =3	; temp = 3
	MUL	R3, R1, R3	; 3x
	ADD 	R0, R3, R2	; 3x + y


; (ii) 3x^2+5x

	LDR	R1, =2	; x = 2

	LDR	R3, =3
	LDR	R4, =5
	MUL	R5, R1, R1	; x^2
	MUL	R3, R5, R3	; 3x^2
	MUL 	R4, R1, R4	; 5x
	ADD	R0, R3, R4	; 3x^2 + 5x


; (iii) 2x^2+6xy+3y^2

	LDR	R1, =2	; x = 2
	LDR	R2, =3	; y = 3

	LDR	R3, = 2
	LDR	R4, = 6
	LDR	R5, = 3
	MUL	R6, R1, R1	; x^2
	MUL	R3, R6, R3	; 2x^2
	MUL	R6, R1, R2	; xy
	MUL	R4, R6, R4	; 6xy
	MUL	R6, R2, R2	; y^2
	MUL	R5, R6, R5	; 3y^2
	ADD	R0, R3, R4	; 2x^2 + 6xy
	ADD	R0, R0, R5	; 2x^2 + 6xy + 3y^2
	


; (iv) x^3-4x^2+3x+8

	LDR	R1, =2	; x = 2
	LDR	R2, =3	; y = 3
	
	LDR	R3, = -4
	LDR	R4, = 3
	LDR	R5, = 8
	MUL 	R6, R1, R1	; x^2
	MUL	R7, R6, R1	; x^3
	MUL 	R6, R3, R6	; -4x^2
	MUL	R4, R1, R4	; 3x
	ADD	R0, R6, R7	; x^3 - 4x^2
	ADD	R0, R0, R4	; x^3 - 4x^2 + 3x
	ADD 	R0, R0, R5	; x^3 - 4x^2 + 3x + 8


STOP	B	STOP

	END
