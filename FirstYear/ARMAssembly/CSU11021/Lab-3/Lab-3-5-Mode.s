;
; CSU11021 Introduction to Computing I 2019/2020
; Mode
;

	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R4, =tstN	; load address of tstN
	LDR	R1, [R4]	; load value of tstN
	LDR	R2, =tstvals	; load address of numbers
	
	
	;INITIALIZATION
	LDR R10, =0			;selected number
	LDR R12, =0			;occurance counter
	LDR R11, =0			;iteration counter
	LDR R0, =0			;current mode [digit]
	LDR R9, =0			;current mode [occurances]
	LDR R8, =0			;nth term in the list
	
	;the following loop selects a number, then compares it
	;to the numbers in the list. If one+ matches, they are
	;added to the 'occurance' counter.
	
numberTester
	CMP R11, R1			;checks if loop has iterated past N
	BEQ exitloop
	
	LDR R5, [R2]		;load first term in sequence
	CMP R5, R10			;compare first term to number selected.
	BNE noCount			;doesn't add to occurance if not equal.
	ADD R12, R12, #1	;add to occurance counter.
noCount
	ADD R11, R11, #1		;add to iteration counter
	ADD R2, R2, #4		;advance address
	B numberTester		;return to start of loop
exitloop

	;the following instructions occur when the loop has iterated
	;through all numbers in the list. Memory address is reset along
	;with the various counters. The program compares the occurance
	;of the last loop with the current highest occurance - if it is
	;greater, it replaces the highest occurance [R0]
	
	LDR R2, =tstvals	;reset address of memory
	CMP R12, R9			;compare occurances with current mode [occurance].
	BLE lowerThanCurrent
	MOV R0, R10			;store current mode [digit] in R0.
	MOV R9, R12			;store current mode [occurance] in R9.
	
	ADD R10, R10, #1	;advance number to next in list.
	ADD R8, R8, #1		;counter for nth term in list
	MOV R12, #0			;reset occurance counter
	MOV R11, #0			;reset iteration counter
	
	CMP R8, R1			;if selected number exceeds list range, exit
	BGT exitProgram	
	B numberTester		;else, return to test next number in list.
	
	;takes place when the highest occurance of the previous loop is lower
	;than the highest mode.
lowerThanCurrent
	ADD R8, R8, #1		;counter for nth term in list
	MOV R12, #0			;reset occurance counter
	MOV R11, #0			;reset iteration
	ADD R10, R10, #1	;advance number to next in list.
	
	B numberTester
exitProgram
	
STOP	B	STOP

tstN	DCD	8			; N (number of numbers)
tstvals	DCD	9, 3, 9, 15, 3, 9, 1, 9	; numbers

	END