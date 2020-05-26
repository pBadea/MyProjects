# - - - - - - - - - - - - - - - -  - - Password Generator - - - - - - - - - - - - - - - - - -
import random
import os

from tkinter import *

# function for  Cesear Cipher option 
def findLetterForRotation(k, letter):

    lower = "abcdefghijklmnopqrstuvwxyz"
    upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    shifts = k // 26

    # if letter is uppercase
    if ord(letter) >= 65 and ord(letter) <= 90:
        pos = ord(letter) - 65
        # if number Z is reached return to A
        actualPos = k - shifts * 26 + pos
        if actualPos > 25:
            actualPos = actualPos - 25
            return upper[actualPos - 1]
        else:
            return upper[actualPos]
    # else if letter is lowercase
    elif ord(letter) >= 97 and ord(letter) <= 122:
        pos = ord(letter) - 97
        # if number z is reached return to a
        actualPos = k - shifts * 26 + pos
        if actualPos > 25:
            actualPos = actualPos - 25
            return lower[actualPos - 1]
        else:
            return lower[actualPos]
    # elese if "letter" is number or symbol
    else:
            return letter


def caesarCipher(s, k):
    result = ""
    for letter in s:
        result += findLetterForRotation(k, letter)
    return result

# for a more fun print
def add_color(str, color):
    if color == "green":
        return '\033[92m' + str + '\033[0m'
    if color == "blue":
        return '\033[94m' + str + '\033[0m'
    if color == "red":
        return '\033[91m' + str + '\033[0m'
    if color == "yellow":
        return '\033[93m' + str + '\033[0m'
    if color == "pink":
        return '\033[95m' + str + '\033[0m'
    if color == "bold":
        return '\033[1m' + str + '\033[0m'
    if color == "underline":
        return '\033[4m' + str + '\033[0m'


def printRandomColor(str):
    randColor = ""
    for i in range(0, len(str)):
        randColor = random.choice(["green", "blue", "red", "yellow", "pink"])
        print(add_color(str[i], randColor), end="")
    print()

def passwordGenerator(Seed):

    word = Seed
    password = ""
    ambiguityString = ""
    for j in range(0, len(word)):
        if(word[j] in "aA"):
            ambiguityString = random.choice(
                ["4", "/-\\", "/_\\", "@", "/\\", "Д"])
        elif (word[j] in "bB"):
            ambiguityString = random.choice(
                ["8", "|3", "13", "|}", "|:", "|8", "18", "6", "|B", "|8", "lo", "|o", "j3", "ß"])
        elif (word[j] in "cC"):
            ambiguityString = random.choice(["<", "{", "[", "(", "©", "¢"])
        elif (word[j] in "dD"):
            ambiguityString = random.choice(["|)", "|}", "|]", "|>"])
        elif (word[j] in "eE"):
            ambiguityString = random.choice(["3", "£", "₤", "€"])
        elif (word[j] in "fF"):
            ambiguityString = random.choice(["|=", "ph", "|#", "|\""])
        elif (word[j] in "gG"):
            ambiguityString = random.choice(["[", "-", "[+", "6", "C-"])
        elif (word[j] in "hH"):
            ambiguityString = random.choice(
                ["4", "|-|", "[-]", "{-}", "}-{", "}{", "|=|", "[=]", "{=}", "/-/", "(-)", ")-(", ":-:", "I+I"])
        elif (word[j] in "iI"):
            ambiguityString = random.choice(["1", "|", "!", "9"])
        elif (word[j] in "jJ"):
            ambiguityString = random.choice(
                ["_|", "_/", "_7", "_)", "_]", "_}"])
        elif (word[j] in "kK"):
            ambiguityString = random.choice(["|<", "1<", "l<", "|{", "l{"])
        elif (word[j] in "lL"):
            ambiguityString = random.choice(["|_", "|", "1", "]["])
        elif (word[j] in "mM"):
            ambiguityString = random.choice(
                ["44", "|\\/|", "^^", "/\\/\\", "/X\\", "[]\\/][", "[]V[]", "][\\\\//][", "(V)", "//.", ".\\\\", "N\\"])
        elif (word[j] in "nN"):
            ambiguityString = random.choice(
                ["|\\|", "/\\/", "/V", "][\\\\][", "И"])
        elif (word[j] in "oO"):
            ambiguityString = random.choice(
                ["0", "()", "[]", "{}", "<>", "Ø", "oh"])
        elif (word[j] in "pP"):
            ambiguityString = random.choice(
                ["|o", "|O", "|>", "|*", "|°", "|D", "/o", "[]D", "|7"])
        elif (word[j] in "qQ"):
            ambiguityString = random.choice(["O_", "9", "(,)", "0", "kw"])
        elif (word[j] in "rR"):
            ambiguityString = random.choice(
                ["|2", "12", ".-", "|^", "l2", "Я", "®"])
        elif (word[j] in "sS"):
            ambiguityString = random.choice(["5", "$", "§"])
        elif (word[j] in "tT"):
            ambiguityString = random.choice(
                ["7", "+", "7`", "\'|\'", "`|`", "~|~", "-|-", "\'][\'"])
        elif (word[j] in "uU"):
            ambiguityString = random.choice(
                ["|_|", "\\_\\", "/_/", "\\_/", "(_)", "[_]", "{_}"])
        elif (word[j] in "vV"):
            ambiguityString = random.choice(["\\/"])
        elif (word[j] in "wW"):
            ambiguityString = random.choice(
                ["\\/\\/", "(/\\)", "\\^/", "|/\\|", "\\X/", "\\\\\'", "\'//", "VV", "\\_|_/", "\\\\//\\\\//", "Ш", "2u", "\\V/"])
        elif (word[j] in "xX"):
            ambiguityString = random.choice(["%", "*", "><", "}{", ")(", "Ж"])
        elif (word[j] in "yY"):
            ambiguityString = random.choice(["`/", "¥", "\\|/", "Ч"])
        elif (word[j] in "zZ"):
            ambiguityString = random.choice(["2", "5", "7_", ">_", "(/)"])
        elif (word[j] in " "):
            ambiguityString = "   "
        password += ambiguityString + " "

    return password


def randomPlantPasswordGenerator():

    # fl = open("Plants.txt","r")
    # print(os.getcwd())
    path = "C:\\Programming\\MyProjects\\PasswordGenerator\\Plants.txt"
    flowerlist = []
    with open(path, "r") as f:
        line = f.readline()
        while (line != ""):
            line = line.strip("\n")
            flowerlist.append(line)
            line = f.readline()
    for k in range(1, 21):
        i = random.randint(0, len(flowerlist)-1)
        print("Pasword nr.", k)
        print(flowerlist[i])
        printRandomColor(passwordGenerator(flowerlist[i]))


# seed = "Badea Patrick"
# seed = "abcdefghijklmnopqrstuvWxyz"
# print("Your seed was: ",seed)
# print(seed)
# for k in range (1,11):
#     i = random.randint(0,len(flowerlist)-1)
#     print("Pasword nr.",k)
#     print (passwordGenerator(seed))
#     # print(passwordGenerator(flowerlist[i]))

# randomPlantPasswordGenerator()
# seed = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
# print(seed)
# for k in range (1,100):
#     # print("Pasword nr.",k)
#     printRandomColor(passwordGenerator(seed))

# printRandomColor("AAAAAAAAAAAAAAA")


class GUIPasswdGen:
    def __init__(self):
        self.root = Tk()
        # for text in entrybox 2 dissapear
        self.once = False
        # for scale
        self.number = 1
    # generates the instructions of how to use the app

    def generateMessage(self):
        self.label_1 = Text(master=self.root, height=8,
                            width=30, wrap=WORD, font=("Times", 20, "italic"))
        self.label_1.grid(row=0, column=0)
        self.label_1.insert(
            END, "Hello!\nThis is a password generator please type in a sequence of characters which you want to be your seed and hit enter or just click on the generate random password button.\nDrag the scale if you want to generate more passwords")
        self.label_1.config(state=DISABLED)

    def generateRandomPlantPassword(self):
        path = "C:\\Programming\\MyProjects\\PasswordGenerator\\Plants.txt"
        flowerlist = []
        with open(path, "r") as f:
            line = f.readline()
            while (line != ""):
                line = line.strip("\n")
                flowerlist.append(line)
                line = f.readline()
    
        self.root_2 = Tk()
        self.root_2.title("Passwords")
        frame = Frame(self.root_2, width=300)
        frame.pack()
        scrollbar = Scrollbar(self.root_2)
        scrollbar.pack(side=RIGHT, fill=Y)
        self.passwordList = Listbox(self.root_2, yscrollcommand=scrollbar.set)
        for k in range(1, self.number + 1):
            i = random.randint(0, len(flowerlist)-1)
            self.passwordList.insert(END, "Pasword nr." + str(k))
            self.passwordList.insert(END, flowerlist[i])
            self.passwordList.insert(END, passwordGenerator(flowerlist[i]))
        self.passwordList.pack(side=LEFT, fill=BOTH, expand=True)
        scrollbar.config(command=self.passwordList.yview)

    # generates random password generator button

    def generateButton_1(self):
        self.button_1 = Button(
            self.root, text="Generate password", command=self.generateRandomPlantPassword)
        self.button_1.grid(row=1, column=1)

    # deletes the text "Insert your password seed here" at the first click on the entry
    def deleteTextInEntry_1(self, event):
        self.entry_1.delete(0, len("Insert your password seed here"))

    def getSeed(self, event):
        self.seed = self.entry_1.get()

    # updates self.number
    def updateNumber(self, event):
        self.number = self.scale_1.get()
        print(self.number)

    # generates scale
    def generateScale_1(self):
        self.scale_1 = Scale(self.root, from_=1, to=200, orient=HORIZONTAL)
        self.scale_1.grid(row=1, column=0)
        self.scale_1.bind("<ButtonRelease-1>", self.updateNumber)

    # generates a password depending on the user imput
    def generateSeedPassword(self, event):
        self.seed = self.entry_1.get()
        self.root_2 = Tk()
        self.root_2.title("Passwords")
        frame = Frame(self.root_2, width=300)
        frame.pack()
        scrollbar = Scrollbar(self.root_2)
        scrollbar.pack(side=RIGHT, fill=Y)
        self.passwordList = Listbox(self.root_2, yscrollcommand=scrollbar.set)
        self.passwordList.insert(END, self.seed)
        for k in range(1, self.number + 1):
            self.passwordList.insert(
                END, "Pasword nr." + str(k) + ":   " + passwordGenerator(self.seed))
        self.passwordList.pack(side=LEFT, fill=BOTH, expand=True)
        scrollbar.config(command=self.passwordList.yview)

    def generateEntry_1(self):
        self.entry_1 = Entry(self.root, width=30)
        self.entry_1.grid(row=0, column=1)

        self.entry_1.insert(1, "Insert your password seed here")
        if not (self.once):
            self.entry_1.bind("<Button-1>", self.deleteTextInEntry_1)
            self.once = True
        self.entry_1.bind("<Return>", self.generateSeedPassword)
        # entry_1.delete(0, END)

    # calls all functions and more
    def generateGUI(self):

        self.root.title("Password Generator")
        frame = Frame(self.root, width=400, height=350)
        frame.grid()
        self.generateMessage()
        self.generateEntry_1()
        self.generateButton_1()
        self.generateScale_1()

        self.root.mainloop()


P = GUIPasswdGen()
P.generateGUI()

# for i in range(0,500):
#     printRandomColor(passwordGenerator("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"))

