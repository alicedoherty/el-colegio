;
; CSU11021 Introduction to Computing I 2019/2020
; String Copy
;

	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R0, =tststr	; address of existing string
	LDR	R1, =0x40000000	; address for new string

whloop	LDRB	R2, [R0]
	CMP	R0, #0
	BEQ	endwh
	STRB	R2, [R1]
	ADD	R1, R1, #1
	ADD	R0, R0, #1
	B	whloop
endwh

STOP	B	STOP

tststr	DCB	"This is a test!",0

	END
