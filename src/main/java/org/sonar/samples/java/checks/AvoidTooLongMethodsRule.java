

package org.sonar.samples.java.checks;

import java.util.List;
import com.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.java.model.InternalSyntaxToken;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.*;

@Rule(key = "AvoidTooLongMethodsRule",name="AvoidTooLongMethods",description =
        "A Method should not have more than 40 lines",
        priority = Priority.MAJOR, tags = "code-smell")
public class AvoidTooLongMethodsRule extends IssuableSubscriptionVisitor {
    private static final int DEFAULT_MAXIMUM_LENGTH_OF_METHOD = 40;
    @RuleProperty(
                key = "maximumLengthOfMethod", description = "Allowed maximum length of methods",
            defaultValue = ""+ DEFAULT_MAXIMUM_LENGTH_OF_METHOD
    )
    int maximumLengthOfMethod = DEFAULT_MAXIMUM_LENGTH_OF_METHOD;
    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.of(Tree.Kind.METHOD);
    }
    @Override
    public void visitNode(Tree tree)
    {
        MethodTree methodTree = (MethodTree)tree;
        if((((InternalSyntaxToken)methodTree.block().closeBraceToken()).getLine()
                -((InternalSyntaxToken)methodTree.block().openBraceToken()).getLine() +1)>maximumLengthOfMethod)
            reportIssue(tree, "A Method should not have more than 40 lines");
        super.visitNode(tree);
    }
}
