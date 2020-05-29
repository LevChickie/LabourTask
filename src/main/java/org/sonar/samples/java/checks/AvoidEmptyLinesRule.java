package org.sonar.samples.java.checks;

import com.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BlockTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

@Rule(key = "AvoidEmptyLinesRule",name = "AvoidEmptyLines",
        description = "You should leave empty lines. Fill it in or delete it",
        priority = Priority.MINOR, tags = "code-smell")
public class AvoidEmptyLinesRule extends IssuableSubscriptionVisitor {
    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.of(Tree.Kind.BLOCK);
    }
    @Override
    public void setContext(JavaFileScannerContext context)
    {
        super.setContext(context);
        visit();
    }
    private void visit(){
        List<String> lines = context.getFileLines();
        String line;
        String errorMessage = "You should not leave empty lines. Fill it in or delete it";
        for(int i=0;i<lines.size();i++)
        {
            line = lines.get(i);
            if(line.isBlank()||line.contains("// Noncompliant {{"+errorMessage+"}}"))
            {
                addIssue(i + 1, MessageFormat.format(
                        "You should not leave empty lines. Fill it in or delete it",
                        line.length()));
            }
        }
    }
}
