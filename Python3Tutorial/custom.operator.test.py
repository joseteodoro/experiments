
class xnor_integer:

    def __init__(self, value):
        self.value = value
        print('creating the instance with value: %d' % value)

    def __add__(self, other):
        print('overriding the default add operator.')
        print('calling + will call this customized method.')
        print('%d - %d' % (self.value, other.value))
        return self.value - other.value

if __name__ == '__main__':
    xi1 = xnor_integer(1)
    xi2 = xnor_integer(3)
    print( xi1 + xi2 )
