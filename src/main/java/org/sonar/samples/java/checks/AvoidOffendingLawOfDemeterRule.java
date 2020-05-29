package org.sonar.samples.java.checks;

import com.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.List;

@Rule(key = "AvoidOffendingLawOfDemeterRule",name = "AvoidOffendingLawOfDemeter",
        description = "Code should not violate Law of Demeter",priority = Priority.MINOR, tags = "code-smell")
public class AvoidOffendingLawOfDemeterRule extends IssuableSubscriptionVisitor {
    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.of(Tree.Kind.METHOD);
    }

    @Override
    public void visitNode(Tree tree)
    {
        MethodTree methodTree = (MethodTree) tree;
        reportIssue(tree, "Code should not violate Law of Demeter");

    }
}
