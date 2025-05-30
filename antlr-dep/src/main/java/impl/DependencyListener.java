    package impl;

    import parser.JavaSnippetBaseListener;
    import parser.JavaSnippetParser.*;

    import java.util.*;

    public class DependencyListener extends JavaSnippetBaseListener {
        private final List<VarEntry> entries = new ArrayList<>();
        private Set<String> currentDependencies = new LinkedHashSet<>();
        private String currentVarName;

        @Override
        public void enterStatement(StatementContext ctx) {
            if (ctx.IDENTIFIER() != null && ctx.expression() != null) {
                currentVarName = ctx.IDENTIFIER().getText();
                currentDependencies = new LinkedHashSet<>();
            }
        }

        @Override
        public void exitStatement(StatementContext ctx) {
            if (currentVarName != null && currentDependencies != null) {
                entries.add(new VarEntry(currentVarName, currentDependencies));
            }
            currentVarName = null;
            currentDependencies = null;
        }

        @Override
        public void enterMethodCallExpr(MethodCallExprContext ctx) {
            MethodCallContext methodCall = ctx.methodCall();
            QualifiedNameContext qualifiedName = methodCall.qualifiedName();
            String firstIdentifier = qualifiedName.IDENTIFIER().get(0).getText();
            currentDependencies.add(firstIdentifier);
            // Add argument dependencies
            ArgumentsContext arguments = methodCall.arguments();
            if (arguments != null && arguments.expression() != null) {
                for (ExpressionContext arg : arguments.expression()) {
                    collectExpressionDependencies(arg);
                }
            }
        }

        @Override
        public void enterConstructorCallExpr(ConstructorCallExprContext ctx) {
            QualifiedNameContext qualifiedName = ctx.qualifiedName();
            String simpleType = qualifiedName.IDENTIFIER()
                    .get(qualifiedName.IDENTIFIER().size() - 1)
                    .getText();
            currentDependencies.add(simpleType);
            // Add argument dependencies
            ArgumentsContext arguments = ctx.arguments();
            if (arguments != null && arguments.expression() != null) {
                for (ExpressionContext arg : arguments.expression()) {
                    collectExpressionDependencies(arg);
                }
            }
        }

        @Override
        public void enterQualifiedIdentifierExpr(QualifiedIdentifierExprContext ctx) {
            QualifiedNameContext qualifiedName = ctx.qualifiedName();
            String firstIdentifier = qualifiedName.IDENTIFIER().get(0).getText();
            currentDependencies.add(firstIdentifier);
        }

        private void collectExpressionDependencies(ExpressionContext expr) {
            // Recursively collect dependencies from nested expressions
            if (expr instanceof MethodCallExprContext) {
                enterMethodCallExpr((MethodCallExprContext) expr);
            } else if (expr instanceof ConstructorCallExprContext) {
                enterConstructorCallExpr((ConstructorCallExprContext) expr);
            } else if (expr instanceof QualifiedIdentifierExprContext) {
                enterQualifiedIdentifierExpr((QualifiedIdentifierExprContext) expr);
            } else if (expr instanceof BinaryExprContext binaryExpr) {
                collectExpressionDependencies(binaryExpr.expression(0));
                collectExpressionDependencies(binaryExpr.expression(1));
            } else if (expr instanceof ParenExprContext parenExpr) {
                collectExpressionDependencies(parenExpr.expression());
            }
            // Ignore StringLiteralExpr and NumberLiteralExpr
        }
        
        public List<VarEntry> getEntries() {
            return entries;
        }

        public List<Map<String, Object>> getEntriesAsJsonObjects() {
            List<Map<String, Object>> result = new ArrayList<>();
            for (VarEntry entry : entries) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("var", entry.varName);
                map.put("dependsOn", new ArrayList<>(entry.dependencies));
                result.add(map);
            }
            return result;
        }

        public static class VarEntry {
            public final String varName;
            public final Set<String> dependencies;


            public VarEntry(String varName, Set<String> dependencies) {
                this.varName = varName;
                this.dependencies = new LinkedHashSet<>(dependencies);
            }

            public String getVarName() {
                return varName;
            }

            public Set<String> getDependencies() {
                return dependencies;
            }

            @Override
            public String toString() {
                return varName + " depends on " + dependencies;
            }
        }
    }
