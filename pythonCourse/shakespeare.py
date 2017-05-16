wordListFile = "sonnet-words.txt"

wordlist = open(wordListFile).readlines()
wordlist = [word.lower().strip() for word in wordlist]
