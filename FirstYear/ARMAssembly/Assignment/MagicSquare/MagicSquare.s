;
; CS1022 Introduction to Computing II 2018/2019
; Magic Square
;
TRUE	EQU	1
FALSE	EQU	0

	AREA	RESET, CODE, READONLY
	ENTRY

	; initialize system stack pointer (SP)
	LDR	SP, =0x40010000

	LDR	R0, =arr1	; test 3x3 array (magic square)
	LDR	R4, =size1
	LDR	R1, [R4]
	
	;LDR	R0, =arr2	; test 5x5 array (magic square)
	;LDR	R4, =size2
	;LDR	R1, [R4]
	
	;LDR	R0, =arr3	; test 3x3 negative array
	;LDR	R4, =size3	; (will return false)
	;LDR	R1, [R4]

	;LDR	R0, =arr4	; test 3x3 array (not magic square)
	;LDR	R4, =size4
	;LDR	R1, [R4]
	
	BL	isPositive	; if(isPositive)
				;  isMagic(arrayAddress, arraySize)
	CMP	R0, #TRUE	; else
	BNE	endProgram	;  return false (end program)
	
	LDR	R0, =arr1
	LDR	R4, =size1
	LDR	R1, [R4]
		
	;LDR	R0, =arr2
	;LDR	R4, =size2
	;LDR	R1, [R4]
	
	;LDR	R0, =arr3
	;LDR	R4, =size3
	;LDR	R1, [R4]

	;LDR	R0, =arr4
	;LDR	R4, =size4
	;LDR	R1, [R4]
	
	BL	isMagic
	
endProgram

stop	B	stop

;
; isPositive
; determines whether all the elements in an array are positive
; parameters:
;	r0 - arrayAddress
;	r1 = arraySize (N)
; return:
;	r0 - true/false
;

isPositive
	PUSH	{R4-R8, lr}
	MOV	R4, R0			; copy arrayAddress to R4
	MOV	R7, R1			; copy arraySize to R7 
	MUL	R7, R1, R7		; numOfElements = arraySize * arraySize
	LDR	R8, =0			; count = 0
loop	
	CMP	R8, R7			; while(count < numOfElements)
	BHS	endLoop			; {
	LDR	R5, [R4]		;  elem = contentsOf[arrayAddress]
	LDR	R6, =0x80000000		;  if(elemMSB == 1) (i.e if element is negative)
	TST	R5, R6  		;   return false
	BNE	false			;  else 
	ADD	R4, R4, #4		;   arrayAddress + 4
	ADD	R8, R8, #1		;   count++
	B	loop			; } 
endLoop					;
	LDR	R0, =TRUE		; 
	B	retResult		;  return true

false
	LDR	R0, =FALSE
	
retResult
	POP	{R4-R8, pc}

;
; isMagic
; determines whether a square two-dimensional array is a Magic Square
; parameters:
;	r0 - arrayAddress
;	r1 - arraySize (N)
; return:
;	r0 - true/false
;

isMagic
	PUSH	{R4-R9, lr}
	
	;check diagonal 1
	LDR	R7, =0			; total = 0
	LDR 	R4, =0			; i = 0 
whLoop1	
	CMP	R4, R1			; while(i < N)
	BHS	endWh1			; {
	
	;load contents of array[i][i]
	MUL	R6, R4, R1		;  index = row * rowSize
	ADD	R6, R6, R4		;  index += column
	LDR	R6, [R0, R6, LSL #2]	;  load contents of array
	
	ADD	R7, R7, R6		;  total += array[i][i]
	ADD	R4, R4, #1		;  i++
	B	whLoop1			; }
endWh1

	;check diagonal 2
	LDR	R9, =0			; total2 = 0
	LDR	R4, =0			; i = 0
whLoop2 
	CMP	R4, R1			; while (i < N) 
	BHS	endWh2			; {
	
	;load contents of array[i][N-1-i]
	SUB	R8, R1, #1		;  column = N - 1
	SUB	R8, R8, R4		;  column -= i
	MUL	R6, R4, R1		;  index = row * rowSize
	ADD	R6, R6, R8		;  index += column
	LDR	R6, [R0, R6, LSL #2]	;  load contents of array
	
	ADD	R9, R9, R6		;  total2 += array[i][N-1-i]
	ADD	R4, R4, #1		;  i++
	B	whLoop2			;  }
endWh2

	;compare diagonals
	CMP	R7, R9			; if(total != total2)
	BNE	returnFalse		;  return false
	
	;compare rows
	LDR	R4, =0			; i = 0
whLoop3	
	CMP	R4, R1			; while(i < N)
	BHS	endWh3			; {
	LDR	R9, =0			;  total2 = 0
	
	LDR	R5, =0			;  j = 0
smlLoop1
	CMP	R5, R1			;  while(j < N)
	BHS	endSml			;  {
	
	;load contents of array[i][j]
	MUL	R6, R4, R1		;   index = row * rowSize
	ADD	R6, R6, R5		;   index += column
	LDR	R6, [R0, R6, LSL #2]	;   load contents of array
	
	ADD	R9, R9, R6		;   total2 += array[i][j]
	ADD	R5, R5, #1		;   j++
	B 	smlLoop1		;  }
endSml
	
	;compare row to total (diagonal 1)
	CMP	R7, R9			;  if(total != total2)
	BNE	returnFalse		;   return false
	
	ADD	R4, R4, #1		;  i++
	B	whLoop3			; }
endWh3

	;compare columns
	LDR	R4, =0			; i = 0
whLoop4	
	CMP	R4, R1			; while(i < N)
	BHS	endWh4			; {
	LDR	R9, =0			;  total2 = 0
	
	LDR	R5, =0			;  j = 0
smlLoop2
	CMP	R5, R1			;  while(j < N)
	BHS	endSml2			;  {
	
	;load contents of array[j][i]
	MUL	R6, R5, R1		;   index = row * rowSize
	ADD	R6, R6, R4		;   index += column
	LDR	R6, [R0, R6, LSL #2]	;   load contents of array
	
	ADD	R9, R9, R6		;   total2 += array[j][i]
	ADD	R5, R5, #1		;   j++
	B 	smlLoop2		;  }
endSml2
	
	;compare column to total (diagonal 1)
	CMP	R7, R9			;  if(total != total2)
	BNE	returnFalse		;   return false
	
	ADD	R4, R4, #1		;  i++
	B	whLoop4			; }
endWh4

	LDR	R0, =TRUE
	B 	return

returnFalse
	LDR	R0, =FALSE

return
	POP	{R4-R9, pc}

size1	DCD	3		; a 3x3 array
arr1	DCD	2,7,6		; the array
	DCD	9,5,1
	DCD	4,3,8

size2 	DCD	5		; a 5x5 array
arr2	DCD	17,24,1,8,15 	; the array
	DCD	23,5,7,14,16
	DCD	4,6,13,20,22
	DCD	10,12,19,21,3
	DCD	11,18,25,2,9
		
size3	DCD	3		; a 3x3 array with negative values
arr3	DCD	-3,-6,3		; the array
	DCD	4,-2,-8		
	DCD	-7,2,-1
		
size4	DCD	3		; a 3x3 array that is NOT a magic square
arr4	DCD	1,7,6		; the array
	DCD	9,5,1
	DCD	4,3,8

	END
