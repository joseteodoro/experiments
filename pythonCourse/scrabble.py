wordListFile = "english.txt"

wordlist = open(wordListFile).readlines()
wordlist = [word.lower().strip() for word in wordlist]
