def quadraticEquation(self): #Noncompliant@-1 {{File "AvoidTooManyLinesInFileCheck.py" has 31 lines, which is greater than 30 authorized. Split it into smaller files.}}
    b = 5
    a = 3
    c = 2
    print 'parameters are: '+a+','+b+','+c
    discriminant = exponentiation(b,2) - (4*a*c)
    print 'discriminant is: '+discriminant
    if discriminant>0:
        solution1 = -b - sqrtRootCalculator(discriminant,2)/(2*a)
        solution2 = -b + sqrtRootCalculator(discriminant,2)/(2*a)
        print 'solutions are: '+solution1+','+solution2
    elif discriminant==0:
         solution = -b / (2*a)
         print 'solution is: '+solution
    else:
        print 'complex number'
def sqrtRootCalculator(number1, number2):
    num = number1 ** (1/number2)
    return num
def exponentiation(number1, number2):
    result = number1 ** number2
    return result
def other(number):
    for i in number:
        result = number * i
        if result > 10:
            result = result-1
            print 'Result is high number'
        else:
            result = result+1
            print 'Result is not high number'