;
; CSU11021 Introduction to Computing I 2019/2020
; Anagrams
;

	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R0, =tststr1	; first string
	LDR	R1, =tststr2	; second string
	
	MOV 	R8, R0		; load ch1[address] into new register to manipulate
	MOV	R9, R1		; load ch2[address] into new register to manipulate

	LDR	R4, =0		; int count1 = 0
whloop1	LDRB	R2, [R8]	; ch1 = ch1[address]
	CMP	R2, #0		; while (ch1 != 0)
	BEQ	endCount1	; {
	ADD	R8, R8, #1	;  ch1[address]++
	ADD	R4, R4, #1	;  count1++
	B	whloop1		; }
endCount1			;

	LDR	R5, =0		; int count2 = 0
whloop2	LDRB	R3, [R9]	; ch2 = ch2[address]
	CMP	R3, #0		; if (ch2 != 0)
	BEQ	endCount2	; {
	ADD	R9, R9, #1	;  ch2[address]++
	ADD	R5, R5, #1 	;  count2++
	B	whloop2		; }
endCount2			

	MOV 	R10, #0x40000000
	LDR	R11, =0x40
	LDR	R12, =0
loadwh	
	CMP	R12, R5
	BHS	endloop
	LDRB	R3, [R9]
	STRB	R3, [R10]
	ADD	R9, R9, #1
	ADD	R10, R10, #1
	ADD	R12, R12, #1
	B	loadwh
endloop

	CMP	R4, R5		; if (count1 != count2)
	BNE	notAnagram	;  anagram = false
	
	LDR	R6, =0		; int letterCount = 0
	MOV 	R8, R0		; reload ch1[address] into new register to manipulate
	MOV	R10, R1		; reload ch2[address] into new register to manipulate
	
mainWhile				
	LDRB	R2, [R8]	; ch1 = ch1[address]
	LDRB	R3, [R10]	; ch2 = ch2[address]
	
	CMP	R6, R4		; while (letterCount < count1)
	BHS	endwhloop	; {
	EOR	R7, R2, R3	;  value = ch1 ^ ch2
	ADD	R10, R10, #1	;  ch2[address]++
				
	CMP	R7, #0		;  if (value == 0)
	BEQ	ifEqualLetter	;   B out of loop
	CMP	R3, #0		;  if (ch2 == null)
	BEQ	endwhloop	;   B out of loop
	B	mainWhile	; }		

endwhloop			
	CMP	R7, #0		;  if (value != 0)
	BNE	notAnagram	;   anagram = false
				;  else
	B	anagram		;   anagram = true
	
ifEqualLetter			; if (value == 0) {
	ADD	R8, R8, #1	;  ch1[address]++
	ADD	R6, R6, #1	;  letterCount++
	STRB	R11, [R10]
	MOV	R10, R1		;  ch2[address] reset to original
	LDR	R7, =0		;  anagram = true
	B	mainWhile	;  B to mainWhile loop
				; }
notAnagram			 
	MOV	R0, #0		; anagram = false
	B	endAnagram	;
	
anagram	
	MOV	R0, #1		; anagram = true
	B	endAnagram	;

endAnagram	

STOP	B	STOP

tststr1	DCB	"tapas",0
tststr2	DCB	"pasta",0

	END
