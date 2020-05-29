package org.sonar.samples.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Rule (key = "AvoidLongLine",name = "AvoidLongLine",description = "A line should not be so long",
        priority = Priority.MINOR,tags = "code-smell")
public class AvoidLongLineRule extends IssuableSubscriptionVisitor {
    private static final int DEFAULT_MAXIMUM_LINE_LENGTH= 100;
    @RuleProperty(
               key = "lineLengthMaximum", description = "Allowed maximum length of lines",
               defaultValue = ""+DEFAULT_MAXIMUM_LINE_LENGTH
       )
    int lineLengthMaximum = DEFAULT_MAXIMUM_LINE_LENGTH;
    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.emptyList();
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
           if(line.length()>lineLengthMaximum)
           {
               addIssue(i + 1, MessageFormat.format(
                       "Your code should not have too long lines. Break them into shorter lines",
                       line.length()));
           }
        }
    }
}









