package org.sonar.samples.java.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class AvoidLongLineCheckTest {

    @Test
    public void verify()
    {
        JavaCheckVerifier.verify("src/test/files/AvoidLongLineCheck.java",new AvoidLongLineRule());
    }
}
