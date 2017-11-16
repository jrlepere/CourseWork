.include "./cs47_proj_macro.asm"
.include "./cs47_proj_procs.asm"
.include "./insertion_sort_proc.asm"
.include "./sort_test_data.asm"

.data
.align 2
main_msg1: .asciiz "------------------------------------------\n"
main_pass_msg: .asciiz "\n**** PASSED [%d/%d] ****\n"
main_fail_msg: .asciiz "\n**** FAILED [%d/%d] ****\n"
main_no_of_test: .word 0
main_no_of_pass: .word 0

.text
.globl main
main:
	la	$a0, main_msg1
	jal	printf
	#for(i=0;i<var_xx; i++)
	add	$s0, $s0, 0	# index i = 0
	lw	$s1, var_xx	# $s1 as limit
	add	$s7, $zero, $zero	# Flag for pass or fail
main_loop1:
	mul	$t0, $s0, 4
	la	$s2, var_aa
	add	$s2, $s2, $t0	# var_aa[$s0]
	la	$s3, var_mm
	add	$s3, $s3, $t0	# var_mm[$s0]
	
	# print array before
	lw	$a0, 0($s2)
	lw	$a1, 0($s3)
	lw	$a1, 0($a1)
	jal	print_int_arr
	
	# call the procedure
	lw	$a0, 0($s2)
	lw	$a1, 0($s3)
	lw	$a1, 0($a1)
	jal	insertion_sort

	# test the array and set flag
	lw	$a0, 0($s2)
	lw	$a1, 0($s3)
	lw	$a1, 0($a1)
	jal	test_ordered_array
	beq	$v0, $zero, main_test_pass
	lw	$t0, main_no_of_pass
	addi	$t0, $t0, -1
	sw	$t0, main_no_of_pass
	addi	$s7, $zero, 1
main_test_pass:
	lw	$t0, main_no_of_pass
	addi	$t0, $t0, 1
	sw	$t0, main_no_of_pass
	# print array after
	lw	$a0, 0($s2)
	lw	$a1, 0($s3)
	lw	$a1, 0($a1)
	jal	print_int_arr
	
	la	$a0, main_msg1
	jal	printf
	# End of for(i=0;i<var_xx; i++)
	addi	$s0, $s0, 1
	lw	$t0, main_no_of_test
	addi	$t0, $t0, 1
	sw	$t0, main_no_of_test
	blt	$s0, $s1, main_loop1
	
	# test flag and print
	lw	$t0, main_no_of_test
	lw	$t1, main_no_of_pass
	push($t0)
	push($t1)
	beq	$s7, $zero, main_print_pass
	call_printf(main_fail_msg)
	pop($t0)
	pop($t0)
	j main_end
main_print_pass:
	call_printf(main_pass_msg)
	pop($t0)
	pop($t0)
	j main_end
main_end:
	exit
