.include "./cs47_proj_macro.asm"
.text
#-----------------------------------------------
# C style signature 'printf(<format string>,<arg1>,
#			 <arg2>, ... , <argn>)'
#
# This routine supports %s and %d only
#
# Argument: $a0, address to the format string
#	    All other addresses / values goes into stack
#-----------------------------------------------
printf:
	#store RTE - 5 *4 = 20 bytes
	addi	$sp, $sp, -24
	sw	$fp, 24($sp)
	sw	$ra, 20($sp)
	sw	$a0, 16($sp)
	sw	$s0, 12($sp)
	sw	$s1,  8($sp)
	addi	$fp, $sp, 24
	# body
	move 	$s0, $a0 #save the argument
	add     $s1, $zero, $zero # store argument index
printf_loop:
	lbu	$a0, 0($s0)
	beqz	$a0, printf_ret
	beq     $a0, '%', printf_format
	# print the character
	li	$v0, 11
	syscall
	j 	printf_last
printf_format:
	addi	$s1, $s1, 1 # increase argument index
	mul	$t0, $s1, 4
	add	$t0, $t0, $fp # all print type assumes 
			      # the latest argument pointer at $t0
	addi	$s0, $s0, 1
	lbu	$a0, 0($s0)
	beq 	$a0, 'd', printf_int
	beq	$a0, 's', printf_str
	beq	$a0, 'c', printf_char
printf_int: 
	lw	$a0, 0($t0) # printf_int
	li	$v0, 1
	syscall
	j 	printf_last
printf_str:
	lw	$a0, 0($t0) # printf_str
	li	$v0, 4
	syscall
	j 	printf_last
printf_char:
	lbu	$a0, 0($t0)
	li	$v0, 11
	syscall
	j 	printf_last
printf_last:
	addi	$s0, $s0, 1 # move to next character
	j	printf_loop
printf_ret:
	#restore RTE
	lw	$fp, 24($sp)
	lw	$ra, 20($sp)
	lw	$a0, 16($sp)
	lw	$s0, 12($sp)
	lw	$s1,  8($sp)
	addi	$sp, $sp, 24
	jr $ra
	
add_logic:
	addi $sp, $sp, -24
	sw $fp, 24($sp)
	sw $ra, 20($sp)
	sw $a0, 16($sp)
	sw $a1, 12($sp)
	sw $a2, 8($sp)
	addi $fp, $fp, 24
	add $a2, $zero, $zero
	j add_sub_logical
sub_logic:
	addi $sp, $sp, -24
	sw $fp, 24($sp)
	sw $ra, 20($sp)
	sw $a0, 16($sp)
	sw $a1, 12($sp)
	sw $a2, 8($sp)
	addi $fp, $fp, 24	
	add $a2, $zero, $zero
	nor $a2, $a2, $zero
	nor $a1, $a1, $zero
	j add_sub_logical
# $t0 = $a0[i]
# $t1 = $a1[i]
# $t2 = i
# $t3 = C (Carry)
# $t4 = result of $t0 + $t1 + $t3
add_sub_logical:
	add $t2, $zero, $zero
	add $v0, $zero, $zero
	addi $t7, $zero, 0x1
	extract_nth_bit($t3, $a2, $zero) # C = a2[0]
	
AddLoop: 
	extract_nth_bit($t0, $a0, $t2)
	extract_nth_bit($t1, $a1, $t2)
	add $t4, $zero, $zero
	add $t4, $t0, $t1
	add $t4, $t4, $t3
	extract_nth_bit($t5, $t4, $zero) # $t5 = Y
	extract_nth_bit($t3, $t4, $t7)
	insert_to_nth_bit($v0,$t2,$t5,$t6)
	addi $t2, $t2, 0x1
	blt $t2, 0x20,AddLoop
	add $v1, $t3, $zero
	lw $fp, 24($sp)
	lw $ra, 20 ($sp)
	lw $a0, 16($sp)
	lw $a1, 12($sp)
	lw $a2, 8($sp)
	addi $sp, $sp, 24
	jr $ra
	
twos_complement:
	addi $sp, $sp, -16
	sw $fp, 16($sp)
	sw $ra, 12($sp)
	sw $a0, 8($sp)
	addi $fp, $fp, 16
	not $a0, $a0
	addi $v0, $a0, 0x1
	lw $fp, 16($sp)
	lw $ra, 12($sp)
	lw $a0, 8($sp)
	addi $sp, $sp, 16
	jr $ra
	
twos_complement_if_negative:
	addi $sp, $sp, -16
	sw $fp, 16($sp)
	sw $ra, 12($sp)
	sw $a0, 8($sp)
	addi $fp, $fp, 16
	bge $a0, $zero, twos_complement_if_negative_end
	jal twos_complement
twos_complement_if_negative_end:
	lw $fp, 16($sp)
	lw $ra, 12($sp)
	lw $a0, 8($sp)
	addi $sp, $sp, 16
	jr $ra
	
twos_complement_64bit:
	addi $sp, $sp, -20
	sw $fp, 20($sp)
	sw $ra, 16($sp)
	sw $a0, 12($sp)
	sw $a1, 8($sp)
	addi $fp, $fp, 20
	not $a0, $a0
	not $a1, $a1
	add $a2, $a1, $zero
	add $a1, $zero, 0x1
	jal add_logic
	add $a3, $zero, $v0 #store the result in t1
	add $a0, $zero, $v1 #set a0 to the carry result
	add $a1, $a2, $zero #set a1 back to original a1
	jal add_logic
	add $v1, $zero, $v0
	add $v0, $zero, $a3
	lw $fp, 20($sp)
	lw $ra, 16($sp)
	sw $a0, 12($sp)
	sw $a1, 8($sp)
	addi $sp, $sp, 20
	jr $ra
	
# sets v0 to 0x00000000 if a0 is 0x0
# sets v0 to 0xFFFFFFFF if ao is 0x1
bit_replicator:
	addi $sp, $sp, -24
	sw $fp, 24($sp)
	sw $ra, 20($sp)
	sw $a0, 16($sp)
	sw $t0, 12($sp)
	sw $t1, 8($sp)
	addi $fp, $fp, 24
	
	add $v0, $zero, $zero
	beq $a0, $zero, bit_rep_end
	nor $v0, $v0, $zero
bit_rep_end:	
	lw $fp, 24($sp)
	lw $ra, 20($sp)
	lw $a0, 16($sp)
	lw $t0, 12($sp)
	lw $t1, 8($sp)
	addi $sp, $sp, 24
	jr $ra
	
# R = $v0
# I = $t0
# H = $t1
# L = $t2
# M = $t3
# X = $t4
# H[0] = $t5
# $t6 = 31
# L[0] = $a0
mul_logic_unsigned:
	addi $sp, $sp, -24
	sw $fp, 24($sp)
	sw $ra, 20($sp)
	sw $a0, 16($sp)
	sw $a1, 12($sp)
	sw $a2, 8($sp)
	addi $fp, $fp, 24
	
	add $t0, $zero, $zero
	add $t1, $zero, $zero
	add $t2, $a1, $zero
	add $t3, $a0, $zero
	addi $t6, $zero, 0x1F
mul_loop:
	extract_nth_bit($a0, $t2, $zero)
	jal bit_replicator
	and $t4, $t3, $v0
	add $t1, $t1, $t4
	srl $t2, $t2, 0x1
	extract_nth_bit($t5, $t1, $zero)
	insert_to_nth_bit($t2, $t6, $t5, $t7)
	srl $t1, $t1, 0x1
	addi $t0, $t0, 0x1
	blt $t0, 0x20, mul_loop
	add $v0, $zero, $t2
	add $v1, $zero, $t1
	lw $fp, 24($sp)
	lw $ra, 20 ($sp)
	lw $a0, 16($sp)
	lw $a1, 12($sp)
	lw $a2, 8($sp)
	addi $sp, $sp, 24
	jr $ra	
	
mul_logic_signed:
	addi $sp, $sp, -24
	sw $fp, 24($sp)
	sw $ra, 20($sp)
	sw $a0, 16($sp)
	sw $a1, 12($sp)
	sw $a2, 8($sp)
	addi $fp, $fp, 24
	
	add $v0, $a0, $zero
	jal twos_complement_if_negative
	add $t1, $v0, $zero #t1 = N1
	add $a0, $a1, $zero
	add $v0, $a0, $zero
	jal twos_complement_if_negative
	add $a1, $v0, $zero
	add $a0, $t1, $zero
	jal mul_logic_unsigned
	
	addi $t4, $zero, 0x1F
	lw $a0, 16($sp)
	lw $a1, 12($sp)
	extract_nth_bit($t1, $a0, $t4)
	extract_nth_bit($t2, $a1, $t4)
	xor $t3, $t1, $t2
	beq $t3, $zero, mul_logic_signed_end
	add $a0, $v0, $zero
	add $a1, $v1, $zero
	jal twos_complement_64bit
	
mul_logic_signed_end:
	lw $fp, 24($sp)
	lw $ra, 20 ($sp)
	lw $a0, 16($sp)
	lw $a1, 12($sp)
	lw $a2, 8($sp)
	addi $sp, $sp, 24
	jr $ra		

div_logic_unsigned:
	addi $sp, $sp, -56
	sw $fp, 56($sp)
	sw $ra, 52($sp)
	sw $a0, 48($sp)
	sw $a1, 44($sp)
	sw $a2, 40($sp)
	sw $s0, 36($sp)
	sw $s1, 32($sp)
	sw $s2, 28($sp)
	sw $s3, 24($sp)
	sw $s4, 20($sp)
	sw $s5, 16($sp)
	sw $s6, 12($sp)
	sw $s7, 8($sp)
	addi $fp, $fp, 56
	
	add $s0, $zero, $zero #I
	add $s1, $zero, $a0 #Q
	add $s2, $zero, $a1 #D
	add $s3, $zero, $zero
	addi $s6, $zero, 0x1F
	addi $t8, $zero, 0x1
	
div_unsigned_loop:
	sll $s3, $s3, 0x1
	extract_nth_bit($s5, $s1, $s6)
	insert_to_nth_bit($s3, $zero, $s5, $s7)
	sll $s1, $s1, 0x1
	add $a0, $zero, $s3
	add $a1, $zero, $s2
	
	jal sub_logic
	
	add $s4, $zero, $v0
	bltz $s4, s_greater_than_0
	add $s3, $zero, $s4
	insert_to_nth_bit($s1, $zero, $t8, $s7) 
s_greater_than_0:
	addi $s0, $s0, 0x1	
	bgt $s0, $s6, div_unsigned_end
	j div_unsigned_loop		
div_unsigned_end:
	add $v0, $s1, $zero
	add $v1, $s3, $zero
		
	lw $fp, 56($sp)
	lw $ra, 52($sp)
	lw $a0, 48($sp)
	lw $a1, 44($sp)
	lw $a2, 40($sp)
	lw $s0, 36($sp)
	lw $s1, 32($sp)
	lw $s2, 28($sp)
	lw $s3, 24($sp)
	lw $s4, 20($sp)
	lw $s5, 16($sp)
	lw $s6, 12($sp)
	lw $s7, 8($sp)
	addi $sp, $sp, 56
	jr $ra	

div_logic_signed:
	addi $sp, $sp, -24
	sw $fp, 24($sp)
	sw $ra, 20($sp)
	sw $a0, 16($sp)
	sw $a1, 12($sp)
	sw $a2, 8($sp)
	addi $fp, $fp, 24
	
	add $v0, $a0, $zero
	jal twos_complement_if_negative
	add $t1, $v0, $zero #t1 = N1
	add $a0, $a1, $zero
	add $v0, $a0, $zero
	jal twos_complement_if_negative
	add $a1, $v0, $zero
	add $a0, $t1, $zero
	jal div_logic_unsigned
	add $t1, $zero, $v0 #t1 = Q
	add $t2, $zero, $v1 #t2 = R
	addi $t6, $zero, 0x1F
	lw $a0, 16($sp)
	lw $a1, 12($sp)
	extract_nth_bit($t3, $a0, $t6)
	extract_nth_bit($t4, $a1, $t6)
	xor $t5, $t3, $t4
	addi $t8, $zero, 0x1
	bne $t5, $t8, keep_q_as_is
	add $a0, $t1, $zero
	jal twos_complement
	add $t1, $v0, $zero
keep_q_as_is:
	lw $a0, 16($sp)
	extract_nth_bit($t3, $a0, $t6)
	addi $t8, $zero, 0x1
	bne $t3, $t8, keep_r_as_is
	add $a0, $t2, $zero
	jal twos_complement
	add $t2, $v0, $zero
keep_r_as_is:
	add $v0, $t1, $zero
	add $v1, $t2, $zero
	lw $fp, 24($sp)
	lw $ra, 20 ($sp)
	lw $a0, 16($sp)
	lw $a1, 12($sp)
	lw $a2, 8($sp)
	addi $sp, $sp, 24
	jr $ra

# TBD: Complete your project procedures
# Needed skeleton is given
#####################################################################
# Implement au_logical
# Argument:
# 	$a0: First number
#	$a1: Second number
#	$a2: operation code ('+':add, '-':sub, '*':mul, '/':div)
# Return:
#	$v0: ($a0+$a1) | ($a0-$a1) | ($a0*$a1):LO | ($a0 / $a1)
# 	$v1: ($a0 * $a1):HI | ($a0 % $a1)
# Notes:
#####################################################################
au_logical:
	addi $sp, $sp, -24
	sw $fp, 24($sp)
	sw $ra, 20($sp)
	sw $a0, 16($sp)
	sw $a1, 12($sp)
	sw $a2, 8($sp)
	addi $fp, $fp, 24
	
	beq $a2, '+', add_logic
	beq $a2, '-', sub_logic
	beq $a2, '*', mul_logic_signed
	beq $a2, '/', div_logic_signed
	
	lw $fp, 24($sp)
	lw $ra, 20 ($sp)
	lw $a0, 16($sp)
	lw $a1, 12($sp)
	lw $a2, 8($sp)
	addi $sp, $sp, 24
# TBD: Complete it
	jr $ra

#####################################################################
# Implement au_normal
# Argument:
# 	$a0: First number
#	$a1: Second number
#	$a2: operation code ('+':add, '-':sub, '*':mul, '/':div)
# Return:
#	$v0: ($a0+$a1) | ($a0-$a1) | ($a0*$a1):LO | ($a0 / $a1)
# 	$v1: ($a0 * $a1):HI | ($a0 % $a1)
# Notes:
#####################################################################
au_normal:

	beq $a2,'+', addition
	beq $a2, '-', subtraction
	beq $a2, '*', multiplication
	beq $a2, '/', division
	addition:
		add $v0, $a0, $a1
		j return_normal
	subtraction:
		sub $v0, $a0, $a1
		j return_normal
	multiplication:
		mul $v0, $a0, $a1
		mfhi $v1
		j return_normal
	division:
		div $a0, $a1 
		mflo $v0
		mfhi $v1
	return_normal:
# TBD: Complete it
	jr	$ra
