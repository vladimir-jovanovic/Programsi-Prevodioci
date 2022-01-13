import java_cup.runtime.*;

%%
%class Lexer

%cup

%line
%column

%eofval{
    return new Symbol(sym.EOF);
%eofval}

%{
    public int getLine(){
        return yyline;
    }

    String string = "";
%}

//STATES
%xstate COMMENT, STRING

//MACROS
WHITESPACE = [\ \t]
NEWLINE = \n|\r|\r\n

IDENTIFIER = [a-z][a-z0-9]*
INTEGER  = [1-9][0-9]*
DOUBLE1 = [-+]?0\.[0-9]+
DOUBLE2 = [-+]?[1-9][0-9]*\.[0-9]+
DOUBLE = {DOUBLE1}|{DOUBLE2}

%%

// RULES

<COMMENT> {
    "--"            { yybegin(YYINITIAL); }
    {NEWLINE}                      { string += ('\n'); }
    .               { ; }
}

<STRING> {
    \"                             { yybegin(YYINITIAL);
                                     return new Symbol( sym.STRCONST, string ); 
                                   }
    [^\n\r\"\\]+                   { string += ( yytext() ); }
    \\t                            { string += ('\t'); }
    \\n                            { string += ('\n'); }
    \\r                            { string += ('\r'); }
    \\\"                           { string += ('\"'); }
    \\                             { string += ('\\'); }
    {NEWLINE}                      { string += ('\n'); }
}

"--"                { yybegin(COMMENT); }

"(" { return new Symbol( sym.OPEN_ROUND_BRACKETS ); }
")" { return new Symbol( sym.CLOSE_ROUND_BRACKETS );}
"[" { return new Symbol( sym.OPEN_SQUARE_BRACKETS ); }
"]" { return new Symbol( sym.CLOSE_SQUARE_BRACKETS ); }
"{" { return new Symbol( sym.OPEN_CURLY_BRACKETS ); }
"}" { return new Symbol( sym.CLOSE_CURLY_BRACKETS ); }
"<" { return new Symbol( sym.OPEN_ANGLE_BRACKETS ); }
">" { return new Symbol( sym.CLOSE_ANGLE_BRACKETS ); }

"+" { return new Symbol( sym.PLUS ); }
"*" { return new Symbol( sym.MULTIPLY ); }
"="  { return new Symbol( sym.ASSIGN ); }

"," { return new Symbol( sym.COMMA ); }
";" { return new Symbol( sym.SEMICOLON ); }
":" { return new Symbol( sym.COLON ); }

"experiment"        { return new Symbol ( sym.EXPERIMENT_BEGIN ); }
"~experiment"       { return new Symbol ( sym.EXPERIMENT_END ); }
"if"                { return new Symbol ( sym.IF ); }
"else"                { return new Symbol ( sym.ELSE ); }
"lt"                { return new Symbol ( sym.LT    ); }
"eq"                { return new Symbol ( sym.EQ    ); }
"gt"                { return new Symbol ( sym.GT    ); }
"requirements"      { return new Symbol ( sym.REQUIREMENTS_BEGIN ); }
"~requirements"     { return new Symbol ( sym.REQUIREMENTS_END ); }
"nodes"             { return new Symbol ( sym.NODES ); }
"execution"         { return new Symbol ( sym.EXECUTION_BEGIN ); }
"~execution"        { return new Symbol ( sym.EXECUTION_END ); }
"node"              { return new Symbol ( sym.NODE_BEGIN ); }
"~node"             { return new Symbol ( sym.NODE_END ); }
"WP"                { return new Symbol ( sym.WP ); }
"name"              { return new Symbol ( sym.NAME ); }
"int"               { return new Symbol ( sym.INT ); }
"double"            { return new Symbol ( sym.DOUBLE ); }
"string"            { return new Symbol ( sym.STRING ); }

{IDENTIFIER}        { return new Symbol ( sym.ID, yytext() ); }

{INTEGER}           { return new Symbol( sym.INTCONST, Integer.valueOf(yytext()) ); }

{DOUBLE}            { return new Symbol( sym.DCONST, Double.valueOf(yytext()) ); }

\"                  { string = ""; yybegin(STRING); }

{WHITESPACE}        { ; }
{NEWLINE}           { ; }

.                   { System.err.println(" ERROR: " + yytext() ); }
