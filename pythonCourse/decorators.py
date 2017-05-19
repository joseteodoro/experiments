def greetings(name):
   return "Hello " + name

def call_fun(fun):
   name_ = "John Doe"
   return fun(name_)

print(call_fun(greetings))

class my_decorator(object):
    def __init__(self, f):
        print("inside my_decorator.__init__()")
        f() # Prove that function definition has completed

    def __call__(self):
        print("inside my_decorator.__call__()")

print("starting definition of aFunction")

@my_decorator
def aFunction():
    print("inside aFunction()")

print("Finished decorating aFunction()")

aFunction()
