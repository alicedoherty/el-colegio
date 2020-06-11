;
; CSU11021 Introduction to Computing I 2019/2020
; Division (inefficient!)
;

	AREA	RESET, CODE, READONLY
	ENTRY
	
	LDR	R2, =433	; test with a = 53
	LDR	R3, =22		; test with b = 0
	
	LDR	R0, =0		; quotient = 0
	CMP	R3, #0		; if (b = 0) {
	BEQ	zero		;  quotient = 0 }
loop	CMP	R2, R3		; while (a > b)
	BLO	enddiv		; {
	SUB	R2, R2, R3	;  a = a - b
	ADD	R0, R0, #1	;  quotient = quotient++
	B	loop		; }
zero	
	MOV	R0, #0		
enddiv		
	MOV	R1, R2		; R1 = remainder

STOP	B	STOP

	END
