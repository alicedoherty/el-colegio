;
; CS1022 Introduction to Computing II 2018/2019
; Lab 1 - Array Move
;

N	EQU	16		; number of elements

	AREA	globals, DATA, READWRITE

; N word-size values

ARRAY	SPACE	N*4		; N words


	AREA	RESET, CODE, READONLY
	ENTRY

	; for convenience, let's initialise test array [0, 1, 2, ... , N-1]

	LDR	R0, =ARRAY
	LDR	R1, =0
L1	CMP	R1, #N
	BHS	L2
	STR	R1, [R0, R1, LSL #2]
	ADD	R1, R1, #1
	B	L1
L2

	; initialise registers for your program

	LDR	R0, =ARRAY	; start address
	LDR	R1, =6		; oldIndex
	LDR	R2, =3		; destIndex
	LDR	R3, =N		; number of elements


	; add going opposite direction
	; your program goes here
	
	LDR 	R4, [R0, R1, LSL #2]	; element = array[oldIndex]
	MOV	R5, R1			; count = oldIndex
whloop	CMP	R5, R2			; while (count > destIndex)
	BLO	endwh			; {
	SUB	R5, R5, #1		;  count--
	LDR	R6, [R0, R5, LSL #2]	;  temp = array[count]
	STR	R6, [R0, R1, LSL #2]	;  array[oldIndex] = temp
	SUB	R1, R1, #1		;  oldIndex--
	B	whloop			; }
endwh
	STR	R4, [R0, R2, LSL #2]	; array[destIndex] = element	

STOP	B	STOP

	END
