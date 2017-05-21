import random

def generator():
    data = yield
    print('Inside generator with the value {}'.format(data))
    """ second yield call make get the generator exhausted """
    yield data

if __name__ == '__main__':
    generator = generator()
    data = generator.send(None)
    print('Outside with the value {}'.format(data))

    data = generator.send(3030)
    print('Outside with the value {}'.format(data))

    data = generator.send(3020)
    print('Outside with the value {}'.format(data))
