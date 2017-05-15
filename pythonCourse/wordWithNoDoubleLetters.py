import scrabble
import string
for letter in string.ascii_lowercase:
    exists = False
    print("Checking "+letter)
    for word in scrabble.wordlist:
        if letter * 2 in word:
            exists = True
            break

    if not exists:
        print("There is not English word with double "+ letter)
