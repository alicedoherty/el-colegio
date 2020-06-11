;
; CS1022 Introduction to Computing II 2018/2019
; Lab 2 - Subarray
;

N	EQU	7
M	EQU	3		

	AREA	RESET, CODE, READONLY
	ENTRY

	; initialize system stack pointer (SP)
	LDR	SP, =0x40010000

	;
	; Write your program here to determine whether SMALL_A
	;   is a subarray of LARGE_A
	;
	; Store 1 in R0 if SMALL_A is a subarray and zero otherwise
	;

	LDR	R0, =LARGE_A
	LDR 	R1, =SMALL_A
	
	LDR	R4, =N		; row/columnSizeA = 7
	LDR	R5, =M		; row/columnSizeB = 3
	SUB	R11, R4, R5	; #N - #M
	ADD	R11, R11, #1

	LDR	R2, =0 		; row = 0
	LDR	R12, =0		; subarray = false
	LDR	R7, [R1]	; load first value from sml array
	
rowWhLoop
	CMP	R2, #N		; while (row < rowSize)
	BHS	endRowWh	; {
	LDR	R3, =0		; column = 0
colWhLoop
	CMP	R3, R11;#N 		;  while (column < colSize)
	BHS	endColWh	;  {
	
	;load contents of ARRAY A
	MUL	R6, R2, R4		; index = row * rowSize
	ADD	R6, R6, R3		; index = index + column
	LDR	R6, [R0, R6, LSL #2]	; load contents of array[row][column]
	
	;compare A and B
	;CMP	R2, #5
	;BHS	skipOver
	
	CMP	R6, R7
	BNE	continue
	BL 	arrayCheck
	
;skipOver
continue
	;if not equal move onto next element of ARRAY A
	ADD	R3, R3, #1		; column++
	B	colWhLoop
endColWh
	ADD	R2, R2, #1		; row++
	B	rowWhLoop
endRowWh

	MOV	R0, R12			; loads whether subarray is true/false into R2
	
STOP	B	STOP

; arrayCheck subroutine
; Goes through all elements in SMALL_A and checks if it's a 
; subarray of LARGE_A
; parameters
; 	R0 = LARGE_A base address
; 	R1 = SMALL_A base address
; 	R2 = LARGE_A current row
; 	R3 = LARGE_A current column
; return
; 	R12 = true (1) or R12 = false (0)


arrayCheck
	PUSH 	{R0-R11, lr}
	LDR	R4, =N
	LDR	R5, =M
	
	LDR	R11, =4		
	
	LDR	R6, =0 		; rowCount = 0
	LDR	R7, =1		; colCount = 1

	
whRow	
	CMP	R6, R5		; while(rowCount < smlArrSz)
	BHS	endWhRow	; {
whCol	
	CMP	R7, R5		;  while(colCount < smlArrSz)
	BHS	endWhCol	;  {
	
	ADD	R3, R3, #1		; column++
	MUL	R8, R2, R4		; index = row * rowSize
	ADD	R8, R8, R3		; index = index + column
	LDR	R8, [R0, R8, LSL #2]	; load contents of large array[row][column]
	
	ADD	R1, #4			; SMALL_Aaddress++
	LDR	R9, [R1]		; load contents of small array
	
	CMP	R8, R9
	BNE	endCmp
	ADD	R7, R7, #1		; colCount++
	
	B 	whCol		;  }
endWhCol

	LDR	R7, =0			; colCount = 0 (reset)
	ADD	R6, R6, #1		; rowCount++
	ADD 	R2, R2, #1		; row++
	SUB	R3, R3, R5		; col = col - #M

	B 	whRow		; }
endWhRow
	LDR	R12, =1		;	subarray = true
endCmp
	POP 	{R0-R11, pc}
;
; test data
;

LARGE_A	DCD	 48, 37, 15, 44,  3, 17, 26
	DCD	  2,  9, 12, 18, 14, 33, 16
	DCD	 13, 20,  1, 22,  7, 48, 21
	DCD	 27, 19, 44, 49, 44, 18, 10
	DCD	 29, 17, 22,  4, 46, 43, 41
	DCD	 37, 35, 38, 34, 16, 25,  0
	DCD	 17,  0, 48, 15, 27, 35, 11

SMALL_A	DCD	 48, 21, 27
	DCD	 18, 10, 29
	DCD	 43, 41, 37

	END
