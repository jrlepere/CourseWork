#<------------------ MACRO DEFINITIONS ---------------------->#
    	# Macro : print_str
        # Usage: print_str(<address of the string>)
        .macro print_str($arg)
	li	$v0, 4     # System call code for print_str  
	la	$a0, $arg   # Address of the string to print
	syscall            # Print the string        
	.end_macro
	
	# Macro : print_int
        # Usage: print_int(<val>)
        .macro print_int($arg)
	li 	$v0, 1     # System call code for print_int
	li	$a0, $arg  # Integer to print
	syscall            # Print the integer
	.end_macro
	
	# Macro : exit
        # Usage: exit
        .macro exit
	li 	$v0, 10 
	syscall
	.end_macro
	
	# Macro : read_int
	# Usaef: read_int(<register>)
	.macro read_int($reg)
	la	$v0, 5 # System call for read_int
	syscall
	move 	$reg, $v0 # Moves v0(the inputed integer) to the provided register
	.end_macro
	
	# Macro : print_reg_int
	# Usage: print_reg_int(<register>)
	.macro print_reg_int($reg)
	li	$v0, 1 # System call for print_int
	move 	$a0, $reg # moves reg to the argument
	syscall
	.end_macro 
	
	# Macro : swap_hi_lo
	# Usage: swap_hi_lo(<temporary reg>,<temporary reg>)
	.macro swap_hi_lo($temp1, $temp2)
	mfhi	$t6 # Moves from the hi register to register t6 (arbitrary)
	mflo 	$t7 # Moves from the lo register to register t7 (arbitrary)
	mthi 	$t7 # Moves register t7 (arbitrary) to the hi register
	mtlo 	$t6 # Moves register t6 (arbitrary) to the lo register
	.end_macro 
	
	# Macro : print_hi_li
	# Usage: print_hi_lo(<str>,<str>,<str>,<str>) 
	.macro print_hi_lo($strHi, $strEqual, $strComma, $strLo) 
	li	$v0, 4 # System call for print_str
	la	$a0, $strHi # Moves the string to the argument
	syscall
	li	$v0, 4 # System call for print_str
	la	$a0, $strEqual # Moves the string to the argument
	syscall
	li	$v0, 1 # System call for print_int
	mfhi 	$a0 # Moves the hi register to the argument
	syscall
	li	$v0, 4 # System call for print_str
	la	$a0, $strComma # Moves the string to the argument
	syscall
	li	$v0, 4 # System call for print_str
	la	$a0, $strLo # Moves the string to the argument
	syscall
	li	$v0, 4 # System call for print_str
	la	$a0, $strEqual # Moves the string to the argument
	syscall
	li	$v0, 1 # System call for print_int
	mflo	$a0 # Moves the lo register to the argument
	syscall	
	.end_macro 
	
	# Macro : lwi
	#Usage: lwi(<reg>,<val>,<val>)
	.macro lwi($reg,$ui,$li)
	lui $reg, $ui
	ori $reg, $li
	.end_macro 
	
