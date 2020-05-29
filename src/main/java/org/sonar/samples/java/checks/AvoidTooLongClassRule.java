package org.sonar.samples.java.checks;

import com.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.java.model.InternalSyntaxToken;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.BlockTree;
import org.sonar.plugins.java.api.tree.StatementTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.io.*;
import java.util.List;

@Rule(key="AvoidTooLongClassRule",name="AvoidTooLongClass",
        description = "A Class should not more than 150 lines",priority= Priority.MINOR, tags = "code-smell")
public class AvoidTooLongClassRule extends IssuableSubscriptionVisitor {
    private static final int DEFAULT_MAXIMUM_LENGTH_OF_CLASS = 150;
    @RuleProperty(
            key = "maximumLengthOfClass", description = "Allowed maximum length of class",
            defaultValue = ""+ DEFAULT_MAXIMUM_LENGTH_OF_CLASS
    )
    int maximumLengthOfClass = DEFAULT_MAXIMUM_LENGTH_OF_CLASS;
    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.of(Tree.Kind.BLOCK);   }
    @Override
    public void visitNode(Tree tree) {
        BlockTree blockTree= (BlockTree) tree;
        if((((InternalSyntaxToken)blockTree.closeBraceToken()).getLine()
              -((InternalSyntaxToken)blockTree.openBraceToken()).getLine()+1 )>maximumLengthOfClass) {
          reportIssue(tree, "A Class should not have more than 150 lines");
      }
        super.visitNode(tree);
    }
}








