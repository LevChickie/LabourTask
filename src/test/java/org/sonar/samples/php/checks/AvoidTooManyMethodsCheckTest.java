package org.sonar.samples.php.checks;

import org.sonar.plugins.php.api.tests.PHPCheckTest;
import org.sonar.plugins.php.api.tests.PhpTestFile;

import java.io.File;

public class AvoidTooManyMethodsCheckTest {
    public void test(){
        PHPCheckTest.check(new AvoidTooManyMethodsCheck(),new PhpTestFile(new File("src/test/resources/checks/avoidTooManyMethodsCheck.php")));
    }
}
