package org.sonar.python.checks;

import java.text.MessageFormat;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.plugins.python.api.PythonSubscriptionCheck;
import org.sonar.plugins.python.api.tree.Tree;


@Rule(
        key = "AvoidTooLongLineCheck",
        priority = Priority.MINOR,
        name = "AvoidTooLongLineCheck",
        description = "")
public class AvoidTooLongLineCheck extends PythonSubscriptionCheck {
    public static final String RULE_KEY = "AvoidTooLongLineCheck";
    private static final int DEFAULT_MAXIMUM_LINE_LENGTH = 120;

    @RuleProperty(
            key = "maximumLineLength",
            description = "The maximum authorized line length",
            defaultValue = "" + DEFAULT_MAXIMUM_LINE_LENGTH)
    public int maximumLineLength = DEFAULT_MAXIMUM_LINE_LENGTH;

    @Override
    public void initialize(Context context) {
        context.registerSyntaxNodeConsumer(Tree.Kind.FILE_INPUT, fileInput -> {
            String[] lines = fileInput.pythonFile().content().split("\r?\n|\r", -1);
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                if (line.length() > maximumLineLength) {
                    String message = MessageFormat.format("The line contains too many characters. Break it into separate lines, only {0,number,integer} character should be in one line."
                            , maximumLineLength);
                    fileInput.addLineIssue(message, i + 1);
                }
            }
        });
    }
}