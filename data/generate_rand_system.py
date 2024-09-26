import sys
import numpy

def main():
    if len(sys.argv) < 2:
        print("Specify a value for n.")
        exit(1)

    n = int(sys.argv[1])
    f = open("sys_rand.lin", "w")
    f.write(str(n))
    f.write("\n")
    coefficients = numpy.random.randn(n, n)
    
    for row in coefficients:
        for col in row:
            f.write(str(col) + " ")
        f.write("\n")

    f1 = open("sys_rand_expected.sol", "w")
    solutions = numpy.random.randn(1, n)

    for s in solutions[0]:
        f1.write(str(s) + " ")

    constants = [None] * n
    for row in range(n):
        sum = 0
        for col in range(n):
            sum = sum + coefficients[row][col] * solutions[0][col]
        constants[row] = sum

    for num in constants:
        f.write(str(num) + " ")
    
    f.close()
    f1.close()


if __name__ == '__main__':
    main()