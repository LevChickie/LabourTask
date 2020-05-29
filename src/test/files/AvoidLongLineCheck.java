import java.util.List;

public class AvoidLongLineCheck
{
    public void wrongmethod()
    {
        String stringA="string";
        String strinbB="string";
        double calculation = strinbB.length()*strinbB.length()* Math.random()*10 + stringA.length()*Math.random()*10 + strinbB.length()*Math.random()*10 + Math.random()*10 * Math.max(stringA.length(),strinbB.length());// Noncompliant {{Your code should not have too long lines. Break them into shorter lines}}
    }
}
