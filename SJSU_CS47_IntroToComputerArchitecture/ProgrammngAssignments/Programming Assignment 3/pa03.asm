.include "./cs47_macro.asm"

.data
msg1: .asciiz "Register S1 contains integer number "
newline: .asciiz "\n"

.text
.globl main
main: 
	# lui overrides the lower word
	li $s1, 0xa5a5
	lui $s1, 0x5a5a
	
	# Macro to load complete word
	lwi ($s1, 0x5a5a, 0xa5a5)
	
	# print the value
	print_str(msg1)
	print_reg_int($s1)
	print_str(newline)
	
	exit
