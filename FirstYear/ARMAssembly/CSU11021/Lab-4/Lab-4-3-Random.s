;
; CSU11021 Introduction to Computing I 2019/2020
; Pseudo-random number generator
;

	;public long randomLong() {
	; x ^= (x << 21);
	; x ^= (x >> 32);
	; x ^= (x << 4);
	; return x
	; }	

	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R0, =0x40000000	; start address for pseudo-random sequence
	LDR	R1, =64		; number of pseudo-random values to generate

	LDR	R2, =0x12345678	; x = 0x12345678 (seed)
	LDR	R4, =0		; count = 0
	
whloop	CMP	R4, R1		; while (count < number of values to generate)
	BHS	exitloop	; {
	MOV	R3, R2, LSL #21	; 
	EOR	R2, R2, R3	; x ^= (x << 21)
	MOVS	R3, R2, LSR #32	; 
	EOR	R2, R2, R3	; x ^= (x >> 32)
	MOV	R3, R2, LSL #4	;
	EOR	R2, R2, R3	; x ^= (x << 4)
	STRB	R2, [R0]	;
	ADD	R0, R0, #1	; [address]++
	ADD	R2, R2, #1	; x++
	ADD	R4, R4, #1	; count++
	B	whloop		; }
exitloop

STOP	B	STOP

	END
