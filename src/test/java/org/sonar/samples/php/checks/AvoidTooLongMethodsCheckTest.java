package org.sonar.samples.php.checks;

import org.sonar.plugins.php.api.tests.PHPCheckTest;
import org.sonar.plugins.php.api.tests.PhpTestFile;

import java.io.File;

public class AvoidTooLongMethodsCheckTest {
    public void test()
    {
        PHPCheckTest.check(new AvoidTooLongMethodsCheck(), new PhpTestFile(new File("src/test/resources/checks/avoidTooLongMethodsCheck.php")));
    }
}
