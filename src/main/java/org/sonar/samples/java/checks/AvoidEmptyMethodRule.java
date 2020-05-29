package org.sonar.samples.java.checks;

import com.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.java.model.ModifiersUtils;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.*;

import java.util.Arrays;
import java.util.List;

@Rule(key = "AvoidEmptyMethodRule",name = "AvoidEmptyMethod",
        description = "You should leave empty method. Fill it in or delete it",
        priority = Priority.MINOR, tags = "code-smell")
public class AvoidEmptyMethodRule extends IssuableSubscriptionVisitor {
    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.of(Tree.Kind.METHOD);
    }
    public void visitNode(Tree tree){
        MethodTree methodTree = (MethodTree) tree;
        if(methodTree.block().body().isEmpty() && !methodTree.symbol().isAbstract())
        {
            reportIssue(tree,"You should not leave empty method. Fill it in or delete it");
        }
    }
}
