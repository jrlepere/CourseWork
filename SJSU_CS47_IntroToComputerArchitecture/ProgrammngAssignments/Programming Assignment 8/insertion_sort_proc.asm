.text
#-------------------------------------------
# Procedure: insertion_sort
# Argument: 
#	$a0: Base address of the array
#       $a1: Number of array element
# Return:
#       None
# Notes: Implement insertion sort, base array 
#        at $a0 will be sorted after the routine
#	 is done.
#-------------------------------------------
insertion_sort:
	# Caller RTE store (TBD)
	addi $sp, $sp, -20
	sw $fp, 20($sp)
	sw $ra, 16($sp)
	sw $a0, 12($sp)
	sw $a1, 8($sp)
	addi $fp, $sp, 20
	
	# Implement insertion sort (TBD)
	add $t0, $zero, $zero
for:
	addi $t0, $t0, 1
	bgt $t0, $a1, insertion_sort_end
	addi $t1, $t0, 0
	lw $a0, 12($sp)
	mul $t3, $t1, 4
	add $a0, $a0, $t3
while:
	blez $t1, for
	lw $t2, 0x0($a0)
	addi $a0, $a0, -4
	lw $t3, 0x0($a0)
	ble $t3, $t2, for
	sw $t2, 0x0($a0)
	addi $a0, $a0, 4
	sw $t3, 0x0($a0)
	addi $a0, $a0, 4
	addi, $t1, $t1, -1
	j while
	
insertion_sort_end:
	# Caller RTE restore (TBD)
	lw $fp, 20($sp)
	lw $ra, 16($sp)
	lw $a0, 12($sp)
	lw $a1, 8($sp)
	addi $sp, $sp, 20
	# Return to Caller
	jr	$ra
