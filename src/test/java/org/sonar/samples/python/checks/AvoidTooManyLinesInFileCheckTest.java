package org.sonar.samples.python.checks;

import org.junit.Test;
import org.sonar.python.checks.AvoidTooManyLinesInFileCheck;
import org.sonar.python.checks.utils.PythonCheckVerifier;

public class AvoidTooManyLinesInFileCheckTest {
    private AvoidTooManyLinesInFileCheck check = new AvoidTooManyLinesInFileCheck();

    @Test
    public void test_negative() {
        check.maximum = 30;
        PythonCheckVerifier.verify("src/test/resources/checks/AvoidTooManyLinesInFileCheck.py", check);
    }
}
