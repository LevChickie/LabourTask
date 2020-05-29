package org.sonar.samples.java.checks;

import com.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.java.model.InternalSyntaxToken;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.text.MessageFormat;
import java.util.List;

@Rule (key = "AvoidToHaveManyConditionInMethodRule", name = "AvoidToHaveManyConditionInMethod",
        description = "A Method should not have so many conditions", priority = Priority.MAJOR, tags ="code-smell")
public class AvoidToHaveManyConditionInMethodRule extends IssuableSubscriptionVisitor {
    private static final int DEFAULT_MAXIMUM_CONDITION_METHOD = 3;
    @RuleProperty(
            key = "maximumConditionNumber", description = "Allowed maximum number of conditions",
            defaultValue = ""+ DEFAULT_MAXIMUM_CONDITION_METHOD
    )
    int maximumConditionNumber = DEFAULT_MAXIMUM_CONDITION_METHOD;
    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.of(Tree.Kind.METHOD);
    }

    @Override
    public void setContext(JavaFileScannerContext context)
    {
        super.setContext(context);
    }

    @Override
    public void visitNode(Tree tree){
        MethodTree methodTree = (MethodTree)tree;
        List<String> lines = context.getFileLines();
        String line;
        int counter=0;
        boolean errorFound = false;
        int methodStart=0;
        int methodEnd = 0;
        methodEnd = ((InternalSyntaxToken)methodTree.block().closeBraceToken()).getLine();
        methodStart = ((InternalSyntaxToken)methodTree.block().openBraceToken()).getLine();
        for(int i=methodStart;i<methodEnd;i++)
        {
            line = lines.get(i);
            if(line.contains("if(")||line.contains("switch("))
            {
                counter++;
            }
            if(counter>=maximumConditionNumber&&!errorFound)
            {
                addIssue(i + 1, MessageFormat.format(
                        "A Method should not have so many conditions",
                        line.length()));
                errorFound=true;
            }
        }
    }

}
