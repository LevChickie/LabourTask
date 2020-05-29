package org.sonar.samples.php.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.php.checks.utils.CheckUtils;
import org.sonar.plugins.php.api.tree.CompilationUnitTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

import java.util.List;
import java.util.stream.Stream;

@Rule(
        key = AvoidLongLineCheck.KEY,
        priority = Priority.MAJOR,
        name = "Your code should not have too long lines. Break them into shorter lines",
        tags = {"convention"})
public class AvoidLongLineCheck extends PHPVisitorCheck {
    public static final String KEY = "S6";

    @Override
    public void visitCompilationUnit(CompilationUnitTree unitTree){
        Stream<String> lines = CheckUtils.lines(context().getPhpFile());

        int[] lineNumber={0}; //it needs a final number, we can't just put a normal counter there;
        lines.forEach(line-> {
            if(line.length()>100)
            {
                context().newLineIssue(this, lineNumber[0],"Your code should not have too long lines. Break them into shorter lines");
                lineNumber[0]= lineNumber[0]+1;
            }
        });
    }
}
