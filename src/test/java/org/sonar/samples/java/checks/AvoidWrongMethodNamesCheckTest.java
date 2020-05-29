package org.sonar.samples.java.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class AvoidWrongMethodNamesCheckTest {

    @Test
    public void verify()
    {
        JavaCheckVerifier.verify("src/test/files/AvoidWrongMethodNamesCheck.java",new AvoidWrongMethodNamesRule());
    }
}
