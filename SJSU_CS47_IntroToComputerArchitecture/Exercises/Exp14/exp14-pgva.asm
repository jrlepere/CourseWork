.include "../cs47_macro.asm"

.data
msg1: .asciiz "Global var value is "
strCR: .asciiz "\n"
msg2: .asciiz "    ----> Control at print_gva\n"

.text
.globl print_gva
print_gva:
	print_str(msg2)		# Print msg2 on console
	print_str(msg1)		# Print msg1 on console
	lw $t1, glob_var_a	# R[t1] = M[glob_var_a]
	print_reg_int($t1)	# Print R[t1] on console
	print_str(strCR)	# Print newline on console
	jr $ra			# return from procedure
