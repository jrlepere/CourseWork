import math


def comp_trap(f, min, max, n):
    x = min
    result = 0.0
    h = ((max - min) / n)
    i = 0
    while i <= n:
        if i == 0 or i == n:
            result += f(x)
        else:
            result += 2 * f(x)
        x += h
        i += 1

    return (h / 2) * result


def f(x): return 1 / (x * x)


def int_f(a, b):
    def h(x): return (-1) / x

    return h(b) - h(a)


a = 0.5
b = 20.5

n = 1
while n <= 8:
    print str(n) + ": " + str(comp_trap(f, a, b, n))
    n *= 2

print int_f(a, b)


def computeRomberg1(f, min, max, n):
    h = (max - min) / n
    result = 0.0
    x = min
    for i in range(n + 1):
        if i == 0 or i == n:
            result += f(x)
        else:
            result += 2 * f(x)
        x += h
    return (h / 2.0) * result


def computeRomberg2(R1, R2, n):
    t = (1.0 / (math.pow(4.0, n - 1) - 1.0))
    return R1 + t * (R1 - R2)


R1_1 = computeRomberg1(f, a, b, 1)
R2_1 = computeRomberg1(f, a, b, 2)
R3_1 = computeRomberg1(f, a, b, 4)
R4_1 = computeRomberg1(f, a, b, 8)

print "R1_1: " + str(R1_1)
print "R2_1: " + str(R2_1)
print "R3_1: " + str(R3_1)
print "R4_1: " + str(R4_1)
print

R2_2 = computeRomberg2(R2_1, R1_1, 2)
R3_2 = computeRomberg2(R3_1, R2_1, 2)
R4_2 = computeRomberg2(R4_1, R3_1, 2)

print "R2_2: " + str(R2_2)
print "R3_2: " + str(R3_2)
print "R4_2: " + str(R4_2)
print

R3_3 = computeRomberg2(R3_2, R2_2, 3)
R4_3 = computeRomberg2(R4_2, R3_2, 3)

print "R3_3: " + str(R3_3)
print "R4_3: " + str(R4_3)
print

R4_4 = computeRomberg2(R4_3, R3_3, 4)

print "R4_4: " + str(R4_4)


n = 1
act = int_f(a, b)
done = False
err_tol = math.pow(10,-3)

while not done:
    res = comp_trap(f, a, b, n)
    print str(n) + ": " + str(res)
    err = abs(res - act)
    if err <= err_tol:
        done = True
    n *= 2


print
print


def g(u):
    return 1.0/(math.pow(20.0*u+21.0,2))

c1 = 0.2369268850
c2 = 0.4786286705
c3 = 0.5689888889
c4 = c2
c5 = c1

p1 = 0.9061798459
p2 = 0.5384693101
p3 = 0.0
p4 = -p2
p5 = -p1

gauss5 = 42*(c1*g(p1)+c2*g(p2)+c3*g(p3)+c4*g(p4)+c5*g(p5))
print gauss5

