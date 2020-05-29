package org.sonar.python.checks;

import java.text.MessageFormat;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.plugins.python.api.PythonSubscriptionCheck;
import org.sonar.plugins.python.api.tree.Tree;

@Rule(
        key = "AvoidTooManyLinesInFileCheck",
        priority = Priority.MINOR,
        name = "Avoid to have too many lines of code",
        description = "desc")
public class AvoidTooManyLinesInFileCheck extends PythonSubscriptionCheck {
    public static final String RULE_KEY = "AvoidTooManyLinesInFileCheck";
    private static final int DEFAULT = 300;
    private static final String MESSAGE = "File \"{0}\" has {1} lines, which is greater than {2} authorized. Split it into smaller files.";

    @RuleProperty(
            key = "maximum",
            description = "Maximum authorized lines in a file",
            defaultValue = "" + DEFAULT)
    public int maximum = DEFAULT;

    @Override
    public void initialize(Context context) {
        context.registerSyntaxNodeConsumer(Tree.Kind.FILE_INPUT, fileInput -> {
            int line = fileInput.syntaxNode().lastToken().line();
            if (line > maximum) {
                fileInput.addFileIssue(MessageFormat.format(MESSAGE, fileInput.pythonFile().fileName(), line, maximum));
            }
        });
    }
}