package org.sonar.samples.java.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class AvoidNonPrivateVariableCheckTest {

    @Test
    public void verify()
    {
        JavaCheckVerifier.verify("src/test/files/AvoidNonPrivateVariableCheck.java",new AvoidNonPrivateVariableRule());
    }
}
