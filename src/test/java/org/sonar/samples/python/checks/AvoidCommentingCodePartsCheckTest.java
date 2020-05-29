package org.sonar.samples.python.checks;

import org.junit.Test;
import org.sonar.python.checks.utils.PythonCheckVerifier;

public class AvoidCommentingCodePartsCheckTest { @Test
public void test() {
    AvoidCommentingCodePartsCheck check = new AvoidCommentingCodePartsCheck();
    PythonCheckVerifier.verify("src/test/resources/checks/AvoidCommentingCodePartsCheck.py", check);
}

}
