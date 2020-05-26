# - - - - - - - - - - - - - - - -  - - Encryptor - - - - - - - - - - - - - - - - - -
from PasswordGenerator import * 
import random
import os

from tkinter import *


class GUIEncryptor:
    def __init__(self):
        self.once = False
        self.root = Tk()

    def generateMessage(self):
        self.label_1 = Text(master=self.root, height=8,
                            width=30, wrap=WORD, font=("Times", 20, "italic"))
        self.label_1.grid(row=0, column=0)
        self.label_1.insert(
            END, "Hello!\nThis is an Encryptor please type in a sequence of characters which you want to be encrypted")
        self.label_1.config(state=DISABLED)


    def deleteTextInEntry_1(self, event):
        self.entry_1.delete(0, len("Insert your password seed here"))

    def generateEntry_1(self):
        self.entry_1 = Entry(self.root, width=30)
        self.entry_1.grid(row=0, column=1)

        self.entry_1.insert(1, "Insert the password here")
        if not (self.once):
            self.entry_1.bind("<Button-1>", self.deleteTextInEntry_1)
            self.once = True



    def generateGUI(self):
        self.root.title("My Encryptor")
        frame = Frame(self.root, width=400, height=350)
        frame.grid()
        self.generateMessage()
        self.generateEntry_1()
        
        self.root.mainloop()

gui = GUIEncryptor()
gui.generateGUI()
