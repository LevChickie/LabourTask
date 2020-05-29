package org.sonar.samples.java.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class AvoidEmptyLinesCheckTest {

    @Test
    public void verify()
    {
        JavaCheckVerifier.verify("src/test/files/AvoidEmptyLinesCheck.java",new AvoidEmptyLinesRule());
    }
}
