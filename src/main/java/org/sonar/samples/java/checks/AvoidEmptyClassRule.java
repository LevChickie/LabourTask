package org.sonar.samples.java.checks;

import com.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.java.model.ModifiersUtils;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

@Rule(key = "AvoidEmptyClassRule",name = "AvoidEmptyClass",
        description = "You should leave empty class. Fill it in or delete it",
        priority = Priority.MINOR, tags = "code-smell")
public class AvoidEmptyClassRule extends IssuableSubscriptionVisitor {
    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.of(Tree.Kind.METHOD);
    }
    @Override
    public void setContext(JavaFileScannerContext context) {
        super.setContext(context);
        visit();
    }
    public void visit() {
        List<String> lines = context.getFileLines();
        String line;
        boolean noError =true;
        int i;
        int j=0;
        for(i = 0;i<lines.size();i++) {
            line = lines.get(i);
            if (line.contains("class")) {
                j=i;
                while(j<lines.size()&& noError) {
                    j++;
                    line = lines.get(j);
                    if (!line.isBlank()&&checkLineCharacters(line)) {
                    break;
                    }
                    else if (line.contains("}")) {
                        addIssue(i + 1, MessageFormat.format(
                                "You should not leave empty class. Fill it in or delete it",
                               line.length()));
                        noError=false;
                    }
                }
            }
        }
    }
    private boolean testIfEmptyExcept(String line, char exception)
    {
        for(int i=0;i<line.length();i++)
        {
            if(line.charAt(i)==exception)
            {

            }
            else if(line.charAt(i)!=' ')
            {
                return false;
            }
        }
        return true;
    }

    private boolean checkLineCharacters(String line)
    {
        boolean onlyOpeningBrace=true;
        boolean onlyClosingBrace=true;
        onlyOpeningBrace = testIfEmptyExcept(line,'{');
        onlyClosingBrace = testIfEmptyExcept(line, '}');
        if(onlyClosingBrace==false && onlyOpeningBrace==false)
        {
            return true;
        }
        return false;
    }
}









