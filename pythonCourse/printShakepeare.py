import scrabble
import shakespeare
import time

start = time.time()

wordset = set()
for word in scrabble.wordlist:
    wordset.add(word)

shakespeareset = set()
for word in shakespeare.wordlist:
    shakespeareset.add(word)

for word in shakespeareset:
    if word not in wordset:
        print(word)

end = time.time()
print("Time elapsed %.1f seconds" % (end - start))
