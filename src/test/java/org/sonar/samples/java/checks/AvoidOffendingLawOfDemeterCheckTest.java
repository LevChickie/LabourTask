package org.sonar.samples.java.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class AvoidOffendingLawOfDemeterCheckTest {
    @Test
    public void verify()
    {
        JavaCheckVerifier.verify("src/test/files/AvoidOffendingLawOfDemeterCheck.java",
                new AvoidOffendingLawOfDemeterRule());
    }


}
