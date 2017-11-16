.include "./cs47_macro.asm"

.extern glob_var_a 4  # Reserving a sapce in the global var memory space

.data
msg1: .asciiz "Enter an integer : "
msg2: .asciiz "---> Control back to main\n"

.text
.globl main
main:
	print_str(msg1)		# Print msg1 in console
	read_int($t0)		# R[t0] = <user input>
	sw $t0, glob_var_a	# M[glob_var_a] = R[t0]
	jal print_gva		# Call procedure print_gva
L1:	print_str(msg2)		# Print msg2 in console
	
	exit
