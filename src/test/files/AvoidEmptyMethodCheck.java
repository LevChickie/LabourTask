public class AvoidEmptyMethodCheck {
    public void wrongmethod()// Noncompliant {{You should not leave empty method. Fill it in or delete it}}
    {

    }
    public void goodmethod()// Compliant
    {
        int a;
    }
}
