;
; CSU11021 Introduction to Computing I 2019/2020
; GCD
;

	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R2, =13		; test with a = 90
	LDR	R3, =6		; test with b = 60
	
while	CMP 	R2, R3		; while (a != b)
	BEQ	endwh		; {
	CMP	R2, R3		;  if (a > b)
	BLS	elsless		;  {
	SUB	R2, R2, R3	;   a = a - b
	B	while		;  }
elsless	SUB	R3, R3, R2	;  else
	B 	while		;  {
endwh	MOV	R0, R2		;   b = b - a
				;  }
STOP	B	STOP		; }

	END