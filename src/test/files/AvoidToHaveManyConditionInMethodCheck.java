public class AvoidToHaveManyConditionInMethodCheck {

    public void methodTestIncorrect()
    {
        int testValueA = 1;
        int testValueB = 4;
        if(testValueA==0)
        {

        }
        switch(testValueA)
        {
            case 1: ;
            case 2: ;
            case 3: ;
        }
        if(testValueB ==4)// Noncompliant {{A Method should not have so many conditions}}
        {

        }
    }




}
