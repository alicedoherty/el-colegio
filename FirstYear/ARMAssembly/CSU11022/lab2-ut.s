;
; CS1022 Introduction to Computing II 2018/2019
; Lab 2 - Upper Triangular
;

N	EQU	4		

	AREA	RESET, CODE, READONLY
	ENTRY

	; initialize system stack pointer (SP)
	LDR	SP, =0x40010000

	LDR	R0, =ARR_A	; load initial array address
	LDR	R1, =4		; row/column size = 4 (also used as size of element)
	
	LDR	R2, =0		; column = 0
	LDR	R3, =0		; row = 0
	
whLoop	CMP	R2, R1		; while(column < columnSize)
	BHS	endWh		; {
	ADD	R3, R2, #1	;  row = column + 1

lilWh	CMP	R3, R1		;  while(row < rowSize)
	BHS	endLil		;  {
	
	;loads content of [row][column] to R4
	MUL	R4, R3, R1	;  row x rowSize
	ADD	R4, R4, R2	;  index = (row x rowSize) + column
	MUL	R4, R1, R4	;  offset = index * 4
	ADD	R4, R0, R4	;  address[row][column] = ARR_A + offset
	LDR	R4, [R4]	;  load contents of address (array)
	
	;check if content of [row][column] is 0
	CMP	R4, #0
	BNE	false
	ADD	R3, R3, #1	;  row++
	
	B	lilWh		;  }
endLil
	ADD	R2, R2, #1	; column++
	B	whLoop		; }
endWh

	;Upper Triangular = true
	MOV	R0, #1
	B	endProg
	
false	;Upper Triangular = false
	MOV	R0, #0

endProg

STOP	B	STOP


;
; test data
;

ARR_A	DCD	 1,  2,  3,  4
	DCD	 0,  6,  7,  8
	DCD	 0,  0, 11, 12
	DCD	 0,  0,  0, 16

	END
