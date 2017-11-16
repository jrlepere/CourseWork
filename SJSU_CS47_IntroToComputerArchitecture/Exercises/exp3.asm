.include "./cs47_macro.asm"

#<--------- APPICATION PROGRAM -------->#
#<-------- DATA SEGMENT DEFINITION 0000000>#
.data
str: .asciiz "the answer is = "

#<------- CODE SEGMENT DEFINITIONS ------>#
.text
.globl main
main: 	print_str(str)
	print_int(5)
	exit