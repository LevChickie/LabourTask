using System;
using System.Collections.Generic;
using System.Collections.Immutable;
using System.Linq;
using System.Threading;
using Microsoft.CodeAnalysis;
using Microsoft.CodeAnalysis.CSharp;
using Microsoft.CodeAnalysis.CSharp.Syntax;
using Microsoft.CodeAnalysis.Diagnostics;

namespace Analyzer1
{
    [DiagnosticAnalyzer(LanguageNames.CSharp)]
    public class Analyzer1Analyzer : DiagnosticAnalyzer
    {
        public const string DiagnosticId = "Analyzer1";

        // You can change these strings in the Resources.resx file. If you do not want your analyzer to be localize-able, you can use regular strings for Title and MessageFormat.
        // See https://github.com/dotnet/roslyn/blob/master/docs/analyzers/Localizing%20Analyzers.md for more on localization
        private static readonly LocalizableString Title = new LocalizableResourceString(nameof(Resources.AnalyzerTitle), Resources.ResourceManager, typeof(Resources));
        private static readonly LocalizableString MakePrivateMessage = "NEED PRIVACY";
        private static readonly LocalizableString Description = new LocalizableResourceString(nameof(Resources.AnalyzerDescription), Resources.ResourceManager, typeof(Resources));
        private const string Category = "Naming";

        private static readonly LocalizableString haveGoodNameTitle = new LocalizableResourceString(nameof(Resources.AnalyzerTitle), Resources.ResourceManager, typeof(Resources));
        private static readonly LocalizableString haveGoodNameMessage = "Name is incorrect";

        private static readonly LocalizableString numberOfMethodTitle = new LocalizableResourceString(nameof(Resources.AnalyzerTitle), Resources.ResourceManager, typeof(Resources));
        private static readonly LocalizableString numberOfMethodMessage = "Too many methods. Break them in different classes";

        private static readonly LocalizableString tooLongMethodMessage = "Too long method. Try to break functionality in smaller parts";
        private static readonly LocalizableString emptyMethodMessage = "Empty method. You could delete it.";
        private static readonly LocalizableString tooLongLineMessage = "Too long line, break it in more lines.";
        private static readonly LocalizableString haveGoodNameMethodMessage = "Method name is incorrect. Please consider using a name that refers to the objective";
        private static readonly LocalizableString haveFewConditionMessage = "Too many conditions. Break them into separate functions";

        private static DiagnosticDescriptor makePrivateRule = new DiagnosticDescriptor(DiagnosticId, Title, MakePrivateMessage,Category, DiagnosticSeverity.Warning, isEnabledByDefault: true, description: Description);
        private static DiagnosticDescriptor haveGoodNameRule = new DiagnosticDescriptor(DiagnosticId, Title, haveGoodNameMessage, Category, DiagnosticSeverity.Warning, isEnabledByDefault: true, description: Description);
        private static DiagnosticDescriptor tooLongMethodRule = new DiagnosticDescriptor(DiagnosticId, Title, tooLongMethodMessage, Category, DiagnosticSeverity.Warning, isEnabledByDefault: true, description: Description);
        private static DiagnosticDescriptor emptyMethodRule = new DiagnosticDescriptor(DiagnosticId, Title, emptyMethodMessage, Category, DiagnosticSeverity.Warning, isEnabledByDefault: true, description: Description);
        private static DiagnosticDescriptor numberOfMethodRule = new DiagnosticDescriptor(DiagnosticId, numberOfMethodTitle, numberOfMethodMessage, Category, DiagnosticSeverity.Warning, isEnabledByDefault: true, description: Description);
        private static DiagnosticDescriptor tooLongLineRule = new DiagnosticDescriptor(DiagnosticId, numberOfMethodTitle, tooLongLineMessage, Category, DiagnosticSeverity.Warning, isEnabledByDefault: true, description: Description);
        private static DiagnosticDescriptor haveGoodNameMethodRule = new DiagnosticDescriptor(DiagnosticId, Title, haveGoodNameMethodMessage, Category, DiagnosticSeverity.Warning, isEnabledByDefault: true, description: Description);
        private static DiagnosticDescriptor haveFewConditionRule = new DiagnosticDescriptor(DiagnosticId, Title, haveFewConditionMessage, Category, DiagnosticSeverity.Warning, isEnabledByDefault: true, description: Description);


        public override ImmutableArray<DiagnosticDescriptor> SupportedDiagnostics { get { return ImmutableArray.Create
                    (makePrivateRule,haveGoodNameRule,numberOfMethodRule,tooLongMethodRule,emptyMethodRule,tooLongLineRule,haveGoodNameMethodRule,haveFewConditionRule);
            }
        }
       

        public override void Initialize(AnalysisContext context)
        {
            // TODO: Consider registering other actions that act on syntax instead of or in addition to symbols
            context.RegisterSyntaxNodeAction<SyntaxKind>(makePrivateVariable, SyntaxKind.VariableDeclaration);
            context.RegisterSyntaxNodeAction<SyntaxKind>(haveGoodNamedVariable, SyntaxKind.VariableDeclaration);
            context.RegisterSyntaxTreeAction(numberOfMethod);
            context.RegisterSyntaxTreeAction(tooLongMethod);
            context.RegisterSyntaxTreeAction(tooLongLine);
            context.RegisterSyntaxTreeAction(emptyMethod);
            context.RegisterSyntaxTreeAction(haveGoodNamedMethod);
            context.RegisterSyntaxTreeAction(haveFewConditions);
        }

        private void numberOfMethod(SyntaxTreeAnalysisContext obj)
        {
            SyntaxTree tree = obj.Tree;
            IEnumerable<MethodDeclarationSyntax> methods = tree.GetRoot()
           .DescendantNodes()
           .OfType<MethodDeclarationSyntax>().ToList();
            int counter = 0;
            foreach (var method in methods)
            {
                counter++;
                if (counter > 15)
                {
                    var diagnostic = Diagnostic.Create(numberOfMethodRule, method.GetLocation(), method);
                    obj.ReportDiagnostic(diagnostic);
                }
            }
           
        }


        private void tooLongMethod(SyntaxTreeAnalysisContext obj)
        {
            SyntaxTree tree = obj.Tree;
            IEnumerable<MethodDeclarationSyntax> methods = tree.GetRoot()
           .DescendantNodes()
           .OfType<MethodDeclarationSyntax>().ToList();        
            foreach (var method in methods)
            {
              var  linespan = method.SyntaxTree.GetMappedLineSpan(method.Span);
              var  lines = linespan.EndLinePosition.Line - linespan.StartLinePosition.Line + 1;
                if(lines >30)
                {
                    var diagnostic = Diagnostic.Create(tooLongMethodRule, method.GetLocation(), method);
                    obj.ReportDiagnostic(diagnostic);
                }
            }
        }

        private void emptyMethod(SyntaxTreeAnalysisContext obj)
        {
            SyntaxTree tree = obj.Tree;
            IEnumerable<MethodDeclarationSyntax> methods = tree.GetRoot()
           .DescendantNodes()
           .OfType<MethodDeclarationSyntax>().ToList();
            foreach (var method in methods)
            {
                if (method.Body != null)
                {
                    if (method.Body.Statements.Count == 0)
                    {
                        var diagnostic = Diagnostic.Create(emptyMethodRule, method.GetLocation(), method);
                        obj.ReportDiagnostic(diagnostic);
                    }
                }
            }
        }
        private void tooLongLine(SyntaxTreeAnalysisContext obj)
        {
            SyntaxTree tree = obj.Tree;
            IEnumerable<MethodDeclarationSyntax> methods = tree.GetRoot()
           .DescendantNodes()
           .OfType<MethodDeclarationSyntax>().ToList();
            foreach (var method in methods)
            { 
                if (method.Body!=null) { 
                for (int i = 0; i < method.Body.Statements.Count; i++)
                {
                    if (method.Body.Statements.ElementAt(i).Span.Length > 100)
                    {
                        var diagnostic = Diagnostic.Create(tooLongLineRule, method.Body.Statements.ElementAt(i).GetLocation(), method.Body.Statements.ElementAt(i));
                        obj.ReportDiagnostic(diagnostic);
                    }
                }}
            }
        }

        private static void makePrivateVariable(SyntaxNodeAnalysisContext context)
        {
               var namedTypeSymbol = context.ContainingSymbol;
               if (namedTypeSymbol.DeclaredAccessibility==Accessibility.Public)
               {
                   var diagnostic = Diagnostic.Create(makePrivateRule, namedTypeSymbol.Locations[0], namedTypeSymbol.Name);
                   context.ReportDiagnostic(diagnostic);
               }
        }

        private static void haveGoodNamedVariable(SyntaxNodeAnalysisContext context)
        {
            var namedTypeSymbol = context.ContainingSymbol;
            if(namedTypeSymbol.Name.Contains("my"))
            {
                var diagnostic = Diagnostic.Create(haveGoodNameRule, namedTypeSymbol.Locations[0], namedTypeSymbol.Name);
                context.ReportDiagnostic(diagnostic);
            }
        }

        private static void haveGoodNamedMethod(SyntaxTreeAnalysisContext obj)
        {
             SyntaxTree tree = obj.Tree;
            IEnumerable<MethodDeclarationSyntax> methods = tree.GetRoot()
           .DescendantNodes()
           .OfType<MethodDeclarationSyntax>().ToList();
            foreach (var method in methods)
            {
                String token = "getint";
                if (token.Equals(method.Identifier.Text))
                {
                    
                        var diagnostic = Diagnostic.Create(haveGoodNameMethodRule, method.GetLocation(), method);
                        obj.ReportDiagnostic(diagnostic);
                }
                else if((method.Identifier.Text.Contains("my")&&method.Identifier.Text.Contains(method.ReturnType.ToString()))||(method.Identifier.Text.Contains("get") && method.Identifier.Text.Contains(method.ReturnType.ToString())))
                {
                    var diagnostic = Diagnostic.Create(haveGoodNameMethodRule, method.GetLocation(), method);
                    obj.ReportDiagnostic(diagnostic);

                }
            }
        }

        private static void haveFewConditions(SyntaxTreeAnalysisContext obj)
        {
            SyntaxTree tree = obj.Tree;
            int counter;
            IEnumerable<MethodDeclarationSyntax> methods = tree.GetRoot()
           .DescendantNodes()
           .OfType<MethodDeclarationSyntax>().ToList();
            foreach (var method in methods)
            {
                counter = 0;
                foreach(var statement in method.Body.Statements)
                {
                    String statementKey=statement.ToString().Split(' ').First();
                    if ( statementKey.Contains("if")|| statementKey.Contains("switch"))
                    {
                        counter++;
                    }
                }
                if (counter >= 5)
                {
                    var diagnostic = Diagnostic.Create(haveFewConditionRule, method.GetLocation(), method);
                    obj.ReportDiagnostic(diagnostic);
                }
            }
        }

    }
}
