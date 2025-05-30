grammar JavaSnippet;

program: (statement | .)*? EOF;

statement: type IDENTIFIER '=' expression ';'?;

type: qualifiedName (typeArguments)? ('[' ']')*;

typeArguments: LT typeArgument (COMMA typeArgument)* GT | LT GT;
typeArgument: type;

qualifiedName: IDENTIFIER ('.' IDENTIFIER)*;

expression:
    methodCall                                  # MethodCallExpr
    | 'new' qualifiedName typeArguments? '(' arguments ')' # ConstructorCallExpr
    | qualifiedName                             # QualifiedIdentifierExpr
    | STRING_LITERAL                            # StringLiteralExpr
    | NUMBER_LITERAL                            # NumberLiteralExpr
    | expression '+' expression                 # BinaryExpr
    | '(' expression ')'                        # ParenExpr
    ;

methodCall: qualifiedName '.' IDENTIFIER '(' arguments ')';
arguments: (expression (',' expression)*)?;

// Lexer Rules
LT: '<';
GT: '>';
COMMA: ',';
IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]*;
STRING_LITERAL: '"' (~["\\] | '\\' .)* '"';
NUMBER_LITERAL: '-'? [0-9]+ ('.' [0-9]+)?;

WS: [ \t\n\r]+ -> skip;
COMMENT: '/*' .*? '*/' -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;
LBRACE: '{' -> skip;
RBRACE: '}' -> skip;
ARROW: '->' -> skip;
COLON: ':' -> skip;
SLASH: '/' -> skip;
SEMI: ';' -> skip;
OTHER: . -> skip;
