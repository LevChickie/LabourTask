package org.sonar.samples.php.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.php.checks.utils.CheckUtils;
import org.sonar.plugins.php.api.tree.CompilationUnitTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

import java.util.List;
import java.util.stream.Stream;

@Rule(
        key = AvoidCommentingCodePartsCheck.KEY,
        priority = Priority.MAJOR,
        name = "You should not comment codes",
        tags = {"convention"})public class AvoidCommentingCodePartsCheck extends PHPVisitorCheck {
    private int numberOfLines=0;
    public static final String KEY = "S7";

    @Override
    public void visitCompilationUnit(CompilationUnitTree tree){
        boolean comment = false;
        int numberOfLines=0;
        Stream<String> lines= CheckUtils.lines(context().getPhpFile());
        lines.forEach(line->{
            if(line.contains("/*")){

            }
            else {
                int commentStart = 0;
                if (line.contains("//")) {
                    commentStart = line.indexOf("//");
                } else if (line.contains("#")) {
                    commentStart = line.indexOf("#");
                }
                if (line.substring(commentStart).contains("=") || line.substring(commentStart).contains(";")) {
                    context().newLineIssue(this,counter(), "You should not comment codes");
                }
            }
                });
    }
    public int counter()
    {
        numberOfLines++;
        return numberOfLines-1;
    }
}
