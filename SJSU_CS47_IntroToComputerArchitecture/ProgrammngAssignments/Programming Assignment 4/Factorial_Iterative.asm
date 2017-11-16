.include "./cs47_macro.asm"

.data
msg1: .asciiz "Enter a number ? "
msg2: .asciiz "Factorial of the number is "
charCR: .asciiz "\n"

.text
.globl main
main:	print_str(msg1)
	read_int($t0)
	
# Write body of the iterative
# factorial program here
# Store the factorial result into 
# register $s0

	bltz $t0, Exit
	li $s0, 1
	beqz $t0, Exit
	li $t1, 0
	Loop:	add $t1, $t1, 1
		mul $s0, $s0, $t1
		bne $t1, $t0, Loop
	Exit:
	
	print_str(msg2)
	print_reg_int($s0)
	print_str(charCR)
	
	exit
	
