;
; CSU11021 Introduction to Computing I 2019/2020
; Proper Case
;

	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R0, =tststr	; address of string
	LDR	R1, =0x40000000	; address for new string

	MOV R2, #0		;assign starting value to null
whileloop
	CMP R2, #32		;check if space
	BEQ lastIsSpace
	CMP R2, #0		;check if null
	BEQ lastIsSpace
	B lastIsLetter
	
;Case 1: last character is a space or null.
lastIsSpace
	LDRB R2, [R0] 	;load new character
	CMP R2, #90		;check if uppercase
	BLE noChange	;return, nothing needs to be changed if uppercase.
	B toCapitalise

toCapitalise
	SUB R2, #32		;convert to capital if lowercase.
	STRB R2, [R1]	;store value in new address
	ADD R0, R0, #1	;advance string
	ADD R1, R1, #1	;advance new address
	B whileloop
		
noChange
	STRB R2, [R1]	;store value in new address
	ADD R0, R0, #1	;advance string
	ADD R1, R1, #1	;advance new address
	B whileloop
	
lastIsLetter
	CMP R2, #90
	BLE lastIsCapital
	B lastIsLower
	
	
;Case 2: last character is a capital. Convert to lowercase.
lastIsCapital
	LDRB R2, [R0]
	CMP R2, #0
	BEQ noChange
	CMP R2, #32
	BEQ noChange
	CMP R2, #90
	BLE toLowercase
	B noChange
	
toLowercase
	ADD R2, #32		;convert to lowercase
	STRB R2, [R1]	;store new lowercase letter in new address
	ADD R0, R0, #1	;advance string
	ADD R1, R1, #1	;advance new address
	B whileloop
	
;Case 3: last character is a lowercase. Convert to lowercase.
lastIsLower
	LDRB R2, [R0]
	CMP R2, #0
	BEQ noChange
	CMP R2, #32
	BEQ noChange
	CMP R2, #97
	BLT	toLowercase
	B noChange

STOP	B	STOP

tststr	DCB	"HELLO world",0

	END