package org.sonar.samples.python.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.python.api.PythonSubscriptionCheck;
import org.sonar.plugins.python.api.tree.ForStatement;
import org.sonar.plugins.python.api.tree.Tree;

@Rule(
        key = CustomPythonSubscriptionCheck.RULE_KEY,
        priority = Priority.MINOR,
        name = "Python subscription visitor check",
        description = "desc")
public class CustomPythonSubscriptionCheck extends PythonSubscriptionCheck {

    public static final String RULE_KEY = "subscription";

    @Override
    public void initialize(Context context) {
        context.registerSyntaxNodeConsumer(Tree.Kind.FOR_STMT, ctx -> ctx.addIssue(((ForStatement) ctx.syntaxNode()).forKeyword(), "For statement."));
    }
}
