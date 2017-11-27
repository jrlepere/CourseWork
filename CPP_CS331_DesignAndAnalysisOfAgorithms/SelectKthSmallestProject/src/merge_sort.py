from random import randint

# Merge Sort
def merge_sort(x):
    if len(x) <= 1:
        return x
    else:
        mid = len(x)/2
        left = merge_sort(x[0:mid])
        right = merge_sort(x[mid:len(x)])
        left_i = 0
        right_i = 0
        x_i = 0
        while (left_i < len(left)) and (right_i < len(right)):
            if left[left_i] <= right[right_i]:
               x[x_i] = left[left_i]
               left_i += 1
            else:
                x[x_i] = right[right_i]
                right_i += 1
            x_i += 1
        while (left_i < len(left)):
               x[x_i] = left[left_i]
               left_i += 1
               x_i += 1
        while (right_i < len(right)):
               x[x_i] = right[right_i]
               right_i += 1
               x_i += 1
        return x

def merge_sort_2(x):
    if len(x) <= 1:
        return x
    else:
        mid = len(x)/2
        left = merge_sort(x[0:mid])
        right = merge_sort(x[mid:len(x)])
        left_i = 0
        right_i = 0
        x_i = 0
        while (left_i < len(left)) and (right_i < len(right)):
            if left[left_i][1] <= right[right_i][1]:
               x[x_i] = left[left_i]
               left_i += 1
            else:
                x[x_i] = right[right_i]
                right_i += 1
            x_i += 1
        while (left_i < len(left)):
               x[x_i] = left[left_i]
               left_i += 1
               x_i += 1
        while (right_i < len(right)):
               x[x_i] = right[right_i]
               right_i += 1
               x_i += 1
        return x


if __name__ == '__main__':
    x = []
    for i in range(0,20):
        x.append(randint(0,100))

    print x
    merge_sort(x)
    print x
