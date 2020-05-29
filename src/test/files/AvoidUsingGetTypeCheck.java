
import org.sonar.api.user.User;

import java.lang.reflect.Field;

public class AvoidUsingGetTypeCheck
{
    public void method() throws NoSuchFieldException {
        Field field = User.class.getField("Marks");
        field.getType();// Noncompliant {{A code should not contain GetType.}}
    }
}
