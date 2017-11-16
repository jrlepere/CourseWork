addi $s0, $zero, 4  # a($s0) = 4
addi $s1, $zero, 8 # b($s1) = 8
addi $s2, $zero, 24 # c($s2) = 24
add $t0, $s0, $s1   # t0 = (a + b)
and $t1, $s1, $s2   # t1 = (b & c)
addi $t2, $zero, 3  # t2 = 3
sub $t3, $t2, $s0   # t3 = (3 - a)
add $t0, $t0, $t1   # t0 = (a + b) + (b & c)
sub $s3, $t0, $t3   # s3 = (a + b) + (b & c) - (3 - a)

#23 = 00010111
# -23 = 11101000 + 1 = 11101001
addi $v0, $zero, 0xFFFFFFE9