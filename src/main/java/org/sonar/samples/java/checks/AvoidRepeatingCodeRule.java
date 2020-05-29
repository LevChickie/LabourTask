package org.sonar.samples.java.checks;

import com.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Rule(key = "AvoidRepeatingCodeRule", name = "AvoidRepeatingCode",
        description = "Class should not have repeated codes", priority = Priority.MINOR, tags ="code-smell")
public class AvoidRepeatingCodeRule extends IssuableSubscriptionVisitor {

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.of(Tree.Kind.METHOD);
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
        String errorMessage="Class should not have repeated codes";
        List<String> alreadyHad= new ArrayList<>();
        for(int i=0;i<lines.size();i++)
        {
            line = lines.get(i);
            if(alreadyHad.contains(line))
            {
                addIssue(i + 1, MessageFormat.format(
                        "Class should not have repeated codes",
                        line.length()));
            }
            else{
                for(int j=0; j<alreadyHad.size();j++)
                {
                    if (line.contains(alreadyHad.get(j)))
                    {
                        addIssue(i + 1, MessageFormat.format(
                                "Class should not have repeated codes",
                                line.length()));
                    }
                }
            }
            if(!line.contains("{") && !line.contains("}") )
            {
                alreadyHad.add(line);
            }
        }
    }
}
