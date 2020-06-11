;
; CSU11021 Introduction to Computing I 2019/2020
; 64-bit Shift
;

	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R1, =0xD9448A90		; most significaint 32 bits (63 ... 32)
	LDR	R0, =0x88AA9D3B		; least significant 32 bits (31 ... 0)
	LDR	R2, =2 		; shift count
	
	CMP	R2, #0
	BLT	ifneg
	CMP	R2, #0	
	BGT	ifpos
	
	LDR	R3, =0			; i = 0

ifneg					; LEFT SHIFT	
	NEG	R2, R2			; negative count changed to positive
	CMP	R2, #0			; if (count > 0) 
	BLE	endif1			; { 
whloop1	CMP	R3, R2			;  while (i = 0; i < count; i++)
	BGE	endwh1			;   {
	MOV	R1, R1, LSL #1		;    R1 = R1 << 1;
	MOVS	R0, R0, LSL #1		;    R0 = R0 << 1;
	BCC	nocarry1		;    if (C = 1) {
	ADD	R1, R1, #1		;     R1++;
nocarry1				;    }
	ADD	R3, R3, #1		;    i++
	B	whloop1			;   } 
endif1					; }
endwh1

ifpos					; RIGHT SHIFT
	CMP	R2, #0			; if (count > 0) 
	BLE	endif2			; { 
whloop2	CMP	R3, R2			;  while (i = 0; i < count; i++)
	BGE	endwh2			;   {
	MOV	R0, R0, LSR #1		;    R0 = R0 >> 1;
	MOVS	R1, R1, LSR #1		;    R1 = R1 >> 1;
	LDR	R4, =0x80000000		;    mask = 0x80000000
	BCC	nocarry2		;    if (C = 1) {
	ORR	R0, R0, R4		;     R0 = R0 | mask
nocarry2				;     }
	ADD	R3, R3, #1		;    i++ 
	B	whloop2			;   }
endif2					;  }
endwh2
	
STOP	B	STOP

	END
