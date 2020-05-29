public class AvoidQueryAndCommandInSingleMethodCheck {
    double a;
    public double getA()
    {
        double a;
        a = Math.random(); // Noncompliant {{A method should not have queries and commands}};
        return a;
    }
    public double getAGood()
    {
        return a;
    }
}
