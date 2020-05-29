package org.sonar.samples.java.checks;

import com.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BlockTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

@Rule(key= "AvoidUsingGetTypeRule",name="AvoidUsingGetType", description = "A code should not contain GetType.",
        priority = Priority.MINOR, tags="code-smell")
public class AvoidUsingGetTypeRule extends IssuableSubscriptionVisitor {
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
        for(int i=0;i<lines.size();i++)
        {
            line = lines.get(i);
               if(line.contains("getType()")||lines.get(i).contains("GetType()"))
               {
                   addIssue(i + 1,MessageFormat.format(
                           "A code should not contain GetType.",
                           line.length()));
               }
        }
    }
}
