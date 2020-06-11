;
; CSU11021 Introduction to Computing I 2019/2020
; String Reverse
;

	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R0, =tststr	; address of existing string
	LDR	R1, =0x40000000	; address for new string

whcount	LDRB	R2, [R0]
	CMP	R0, #0
	BEQ	endwh
	

STOP	B	STOP

tststr	DCB	"This is a test!",0

	END
