package org.sonar.samples.java.checks;

import com.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.java.model.InternalSyntaxToken;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BlockTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.VariableTree;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

@Rule(key="AvoidQueryAndCommandInSingleMethodRule",name="AvoidQueryAndCommandInSingleMethod",
        description = "A method should not have queries and commands",priority = Priority.MAJOR,tags="code-smell")
public class AvoidQueryAndCommandInSingleMethodRule extends IssuableSubscriptionVisitor {
    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.of(Tree.Kind.METHOD);
    }

    @Override
    public void setContext(JavaFileScannerContext context) {
        super.setContext(context);
    }

    public void visitNode(Tree tree) {
        MethodTree methodTree = (MethodTree) tree;
        List<String> lines = context.getFileLines();
        boolean methodContent = false;
        String line;
        int methodStart=0;
        int methodEnd = 0;
        if (methodTree.symbol().name().contains("get") ||
                methodTree.symbol().name().contains("Get")) {
            methodStart = ((InternalSyntaxToken)methodTree.block().openBraceToken()).getLine();
            methodEnd = ((InternalSyntaxToken)methodTree.block().closeBraceToken()).getLine();
            for (int i = methodStart; i < methodEnd; i++) {
                line = lines.get(i);
                if (line.contains("(") && line.contains(")")&& line.contains(";")) {
                    addIssue(i + 1, MessageFormat.format(
                            "A method should not have queries and commands",
                            line.length()));
                }
            }
        }
        super.visitNode(tree);
    }
}
