package org.sonar.samples.python.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.plugins.python.api.tree.FunctionDef;
import org.sonar.plugins.python.api.tree.Name;
import org.sonar.plugins.python.api.tree.Tree;
import org.sonar.python.checks.AbstractNameCheck;
import org.sonar.python.checks.ClassNameCheck;
import static org.sonar.python.checks.CheckUtils.classHasInheritance;
import static org.sonar.python.checks.CheckUtils.getParentClassDef;

@Rule(
        key = "AvoidWrongNameCheck",
        priority = Priority.MINOR,
        name = "Python subscription visitor check",
        description = "desc")
public class AvoidWrongNameCheck extends AbstractNameCheck {
    private static final String DEFAULT = "^[a-z_][a-z0-9_]{2,}$";
    private static final String MESSAGE = "Rename %s \"%s\" to match the regular expression %s.";
    public static final String RULE_KEY = "AvoidWrongNameCheck";

    @RuleProperty(
            key = "format",
            description = "Regular expression used to check the class names against",
            defaultValue = "" + DEFAULT)
    public String format = DEFAULT;

    @Override
    protected String format() {
        return format;
    }

    @Override
    public void initialize(Context context) {
        context.registerSyntaxNodeConsumer(
                Tree.Kind.FUNCDEF, fileContext -> {
                    FunctionDef pyFunctionDefTree = (FunctionDef) fileContext.syntaxNode();
                    Name functionNameTree = pyFunctionDefTree.name();
                    pyFunctionDefTree.body().statements();
                    String name = functionNameTree.name();
                    if (name.contains("my")||name.length()<3) {
                        String message = String.format(MESSAGE, typeName(), name, this.format);
                        fileContext.addIssue(functionNameTree, message);
                    } }
        );
    }

    public String typeName() {
        return "method";
    }
    public boolean shouldCheckFunctionDeclaration(FunctionDef pyFunctionDefTree){
        return pyFunctionDefTree.isMethodDefinition() && !classHasInheritance(getParentClassDef(pyFunctionDefTree));
    }

}
