package org.sonar.samples.php.checks;

import com.sun.source.tree.MethodTree;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.php.checks.utils.CheckUtils;
import org.sonar.php.metrics.LineVisitor;
import org.sonar.php.tree.visitors.PHPCheckContext;
import org.sonar.plugins.php.api.tree.Tree;
import org.sonar.plugins.php.api.tree.declaration.FunctionTree;
import org.sonar.plugins.php.api.visitors.PHPSubscriptionCheck;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
@Rule(
        key = AvoidTooManyConditionsInMethodCheck.KEY,
        priority = Priority.MAJOR,
        name = "Too many condition in one method should be avoided",
        tags = {"convention"})
public class AvoidTooManyConditionsInMethodCheck extends PHPSubscriptionCheck {
    private static final int DEFAULT_MAXIMUM_CONDITION_METHOD = 3;
    public static final String KEY = "S4";
    @RuleProperty(
            key = "maximumConditionNumber", description = "Allowed maximum number of conditions",
            defaultValue = ""+ DEFAULT_MAXIMUM_CONDITION_METHOD
    )
    int maximumConditionNumber = DEFAULT_MAXIMUM_CONDITION_METHOD;
    @Override
    public List<Tree.Kind> nodesToVisit() {
        return CheckUtils.FUNCTION_KINDS;
    }

    @Override
    public void visitNode(Tree tree) {
        FunctionTree methodTree = (FunctionTree)tree;
        List<String> lines = context().getPhpFile().contents().lines().collect(Collectors.toList());
        String line;
        int counter=0;
        boolean errorFound = false;
        int methodStart=0;
        int methodEnd = 0;
        for(int i=methodStart;i<methodEnd;i++)
        {
            line = lines.get(i);
            if(line.contains("if(")||line.contains("switch("))
            {
                counter++;
            }
            if(counter>=maximumConditionNumber&&!errorFound)
            {
                context().newLineIssue(this, LineVisitor.linesOfCode(methodTree),"Too many condition in one method should be avoided");
                errorFound=true;
            }
        }
    }
}
