import math
import matplotlib.pyplot as plt

def f(t, y):
    return 2 * y - math.pow(math.e, t)

def f_prime(t, y):
    return 0 * y - math.pow(math.e, t)

t_start = 0.00
t_end = 3.00
h = 0.1
w0 = 1.00

def euler():
    data = []
    t = t_start
    w = w0
    data.append(w)
    count = 0.0
    max_count = (t_end - t_start) / h
    while count < max_count:
        w = w + h * f(t, w)
        t = t + h
        data.append(w)
        count += 1
    return data

def taylor_order_2():
    data = []
    t = t_start
    w = w0
    data.append(w)
    count = 0.0
    max_count = (t_end - t_start) / h
    while count < max_count:
        w = w + h * f(t, w) + (math.pow(h, 2) / 2) * f_prime(t, w)
        t = t + h
        data.append(w)
        count += 1
    return data

def runge_kutta_4():
    data = []
    t = t_start
    w = w0
    data.append(w)
    count = 0.0
    max_count = (t_end - t_start) / h
    while count < max_count:
        k1 = h * f(t, w)
        k2 = h * f(t + 0.5 * h, w + 0.5 * k1)
        k3 = h * f(t + 0.5 * h, w + 0.5 * k2)
        k4 = h * f(t + h, w + k3)
        w = w + (1.0 / 6.0) * (k1 + 2.0 * k2 + 2.0 * k3 + k4)
        t = t + h
        data.append(w)
        count += 1
    return data

eulerData = euler()
print "Euler's Method: " + str(eulerData[len(eulerData) - 1])

taylorData = taylor_order_2()
print "Taylor's Method of Order 2: " + str(taylorData[len(taylorData) - 1])

rk4Data = runge_kutta_4()
print "Runge-Kutta 4th Order: " + str(rk4Data[len(rk4Data) - 1])

print "Expected: " + str(math.pow(math.e, 3))

x = []
for i in range(int((t_end - t_start) / h) + 1):
    x.append(i * h)

rk4Line = plt.plot(x, rk4Data, 'ro-', label="Runge-Kutta 4")
eulerLine = plt.plot(x, eulerData, 'bo-', label="Euler's Method")
taylorLine = plt.plot(x, taylorData, 'go-', label="Taylor's Method 2nd Order")
plt.xlabel("t")
plt.ylabel("y")
plt.title("y'(t) = 2y - e^t")
plt.legend()
plt.grid()
plt.show()