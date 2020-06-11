;
; CSU11021 Introduction to Computing I 2019/2020
; Intersection
;

	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R0, =0x40000000	; address of sizeC
	LDR	R1, =0x40000004	; address of elemsC
	
	LDR	R6, =sizeA	; address of sizeA
	LDR	R2, [R6]	; load sizeA
	LDR	R3, =elemsA	; address of elemsA
	
	LDR	R6, =sizeB	; address of sizeB
	LDR	R4, [R6]	; load sizeB
	LDR	R5, =elemsB	; address of elemsB

	;
	; Your program to compute the interaction of A and B goes here
	;
	; Store the size of the intersection in memory at the address in R0
	;
	; Store the elements in the intersection in memory beginning at the
	;   address in R1
	;

	LDR 	R7, [R5] 	; load elemsB
	LDR 	R8, =0 		; count = 0
	LDR	R10, =0 	; innerCount = 0
	LDR	R11, =0 	; sizeC = 0
	LDR 	R9, [R3] 	; load elemsA

whileA 	CMP 	R8, R2 		; while ( count <= sizeA )
	BHS 	eWhileA 	; {

whileB 	CMP 	R10, R4 	;  while ( innerCount <= sizeB )
	BHI 	eWhileB 	;  {
	CMP 	R9, R7 		;   if ( elemsA = elemsB )
	BNE 	elseA 		;   {
	ADD 	R11, R11, #1 	;    sizeC = sizeC + 1
	STR 	R9, [R1] 	;    load elemsC
	ADD 	R1, R1, #4 	;    next elemsC address
elseA 				;   }
	ADD 	R5, R5, #4 	;   next elemsB address
	LDR 	R7, [R5] 	;   load next elemsB
	ADD 	R10, R10, #1 	;   innerCount = innerCount + 1
	B 	whileB 		;  }
eWhileB
	ADD 	R3, R3, #4 	;  next elemsA address
	LDR 	R9, [R3] 	;  load next elemsA
	ADD 	R8, R8, #1 	;  count = count + 1
	LDR 	R10, =0 	;  innerCount = 0
	LDR 	R5, =elemsB 	;  address of elemsB
	LDR 	R7, [R5] 	; load elemsB
	B 	whileA 		; }
eWhileA
	STR 	R11, [R0] 	; load sizeC

STOP	B	STOP

sizeA	DCD	4
elemsA	DCD	7, 14, 6, 3

sizeB	DCD	9
elemsB	DCD	20, 11, 14, 5, 7, 2, 9, 12, 17

	END

