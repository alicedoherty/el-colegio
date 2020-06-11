;
; CSU11021 Introduction to Computing I 2019/2020
; Adding the values represented by ASCII digit symbols
;

	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R1, ='2'	; Load R1 with ASCII code for symbol '2'
	LDR	R2, ='4'	; Load R2 with ASCII code for symbol '4'
	
	LDR	R3, =0x30	; Converts ASCII character to integer
	SUB	R1, R1, R3	; R1 converted to integer
	SUB	R2, R2, R3	; R2 converted to integer
	ADD	R0, R1, R2	; R1 + R2
	ADD	R0, R0, R3	; Result converted to ASCII

STOP	B	STOP

	END
