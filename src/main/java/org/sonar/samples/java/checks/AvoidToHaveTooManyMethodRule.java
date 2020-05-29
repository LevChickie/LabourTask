package org.sonar.samples.java.checks;


import com.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.List;

@Rule (key="AvoidToHaveTooManyMethodRule", name="AvoidToHaveTooManyMethod",
        description = "A class should not have so many methods",priority = Priority.MINOR, tags = "code-smell")
public class AvoidToHaveTooManyMethodRule extends IssuableSubscriptionVisitor {
    private static final int DEFAULT_MAXIMUM_NUMBER_OF_METHODS = 15;
    @RuleProperty(
            key = "maximumNumberOfMethods", description = "Allowed maximum number of methods",
            defaultValue = ""+ DEFAULT_MAXIMUM_NUMBER_OF_METHODS
    )
    int maximumNumberOfMethods = DEFAULT_MAXIMUM_NUMBER_OF_METHODS;
    int counter;
    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.of(Tree.Kind.METHOD);
    }
    @Override
    public void visitNode(Tree tree) {
       counter += 1;
       if(counter>=maximumNumberOfMethods)
            reportIssue(tree, "A class should not have so many methods");
       super.visitNode(tree);
    }
}







