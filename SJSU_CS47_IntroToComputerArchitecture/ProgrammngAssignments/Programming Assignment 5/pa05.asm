.include "./cs47_macro.asm"

.data 0x10000000
var_a: .byte 0x10          # Type: char  (8-bit)
var_b: .half 0x3210        # Type: short (16-bit)
var_c: .byte 0x20          # Type: char  (8-bit)
var_d: .word 0x76543210    # Type: int   (32-bit)
msg_push: .asciiz "Pushing -> "
msg_pop: .asciiz "Popping -> "
char_pipe: .asciiz " | "
char_nl: .asciiz "\n"

.text
.globl main
main:
	# these are all pseudo instruction
	# MIPS natively supports only relative
	# address translation (e.g. lb $s0, 0x2345($gp))
	lb	$s0, var_a # R[s0] = M[var_a](7:0)	
	lh	$s1, var_b # R[s1] = M[var_b](15:0)
	lb      $s2, var_c # R[s2] = M[var_c](7:0)
	lw      $s3, var_d # R[s3] = M[var_d](15:0)

	
	# Push the S0...4 to stack
	print_str(msg_push)
	push($s0)
	print_reg_int($s0)
	
	push($s1)
	print_str(char_pipe)
	print_reg_int($s1)

	push($s2)
	print_str(char_pipe)
	print_reg_int($s2)
	
	push($s3)
	print_str(char_pipe)
	print_reg_int($s3)
	
	print_str(char_nl)
	
	# pop the t0...4 to stack
	print_str(msg_pop)
	pop($t0)
	print_reg_int($t0)
	
	pop($t1)
	print_str(char_pipe)
	print_reg_int($t1)
	
	pop($t2)
	print_str(char_pipe)
	print_reg_int($t2)
	
	pop($t3)
	print_str(char_pipe)
	print_reg_int($t3)
	
	print_str(char_nl)
	# system Exit
	exit
