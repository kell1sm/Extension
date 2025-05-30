// Generated from JavaSnippet.g4 by ANTLR 4.13.2
package parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JavaSnippetParser}.
 */
public interface JavaSnippetListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JavaSnippetParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(JavaSnippetParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaSnippetParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(JavaSnippetParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaSnippetParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(JavaSnippetParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaSnippetParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(JavaSnippetParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaSnippetParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(JavaSnippetParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaSnippetParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(JavaSnippetParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaSnippetParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void enterTypeArguments(JavaSnippetParser.TypeArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaSnippetParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void exitTypeArguments(JavaSnippetParser.TypeArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaSnippetParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgument(JavaSnippetParser.TypeArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaSnippetParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgument(JavaSnippetParser.TypeArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaSnippetParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedName(JavaSnippetParser.QualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaSnippetParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedName(JavaSnippetParser.QualifiedNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConstructorCallExpr}
	 * labeled alternative in {@link JavaSnippetParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConstructorCallExpr(JavaSnippetParser.ConstructorCallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConstructorCallExpr}
	 * labeled alternative in {@link JavaSnippetParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConstructorCallExpr(JavaSnippetParser.ConstructorCallExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberLiteralExpr}
	 * labeled alternative in {@link JavaSnippetParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNumberLiteralExpr(JavaSnippetParser.NumberLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberLiteralExpr}
	 * labeled alternative in {@link JavaSnippetParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNumberLiteralExpr(JavaSnippetParser.NumberLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinaryExpr}
	 * labeled alternative in {@link JavaSnippetParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpr(JavaSnippetParser.BinaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinaryExpr}
	 * labeled alternative in {@link JavaSnippetParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpr(JavaSnippetParser.BinaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringLiteralExpr}
	 * labeled alternative in {@link JavaSnippetParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteralExpr(JavaSnippetParser.StringLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringLiteralExpr}
	 * labeled alternative in {@link JavaSnippetParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteralExpr(JavaSnippetParser.StringLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code QualifiedIdentifierExpr}
	 * labeled alternative in {@link JavaSnippetParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedIdentifierExpr(JavaSnippetParser.QualifiedIdentifierExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code QualifiedIdentifierExpr}
	 * labeled alternative in {@link JavaSnippetParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedIdentifierExpr(JavaSnippetParser.QualifiedIdentifierExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link JavaSnippetParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(JavaSnippetParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link JavaSnippetParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(JavaSnippetParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MethodCallExpr}
	 * labeled alternative in {@link JavaSnippetParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMethodCallExpr(JavaSnippetParser.MethodCallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MethodCallExpr}
	 * labeled alternative in {@link JavaSnippetParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMethodCallExpr(JavaSnippetParser.MethodCallExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaSnippetParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(JavaSnippetParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaSnippetParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(JavaSnippetParser.MethodCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaSnippetParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(JavaSnippetParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaSnippetParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(JavaSnippetParser.ArgumentsContext ctx);
}