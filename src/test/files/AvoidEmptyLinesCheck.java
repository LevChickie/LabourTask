public class AvoidEmptyLinesCheck
{
    public void wrongmethod()
    {
        int a;
        //Comment
        // Noncompliant {{You should not leave empty lines. Fill it in or delete it}}
        a = 2;
    }
}
