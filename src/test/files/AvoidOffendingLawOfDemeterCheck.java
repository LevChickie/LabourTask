public class AvoidOffendingLawOfDemeterCheck {
    class Motor{
        public void startEngine(){/*enginge starts*/}// Noncompliant {{Code should not violate Law of Demeter}}
    }
    public class Auto {
        Motor motor;
        public Auto(){motor = new Motor();}
    }
    public class Person {
        public void drive()// Noncompliant {{Code should not violate Law of Demeter}}
        {
            Auto auto = new Auto(); auto.motor.startEngine();
        }
    }
}
