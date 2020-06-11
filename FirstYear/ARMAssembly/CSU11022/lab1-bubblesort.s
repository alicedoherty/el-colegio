;
; CS1022 Introduction to Computing II 2019/2020
; Lab 1B - Bubble Sort
;

N	EQU	10

	AREA	globals, DATA, READWRITE

; N word-size values

SORTED	SPACE	N*4		; N words (4 bytes each)


	AREA	RESET, CODE, READONLY
	ENTRY


	;
	; copy the test data into RAM
	;

	LDR	R4, =SORTED
	LDR	R5, =UNSORT
	LDR	R6, =0
whInit	CMP	R6, #N
	BHS	eWhInit
	LDR	R7, [R5, R6, LSL #2]
	STR	R7, [R4, R6, LSL #2]
	ADD	R6, R6, #1
	B	whInit
eWhInit

	LDR	R4, =SORTED
	LDR	R5, =UNSORT

	;
	; your sort subroutine goes here
	;
	

dowhloop				; do {
	LDR	R1, =0			; swapped = false
	LDR	R2, =1			; for(i = 1;
forloop	
	CMP	R2, #N			;  i < N;)
	BHS	endfor			; {
	SUB	R6, R2, #1		; i - 1
	LDR 	R7, [R4, R6, LSL #2]	; contents of array[i - 1]
	LDR	R8, [R4, R2, LSL #2]	; content of array[i]
	CMP 	R7, R8			; if(array[i - 1] > array[i]
	BLS	eif			; {
	MOV	R9, R7			;  tmpswap = array[i - 1]
	STR	R8, [R4, R6, LSL #2]	;  array[i - 1] = array[i]
	STR	R9, [R4, R2, LSL #2]	;  array[i] = tmpswap
	LDR	R1, =1			;  swapped = true	
eif					; }
	ADD	R2, R2, #1		; i++
	B	forloop			; }
endfor
	CMP	R1, #1
	BEQ	dowhloop
	
STOP	B	STOP

UNSORT	DCD	9,3,0,1,6,2,4,7,8,5

	END
