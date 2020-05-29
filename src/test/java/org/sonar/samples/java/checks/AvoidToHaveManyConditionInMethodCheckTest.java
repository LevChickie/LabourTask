package org.sonar.samples.java.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class AvoidToHaveManyConditionInMethodCheckTest {
    @Test
    public void verify()
    {
        JavaCheckVerifier.verify("src/test/files/AvoidToHaveManyConditionInMethodCheck.java",new AvoidToHaveManyConditionInMethodRule());
    }
}
