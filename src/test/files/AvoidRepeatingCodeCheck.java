public class AvoidRepeatingCodeCheck
{
    public void aMethod()
    {
    int a;
    }
    public void bMethod()
    {
    int a;// Noncompliant {{Class should not have repeated codes}}
    }
}
