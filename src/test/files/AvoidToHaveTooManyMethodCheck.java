public class AvoidToHaveTooManyMethodCheck{
    public void methodA(){}// Compliant
    public void methodB(){}// Compliant
    public void methodC(){}// Compliant
    public void methodD(){}// Compliant
    public void methodE(){}// Compliant
    public void methodF(){}// Compliant
    public void methodG(){}// Compliant
    public void methodH(){}// Compliant
    public void methodI(){}// Compliant
    public void methodJ(){}// Compliant
    public void methodK(){}// Compliant
    public void methodL(){}// Compliant
    public void methodM(){}// Compliant
    public void methodN(){}// Compliant
    public void methodO(){}// Noncompliant {{A class should not have so many methods}}
    public void methodP(){}// Noncompliant {{A class should not have so many methods}}
}
