package org.sonar.samples.php.checks;

import org.sonar.plugins.php.api.tests.PHPCheckTest;
import org.sonar.plugins.php.api.tests.PhpTestFile;

import java.io.File;

public class AvoidLongLineCheckTest {

    public void test(){
        PHPCheckTest.check(new AvoidLongLineCheck(),new PhpTestFile(new File("src/test/resources/checks/avoidLongLineCheck.php")));
    }
}
