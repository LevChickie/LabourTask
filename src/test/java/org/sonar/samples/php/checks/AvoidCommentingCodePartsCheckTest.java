package org.sonar.samples.php.checks;

import org.sonar.plugins.php.api.tests.PHPCheckTest;
import org.sonar.plugins.php.api.tests.PhpTestFile;

import java.io.File;

public class AvoidCommentingCodePartsCheckTest {
    public  void test(){
        PHPCheckTest.check(new AvoidCommentingCodePartsCheck(),new PhpTestFile(new File("src/test/resources/checks/avoidCommentingCodePartsCheck.php")));
    }
}
