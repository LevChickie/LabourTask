package org.sonar.samples.php.checks;

import com.google.common.collect.ImmutableList;
import com.sun.source.tree.MethodTree;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.php.checks.utils.CheckUtils;
import org.sonar.php.metrics.LineVisitor;
import org.sonar.php.tree.impl.lexical.InternalSyntaxToken;
import org.sonar.plugins.php.api.tree.Tree;
import org.sonar.plugins.php.api.tree.declaration.FunctionTree;
import org.sonar.plugins.php.api.visitors.PHPSubscriptionCheck;
import java.util.List;

@Rule(key="S5", priority = Priority.MAJOR,
        name = "A Method should not have more than 40 lines")
public class AvoidTooLongMethodsCheck extends PHPSubscriptionCheck {
    public static final String KEY = "S5";
    private int maximumLengthOfMethod=40;

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return CheckUtils.FUNCTION_KINDS;
    }

    @Override
    public void visitNode(Tree tree)
    {
        FunctionTree methodTree = (FunctionTree) tree;
        int numberOfLines;
        numberOfLines=LineVisitor.linesOfCode(methodTree);
        if(methodTree.body().is(Tree.Kind.BLOCK)){numberOfLines=0;}
        if(numberOfLines>maximumLengthOfMethod)
                context().newLineIssue(this, LineVisitor.linesOfCode(methodTree),"A Method should not have more than 40 lines");
        super.visitNode(tree);
    }
}
