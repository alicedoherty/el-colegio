;
; CS1022 Introduction to Computing II 2018/2019
; Lab 2 - Matrix Multiplication
;

N	EQU	4		

	AREA	globals, DATA, READWRITE

; result array
ARR_R	SPACE	N*N*4		; N*N words (4 bytes each)


	AREA	RESET, CODE, READONLY
	ENTRY

	; initialize system stack pointer (SP)
	LDR	SP, =0x40010000

	LDR	R4, =ARR_A
	LDR	R5, =ARR_B
	LDR	R9, =ARR_R

	LDR	R6, =4 		; row size
	
	LDR	R0, =0		; for(i = 0; i < N)
for1	CMP	R0, #N		
	BHS	endFor1		
	
	LDR	R1, =0		;  for(j = 0;  j < N)
for2	CMP	R1, #N		  
	BHS	endFor2		
	LDR	R2, =0		;   r = 0
	
	LDR	R3, =0		;   for(k = 0;  k < N)
for3	CMP	R3, #N		   
	BHS	endFor3		


	
	;get A[i, k]		
	MUL	R7, R0, R6	; (i * 4)
	ADD	R7, R7, R3	; index = (i * 4) + k
	MUL	R7, R6, R7	; byteOffset = index * 4
	ADD	R7, R4, R7	; address = ARR_A + byteOffset
	LDR	R7, [R7]	; load contents of array at address
	
	;get B[k, j]
	MUL	R8, R3, R6	; (k * 4)
	ADD	R8, R8, R1	; index = (k * 4) + j
	MUL	R8, R6, R8	; byteOffset = index * 4
	ADD	R8, R5, R8	; address = ARR_B + byteOffset
	LDR	R8, [R8]	; load contents of array at address
	
	;A[i, k] * B[k, j]
	MUL	R7, R8, R7	
	
	;r = r + (A * B)
	ADD	R2, R2, R7
	
	ADD	R3, R3, #1	;    k++ 
	B	for3
endFor3	
	
	; R[i, j] = r;
	MUL	R7, R0, R6	; (i x 4)
	ADD	R7, R7, R1	; index = (i x 4) + j

	STR	R2, [R9, R7, LSL #2] ;storing r into ARR_R
	
	ADD 	R1, R1, #1	;   j++
	B	for2
endFor2
	ADD	R0, R0, #1	; i++
	B	for1
endFor1


STOP	B	STOP


;
; test data
;

ARR_A	DCD	 1,  2,  3,  4
	DCD	 5,  6,  7,  8
	DCD	 9, 10, 11, 12
	DCD	13, 14, 15, 16

ARR_B	DCD	 1,  2,  3,  4
	DCD	 5,  6,  7,  8
	DCD	 9, 10, 11, 12
	DCD	13, 14, 15, 16

	END
