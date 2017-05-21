import random

def get_data():
    """Return 3 random integers between 0 and 9"""
    return random.sample(range(10), 3)


def consume():
    """Displays a running average across lists of integers sent to it.

    Since this function has the keyword `yield`, python make it able to
    frozen/continue its states.

    Each call for consumer.send(data) will unfreeze the function one
    more step"""
    running_sum = 0
    data_items_seen = 0

    while True:
        data = yield
        data_items_seen += len(data)
        running_sum += sum(data)
        print('The running data_items_seen is {}'.format(data_items_seen))
        print('The running average is {}'.format(running_sum / float(data_items_seen)))

def produce(consumer):
    """Produces a set of values and forwards them to the pre-defined consumer
    function

    Each time the next() function is called, this function produce a new set of
    data to be consumed by the function consumer.send(data)
    """

    while True:
        data = get_data()
        print('Produced {}'.format(data))
        consumer.send(data)
        yield

if __name__ == '__main__':
    consumer = consume()
    consumer.send(None)
    producer = produce(consumer)

    for _ in range(10):
        print('Producing...')
        """ call the producer 10 times """
        next(producer)
