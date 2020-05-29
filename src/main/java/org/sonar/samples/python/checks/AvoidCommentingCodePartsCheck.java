package org.sonar.samples.python.checks;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.python.api.PythonSubscriptionCheck;
import org.sonar.plugins.python.api.SubscriptionCheck;
import org.sonar.plugins.python.api.tree.Tree;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.text.MessageFormat;

@Rule(
        key = "AvoidCommentingCodePartsCheck",
        priority = Priority.MINOR,
        name = "Python subscription visitor check",
        description = "desc")
public class AvoidCommentingCodePartsCheck extends PythonSubscriptionCheck {
    public static final String RULE_KEY = "AvoidCommentingCodePartsCheck";

    @Override
    public void initialize(SubscriptionCheck.Context context) {
        context.registerSyntaxNodeConsumer(Tree.Kind.FILE_INPUT, fileInput -> {
           Stream<String> contentLine = fileInput.pythonFile().content().lines();
           List<String> content = contentLine.collect(Collectors.toList());
           String line;
           boolean isComment = false;
           int commentStart=0;
           for(int i=0; i<content.size();i++)
           {
               line=content.get(i);
               if(isComment){
                   commentStart=0;
                   if(isIssue(line,commentStart)){
                       fileInput.addLineIssue("Don't comment code parts",i);
                   }
               }
               else {
                   if(line.contains("\"\"\"")){
                    isComment=true;
                    commentStart=line.indexOf("\"\"\"");
                       if(isIssue(line,commentStart)){
                           fileInput.addLineIssue("Don't comment code parts",i);
                       }
                    }
                    else if (line.contains("#")) {
                        commentStart = line.indexOf("#");
                       if(isIssue(line,commentStart)){
                           fileInput.addLineIssue("Don't comment code parts",i);
                       }
                    }
               }
           }
           }
        );
    }

    public boolean isIssue(String line,int commentStart)
    {
        if (line.substring(commentStart).contains("=") || line.substring(commentStart).contains(";")) {
        return true;
        }
        return false;
    }
}
