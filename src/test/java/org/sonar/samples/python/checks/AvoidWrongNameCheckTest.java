package org.sonar.samples.python.checks;

import org.junit.Test;
import org.sonar.python.checks.utils.PythonCheckVerifier;

public class AvoidWrongNameCheckTest {
    @Test
    public void test() {
        AvoidWrongNameCheck check = new AvoidWrongNameCheck();
        PythonCheckVerifier.verify("src/test/resources/checks/AvoidWrongNameCheck.py", check);
    }
}
