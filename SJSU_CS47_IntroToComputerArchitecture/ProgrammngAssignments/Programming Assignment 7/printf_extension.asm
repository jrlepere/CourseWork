.include "./cs47_macro.asm"

.data
.align 2
format1: .asciiz "My string is %s and integer is %d \n"
int_a: .word 0x10
str_b: .asciiz "'test printf'"
format2: .asciiz "My name is %s\nI am %d year old\nI love to eat %s\nI will graduate in %d\n"
str_name: .asciiz "John Adams"
int_age: .word 26
str_food: .asciiz "pizza"
int_year: .word 2015
format3: .asciiz "My name is \\%s\nI am \\%d year old\nI love to eat \\%s\nI will graduate in \\%d\n"

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
	beq	$a0, '\\', printf_nospecial
	beq     $a0, '%', printf_format
	# print the character
	li	$v0, 11
	syscall
	j 	printf_last
printf_nospecial:
	addi	$s0, $s0, 1 #Moves to the next character ('%')
	lbu	$a0, 0($s0) #Loads the character to the argument
	# Print the character
	li	$v0, 11
	syscall
	j	printf_last
printf_format:
	addi	$s1, $s1, 1 # increase argument index
	mul	$t0, $s1, 4
	add	$t0, $t0, $fp # all print type assumes 
			      # the latest argument pointer at $t0
	addi	$s0, $s0, 1
	lbu	$a0, 0($s0)
	beq 	$a0, 'd', printf_int
	beq	$a0, 's', printf_str
printf_int: 
	lw	$a0, 0($t0)
	li	$v0, 1
	syscall
	j 	printf_last
printf_str:
	lw	$a0, 0($t0)
	li	$v0, 4
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
.globl main
main:
	# push the arguments
	# in reverse order of the sequence
	# in the format
	lw	$t0, int_a
	push($t0)
	la	$t0, str_b
	push ($t0)
	# load the format in argument
	# and call printf
	la	$a0, format1
	jal 	printf
	# pop the arguments
	pop($t0)
	pop($t0)
	
	# print the next string
	lw	$t0, int_year
	push($t0)
	la	$t0, str_food
	push($t0)
	lw	$t0, int_age
	push($t0)
	la	$t0, str_name
	push($t0)
	la	$a0, format2
	jal 	printf
	pop($t0)
	pop($t0)
	pop($t0)
	pop($t0)
	
	la	$a0, format3
	jal 	printf
	exit
