package org.sonar.samples.java.checks;
import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class AvoidOffendingBoyScoutLawCheckTest {
    @Test
    public void verify()
    {
        JavaCheckVerifier.verify("src/test/files/AvoidOffendingBoyScoutLawCheck.java",new AvoidOffendingBoyScoutLawRule());
    }
}
