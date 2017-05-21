def scope_test():
    def do_local():
        spam = "local spam"

    def do_nonlocal():
        nonlocal spam
        spam = "nonlocal spam"

    def do_global():
        global spam
        spam = "global spam"

    spam = "test spam"
    do_local()
    print("After local assignment:", spam)
    do_nonlocal()
    print("After nonlocal assignment:", spam)
    do_global()
    print("After global assignment:", spam)

scope_test()
print("In global scope:", spam)

print('\n\n\n\n\n')

class Dog:
    print('declaring `Dog` class')
    tricks = []             # mistaken use of a class variable

    print('declaring `tricks` as a class variable')

    def __init__(self, name):
        self.name = name
        print('Creating the instance '+self.name)

    def add_trick(self, trick):
        self.tricks.append(trick)
        print('Calling instance: '+self.name+' and adding the trick: '+trick+' to the class array instead of the instance')

d = Dog('Fido')
e = Dog('Buddy')
d.add_trick('roll over')
e.add_trick('play dead')
print('When added on class scope, all instances will share the same tricks:')
print(d.tricks)
