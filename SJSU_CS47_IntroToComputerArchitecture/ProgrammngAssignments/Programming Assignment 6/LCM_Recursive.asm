.include "./cs47_macro.asm"

.data
msg1: .asciiz "Enter a +ve number : "
msg2: .asciiz "Enter another +ve number : "
msg3: .asciiz "LCM of "
s_is: .asciiz "is"
s_and: .asciiz "and"
s_space: .asciiz " "
s_cr: .asciiz "\n"

.text
.globl main
main:
	print_str(msg1)
	read_int($s0)
	print_str(msg2)
	read_int($s1)
	
	move $v0, $zero
	move $a0, $s0
	move $a1, $s1
	move $a2, $s0
	move $a3, $s1
	jal  lcm_recursive
	move $s3, $v0
	
	print_str(msg3)
	print_reg_int($s0)
	print_str(s_space)
	print_str(s_and)
	print_str(s_space)
	print_reg_int($s1)
	print_str(s_space)
	print_str(s_is)
	print_str(s_space)
	print_reg_int($s3)
	print_str(s_cr)
	exit

#------------------------------------------------------------------------------
# Function: lcm_recursive 
# Argument:
#	$a0 : +ve integer number m
#       $a1 : +ve integer number n
#       $a2 : temporary LCM by increamenting m, initial is m
#       $a3 : temporary LCM by increamenting n, initial is n
# Returns
#	$v0 : lcm of m,n 
#
# Purpose: Implementing LCM function using recursive call.
# 
#------------------------------------------------------------------------------
lcm_recursive:

	beq $a0, $a1, lcm_return # go to lcm_return if m == n
	bgt $a0, $a1, increment_n # go to increment_n if m > n
	blt $a0, $a1, increment_m # go to increment_m if m < n
	# j lcm_recursive not necessary because every case is covered above
	
#Helper method to increment m by the initial m
increment_m:
	add $a0, $a2, $a0 # adds the initial value of n to n
	j lcm_recursive # go to lcm_recursive, now with the new m value

#Helper method to increment n by the initial n
increment_n:
	add $a1, $a3, $a1 # adds the initial value of m to m
	j lcm_recursive # go to lcm_recursive, now with the new n value

#Method to return to the caller
lcm_return:
	la $v0, ($a0) # sets v0 to the value of a0
	jr $ra # return to the caller
	
