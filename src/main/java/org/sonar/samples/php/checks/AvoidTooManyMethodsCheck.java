package org.sonar.samples.php.checks;

import com.sun.source.tree.MethodTree;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.php.checks.utils.CheckUtils;
import org.sonar.php.checks.utils.FunctionUsageCheck;
import org.sonar.php.metrics.LineVisitor;
import org.sonar.plugins.php.api.tree.Tree;
import org.sonar.plugins.php.api.tree.declaration.FunctionTree;
import org.sonar.plugins.php.api.visitors.PHPSubscriptionCheck;

import java.util.List;
import java.util.stream.Collectors;

@Rule(
        key = AvoidTooManyMethodsCheck.KEY,
        priority = Priority.MAJOR,
        name = "Too many methods should be avoided",
        tags = {"convention"})
public class AvoidTooManyMethodsCheck extends PHPSubscriptionCheck {
    private static final int DEFAULT_MAXIMUM_METHOD_NUMBER= 15;
    private static int counter = 0;
    public static final String KEY = "S3";
    @RuleProperty(
            key = "maximumConditionNumber", description = "Allowed maximum number of conditions",
            defaultValue = ""+ DEFAULT_MAXIMUM_METHOD_NUMBER
    )
    int maximumConditionNumber = DEFAULT_MAXIMUM_METHOD_NUMBER;
    @Override
    public List<Tree.Kind> nodesToVisit() {
        return CheckUtils.FUNCTION_KINDS;
    }

    @Override
    public void visitNode(Tree tree) {
        FunctionTree methodTree= (FunctionTree)tree;
        counter++;
        if(counter>=DEFAULT_MAXIMUM_METHOD_NUMBER)
            {
                context().newLineIssue(this, LineVisitor.linesOfCode(methodTree),"Too many methods should be avoided");
            }
        }
}
