
sonnets = open("sonnets.txt")

wordset = set()
for line in sonnets.readlines():
    for word in line.split(" "):
        wordset.add(word)

file = open("sonnet-words.txt","w")
for word in wordset:
    file.write(word+"\n")
