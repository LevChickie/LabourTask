package org.sonar.samples.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.java.ast.visitors.CommentLinesVisitor;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import com.google.common.collect.ImmutableList;
import org.sonar.plugins.java.api.tree.VariableTree;

import java.io.*;
import java.text.MessageFormat;
import java.util.List;

@Rule (key = "AvoidCommentingCodePartsRule",name = "AvoidCommentingCodeParts",
        description = "You should not comment codes",
        priority = Priority.MAJOR, tags = "bug")
public class AvoidCommentingCodePartsRule extends IssuableSubscriptionVisitor {
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
        boolean comment=false;
        int indexCommentStart;
        for(int i=0;i<lines.size();i++)
        {
            line = lines.get(i);
            if(line.contains("/*"))
            {
                comment = true;
            }
            if(line.contains("//"))
            {
                indexCommentStart = line.indexOf("//");
                for(int j = indexCommentStart; j<line.length();j++)
                {
                    if(line.charAt(j)=='='||line.charAt(j)==';')
                    {
                        addIssue(i+1, MessageFormat.format(
                                "You should not comment codes",
                                line.length()));
                    }
                }
            }
            if(comment)
            {
                for(int j = 0; j<line.length();j++)
                {
                    if(line.charAt(j)=='='||line.charAt(j)==';')
                    {
                        addIssue(i + 1, MessageFormat.format(
                                "You should not comment codes",
                                line.length()));
                    }
                }
            }
            if (line.contains("/*"))
            {
                comment = false;
            }

        }
    }
}
