import scrabble

longest = ""
for word in scrabble.wordlist:
    isPalindrome = True;

    #for index in range(len(word)):
    #    if word[index] != word[-(index+1)]:
    #        isPalindrome = False
    if list(word) != list(reversed(word)):
        isPalindrome = False

    if isPalindrome and len(longest) < len(word):
        longest = word

print(longest)
