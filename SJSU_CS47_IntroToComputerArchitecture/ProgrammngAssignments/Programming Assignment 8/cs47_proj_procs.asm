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
#####################################################################
# Procedure: print_int_arr
# Argument:
# 	$a0: base address of array
#	$a1: number of array elements
# Return:
#	None
# 
# Notes: Prints elements in array 10 in a line
#####################################################################
.data
.align 2
print_int_arr_msg1: .asciiz "Array is %s\n"
print_int_arr_sorted: .asciiz "SORTED"
print_int_arr_unsorted: .asciiz "UNSORTED"
print_int_arr_fmt1: .asciiz "%d "
print_int_arr_fmt2: .asciiz "\n"
.text
print_int_arr:
	# RTE store
	# Need to store 4*9 + 8 - 4 = 40 bytes
	addi	$sp, $sp, -40
	sw	$fp, 40($sp)
	sw	$ra, 36($sp)
	sw	$a0, 32($sp)
	sw	$a1, 28($sp)
	sw	$s0, 24($sp)
	sw	$s1, 20($sp)
	sw	$s2, 16($sp)
	sw	$s3, 12($sp)
	sw	$s4,  8($sp)
	addi	$fp, $sp, 40
	# body
	# test the array
	jal	test_ordered_array
	beq	$v0, $zero, print_int_arr_load_sorted
	la	$t0, print_int_arr_unsorted
	push($t0)
	j	print_int_arr_print_msg1
print_int_arr_load_sorted:
	la	$t0, print_int_arr_sorted
	push($t0)
print_int_arr_print_msg1:
	la	$a0, print_int_arr_msg1
	jal	printf
	pop($t0)
	
	# restore the array address
	lw	$a0, 32($sp)
	# print the array
	addi	$s0, $zero, 10 			# constant 10 at line break
	add	$s1, $zero, $zero		# index for element
	add	$s2, $a0, $zero			# $s2 = base addres of array
	add	$s3, $a1, $zero			# $s3 = number of elements'
	add	$s4, $zero, $zero		# $s4 = 0
print_int_arr_loop:
	beq	$s1, $s3, print_int_arr_L2	# if ($s1 == $a1) goto print_int_arr_end
	mul	$t0, $s1, 4			# $t0 = $s1 * 4
	add	$t0, $s2, $t0			# $t0 = $s2 + $t0
	lw	$t0, 0($t0)			# $t0 = M[$t0 + 0]
	# Call printf("%d ", $t0)
	push($t0)
	la	$a0, print_int_arr_fmt1
	jal	printf
	pop($t0)
	addi	$s4, $s4, 1			# $s4 = $s4 + 1
	# if this is 10th element reset and insert '\n'
	bne	$s4, 10, print_int_arr_L1
	la	$a0, print_int_arr_fmt2
	jal	printf
	add	$s4, $zero, $zero		# $s4 = 0
print_int_arr_L1:
	# end part of program
	addi	$s1, $s1, 1 			# $s1 = $s1 + 1
	j	print_int_arr_loop		# goto print_int_arr_loop
print_int_arr_L2:
	la	$a0, print_int_arr_fmt2
	jal	printf
print_int_arr_end:
	# RTE restore and return
	lw	$fp, 40($sp)
	lw	$ra, 36($sp)
	lw	$a0, 32($sp)
	lw	$a1, 28($sp)
	lw	$s0, 24($sp)
	lw	$s1, 20($sp)
	lw	$s2, 16($sp)
	lw	$s3, 12($sp)
	lw	$s4,  8($sp)
	addi	$sp, $sp, 40
	jr $ra

#-------------------------------------------
# Procedure: test_ordered_array
# Argument: 
#	$a0: Base address of the array
#       $a1: Number of array element
# Return:
#       $v0: 0 - pass
#            1 - fail
#
# Notes: Implement testing of ordered array
#	 to test the sorting result
#-------------------------------------------
.text
test_ordered_array:
	# RTE store
	# Need to store 4*4 + 8 - 4 =20
	addi	$sp, $sp, -20
	sw	$fp, 20($sp)
	sw	$ra, 16($sp)
	sw	$a0, 12($sp)
	sw	$a1,  8($sp)
	addi	$fp, $sp, 20
	# body
	add	$v0, $zero, $zero
	ble	$a1, 1, test_ordered_array_end
	# for ($t0=1; $t0<$a1; $t0++)
	addi	$t0, $zero, 1	# $t0 = 1
test_ordered_array_loop:
	mul	$t1, $t0, 4
	add	$t2, $t1, $a0
	lw	$t2, 0($t2)
	addi	$t3, $t1, -4
	add	$t3, $t3, $a0
	lw	$t3, 0($t3)
	ble	$t3, $t2, test_ordered_array_ok
	addi	$v0, $v0, 1
	j	test_ordered_array_end
test_ordered_array_ok:
	# End of for ($t0=1; $t0<$a1; $t0++)
	add	$t0, $t0, 1
	blt	$t0, $a1, test_ordered_array_loop
test_ordered_array_end:
	# RTE restore
	lw	$fp, 20($sp)
	lw	$ra, 16($sp)
	lw	$a0, 12($sp)
	lw	$a1,  8($sp)
	addi	$sp, $sp, 20
	jr	$ra
