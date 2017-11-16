.include "./cs47_macro.asm"

.data
msg1: 		.asciiz "Enter number for "
strHi: 		.asciiz "Hi"
strLo:          .asciiz "Lo"
strQuery: 	.asciiz " ? "
strEqual: 	.asciiz " = "
strComma:	.asciiz " , "
strNewline:	.asciiz "\n"
msg2:		.asciiz "Before swapping "
msg3:		.asciiz "After swapping "

.text
.globl main
main:	
	# Get and store Hi value
	print_str(msg1)       	# Prints: Enter a number for
	print_str(strHi)      	# Prints: Hi
	print_str(strQuery)   	# Prints: ? 
	read_int($t0)         	# Read integer into $t0
	mthi $t0	      	# Move $t0 value to Hi
	
	# Get and store Lo value
	print_str(msg1)       	# Prints: Enter a number for
	print_str(strLo)      	# Prints: Hi
	print_str(strQuery)   	# Prints: ? 
	read_int($t0)         	# Read integer into $t0
	mtlo $t0	      	# Move $t0 value to Lo
	
	#print content of Hi and Lo
	print_str(msg2) 	# Prints: Before swaping
	print_hi_lo(strHi,strEqual, strComma, strLo)	
				# Prints: Hi = <val> , Lo = <val>
	print_str(strNewline)	# Prints: newline
	
	# Swap the content
	swap_hi_lo($t0,$t1)
	
	#print content of Hi and Lo
	print_str(msg3) 	# Prints: After swaping
	print_hi_lo (strHi, strEqual, strComma, strLo)		
				# Prints: Hi = <val> , Lo = <val>
	print_str(strNewline)	# Prints: newline
	
	exit
