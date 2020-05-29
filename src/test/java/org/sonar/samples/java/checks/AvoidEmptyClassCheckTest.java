package org.sonar.samples.java.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class AvoidEmptyClassCheckTest {

    @Test
    public void verify()
    {
        JavaCheckVerifier.verify("src/test/files/AvoidEmptyClassCheck.java",new AvoidEmptyClassRule());
    }
}
