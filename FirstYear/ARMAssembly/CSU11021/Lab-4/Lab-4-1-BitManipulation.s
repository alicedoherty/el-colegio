;
; CSU11021 Introduction to Computing I 2019/2020
; Bit Manipulation
;

	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R0, =137	; a = 137
	LDR	R1, =6		; b = 6

	LDR	R2, =0		; quotient = 0
	LDR	R3, =0		; remainder = 0
	LDR	R4, =0x80000000	; mask = 0x80000000
	
whloop	CMP	R4, #0
	BEQ	endwh
	MOV	R3, R3, LSL #1
	
	AND	R5, R0, R4	; a & mask
	CMP	R5, #0
	BEQ	endif1
	ORR	R3, R3, #1
endif1
	CMP	R3, R1
	BLO	endif2
	SUB	R3, R3, R1
	ORR	R2, R2, R4
endif2
	MOV	R4, R4, LSR #1
	B	whloop
endwh

STOP	B	STOP

	END
