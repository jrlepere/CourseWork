.include "./cs47_macro.asm"

.data
.align 0
var_a: .byte 0x10
var_c: .byte 0x20
var_b: .half 0x3210
var_d: .word 0x76543210

.text
.globl main
main:
	# Variables are read into reg from mem
	lb $s0, var_a
	lh $s1, var_b
	lb $s2, var_c
	lw $s3, var_d	
	
	sw $s0, 0x0($sp)
	addi $sp, $sp, -4
	sw $s1, 0x0($sp) 
	addi $sp, $sp, -4 
	sw $s2, 0x0($sp) 
	addi $sp, $sp, -4 
	sw $s3, 0x0($sp) 
	addi $sp, $sp, -4
	
	addi $sp, $sp, +4 
	lw $t0, 0x0($sp)
	addi $sp, $sp, +4 
	lw $t1, 0x0($sp) 
	addi $sp, $sp, +4 
	lw $t2, 0x0($sp) 
	addi $sp, $sp, +4 
	lw $t3, 0x0($sp)
	
	exit