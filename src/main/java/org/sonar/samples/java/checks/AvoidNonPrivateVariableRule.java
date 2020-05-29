package org.sonar.samples.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;

@Rule(key = "AvoidNonPrivateVariableRule",name = "AvoidNonPrivateVariable",description = "Class should not have non-private variable",priority = Priority.MAJOR, tags = "bug")
public class AvoidNonPrivateVariableRule extends BaseTreeVisitor implements JavaFileScanner{
    private JavaFileScannerContext context;
    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }
    @Override
    public void visitVariable(VariableTree tree) {
        if (!tree.symbol().isPrivate())
            context.reportIssue(this, tree, String.format("Avoid using non private Variables in class"));
            super.visitVariable(tree);
    }
}


