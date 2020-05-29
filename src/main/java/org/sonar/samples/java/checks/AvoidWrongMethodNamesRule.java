package org.sonar.samples.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.VariableTree;

@Rule(key = "AvoidWrongMethodNamesRule",name = "AvoidWrongMethodNames",description = "Use names that reference to its purpose",priority = Priority.MINOR, tags = "bug")
public class AvoidWrongMethodNamesRule extends BaseTreeVisitor implements JavaFileScanner{
    private JavaFileScannerContext context;
    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }
    @Override
    public void visitMethod(MethodTree tree) {
        if ((tree.symbol().name().length()<3)||(tree.symbol().name().contains("my"+tree.symbol().returnType())))
            context.reportIssue(this, tree, String.format("Use names that reference to its purpose"));
        super.visitMethod(tree);
    }
}


