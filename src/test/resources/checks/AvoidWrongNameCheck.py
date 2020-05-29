class A:
    def myFun(self): #Noncompliant  {{Rename method "myFun" to match the regular expression ^[a-z_][a-z0-9_]{2,}$.}}
        {}
    def a(self): #Noncompliant  {{Rename method "a" to match the regular expression ^[a-z_][a-z0-9_]{2,}$.}}
        {}