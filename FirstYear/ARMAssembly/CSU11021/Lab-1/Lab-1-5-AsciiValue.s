;
; CSU11021 Introduction to Computing I 2019/2020
; Convert a sequence of ASCII digits to the value they represent
;

	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R1, ='2'	; Load R1 with ASCII code for symbol '2'
	LDR	R2, ='0'	; Load R2 with ASCII code for symbol '0'
	LDR	R3, ='3'	; Load R3 with ASCII code for symbol '3'
	LDR	R4, ='4'	; Load R4 with ASCII code for symbol '4'

	LDR	R5, =0x30	; Coverts ASCII character to integer
	LDR	R6, =10	
	LDR	R7, =100
	LDR	R8, =1000
	SUB	R1, R1, R5	; Converts R1 to integer
	SUB 	R2, R2, R5	; Converts R2 to integer
	SUB	R3, R3, R5	; Converts R3 to integer
	SUB	R4, R4, R5	; Converts R4 to integer
	MUL	R1, R8, R1	; R1 x 1000	
	MUL	R2, R7, R2	; R2 x 100
	MUL	R3, R6, R3	; R3 x 10
	ADD	R0, R1, R2	; 2000 + 0
	ADD	R0, R0, R3	; 2000 + 30
	ADD	R0, R0, R4	; 2030 + 4

STOP	B	STOP


	END
