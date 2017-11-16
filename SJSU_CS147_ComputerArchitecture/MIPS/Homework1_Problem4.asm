# B[3] = a + B[7]

addi $s0, $zero, 0x10010000     # $a0 = B 
addi $s1, $zero, 8
addi $t4, $zero, 22        # helper to store 22 into B[7] to check
sw $t4, 28($s0)
lw $t0, 28($s0)
add $t0, $s1, $t0
sw $t0, 12($s0)
lw $t3, 12($s0)





