#<-------- MACRO DEFINITIONS -------->#
# Macro : print_str
# Usage: print_str(<address of the string>)
.macro print_str($arg)
li $v0, 4 # System call code for print_str
la $a0, $arg # Address of the string to print
syscall
.end_macro

# Macro : print_int
# Usage : print_int(<val>)
.macro print_int($arg)
li $v0, 1 # System call code for print_int
li $a0, $arg # Integer to print
syscall
.end_macro 

# Macro : exit
# Uage : exit
.macro exit
li $v0, 10
syscall
.end_macro


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