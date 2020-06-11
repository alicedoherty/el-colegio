;
; CSU11021 Introduction to Computing I 2019/2020
; Flow Control
;

	AREA 	RESET, CODE, READONLY
	ENTRY

; (i)
; if (h >= 13) {
; 	h = h - 12;
; }

	LDR	R0, =4		; test with h = 4
	
	CMP	R0, #13		; if (h >= 13)
	BLO	endh		; {
	SUB	R0, R0, #12	;  h = h -12
endh			; }
	
; (ii)
; if (a > b) {
;	i = i + 1;
; } else {
;	i = i - 1;
; }

	LDR	R0, =5		; test with a = 5
	LDR	R1, =2		; test with b = 2
	LDR	R2, =3		; test with i = 3
	
	CMP	R0, R1		; if (a > b)
	BLS	bgreater	; {
	ADD	R2, R2, #1	;  i = i + 1
	B	endab		; }
bgreater			; else {
	SUB	R2, R2, #1	;  i = i -1
endab				; }

; (iii)
; if (v < 10) {
; 	a = 1;
; }
; else if (v < 100) {
; 	a = 10;
; }
; else if (v < 1000) {
; 	a = 100;
; }
; else {
; 	a = 0;
; }

	LDR	R0, =1		; test with a = 1
	LDR	R1, =101	; test with v = 101
	
	CMP	R1, #10		; if (v < 10)
	BHS	elseif		; {
	LDR	R0, =1		;  a = 1
	B	endav		; }
elseif	
	CMP	R1, #100	; else if (v < 100)
	BHS	elseif2		; {
	LDR	R0, =10		;  a = 10
	B	endav		; }
elseif2	
	CMP	R1, #1000	; else if (v < 1000)
	BHS	elsefin		; {
	LDR	R0, =100	;  a = 100
	B	endav		; }
elsefin				; else {
	LDR	R0, =0		;  a = 0 
endav				; {
	
; (iv)
; i = 3;
; while (i < 1000) {
; 	a = a + i;
; 	i = i + 3;
; }

	LDR	R0, =1		; test with a = 1
	
	LDR	R1, =3		; i = 3
whloop	CMP	R1, #1000	; while (i < 1000)
	BHS	endwh		; {
	ADD	R0, R0, R1	;  a = a + i
	ADD	R1, R1, #3	;  i = i + 3
	B	whloop		; }
endwh	

; (v) 
; for (int i = 3; i < 1000; i = i + 3) {
; 	a = a + i;
; }

	LDR	R0, =1		; test with a = 1
	
	LDR 	R1, =3		; for (int i = 3;
forloop	CMP	R1, #1000	; i < 1000;)
	BHS	endfor		; {
	ADD	R0, R0, R1	;  a = a + i
	ADD	R1, R1, #3	;  i = i + 3
	B	forloop		; }
endfor

; (vi)
; p = 1;
; do {
; 	p = p * 10;
; } while (v < p);

	LDR	R0, =1		; test with v = 1
	
	LDR	R1, =1		; p = 1
	
; (vii)
; if (ch >= 'A' && ch <= 'Z') {
; 	upper = upper + 1;
; }

	LDR	R0, ='E'	; test with ch = 'E'
	LDR	R1, =1		; test with upper = 1
	
	CMP	R0, #'A'	; if (ch >= 'A'
	BLO	exitif		; &&
	CMP	R0, #'Z'	; ch <= 'Z')
	BHI	exitif		; {
	ADD	R1, R1, #1	;  upper = upper + 1	; }
exitif				; }
	

; (viii)
; if (ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u')
; {
; 	v = v + 1;
; }

	LDR	R0, ='a'	; test with ch = 'a'
	LDR	R1, =2		; test with v = 2
	
	CMP	R0, #'a'	; if (ch == 'a'
	BEQ	then		; ||
	CMP	R0, #'e'	; ch == 'e'
	BEQ	then		; ||
	CMP	R0, #'i'	; ch == 'i'
	BEQ	then		; ||
	CMP	R0, #'o'	; ch == 'o'
	BEQ	then		; ||
	CMP	R0, #'u'	; ch == 'u')
	BNE	nonetru		; {
then	ADD	R1, R1, #1	;  v = v + 1
nonetru				; }

STOP	B	STOP

	END
