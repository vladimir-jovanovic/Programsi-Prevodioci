%%

%class MPLexer
%function next_token
%line
%column
%standalone
%type Yytoken

%eofval{
    return new Yytoken(sym.EOF, null, yyline, yycolumn);
%eofval}

%{
    KWTable kwTable = new KWTable();
    int getKW()
    {
        return kwTable.find(yytext());
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
                                     return new Yytoken( sym.CONST, string, yyline, yycolumn); 
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

"(" { return new Yytoken( sym.OPEN_ROUND_BRACKETS, yytext(), yyline, yycolumn); }
")" { return new Yytoken( sym.CLOSE_ROUND_BRACKETS, yytext(), yyline, yycolumn); }
"{" { return new Yytoken( sym.OPEN_CURLY_BRACKETS, yytext(), yyline, yycolumn); }
"}" { return new Yytoken( sym.CLOSE_CURLY_BRACKETS, yytext(), yyline, yycolumn); }
//"[" { return new Yytoken( sym.OPEN_SQUARE_BRACKETS, yytext(), yyline, yycolumn); }
//"]" { return new Yytoken( sym.CLOSE_SQUARE_BRACKETS, yytext(), yyline, yycolumn); }
//"<" { return new Yytoken( sym.OPEN_ANGLE_BRACKETS, yytext(), yyline, yycolumn); }
//">" { return new Yytoken( sym.CLOSE_ANGLE_BRACKETS, yytext(), yyline, yycolumn); }

//"+" { return new Yytoken( sym.PLUS, yytext(), yyline, yycolumn); }
//"*" { return new Yytoken( sym.MULTIPLY, yytext(), yyline, yycolumn); }
"=" { return new Yytoken( sym.ASSIGN, yytext(), yyline, yycolumn); }

//"," { return new Yytoken( sym.COMMA, yytext(), yyline, yycolumn); }
";" { return new Yytoken( sym.SEMICOLON, yytext(), yyline, yycolumn); }
":" { return new Yytoken( sym.COLON, yytext(), yyline, yycolumn); }

{IDENTIFIER}        { int keywordSymbol = getKW();
                      if(keywordSymbol != -1) 
                        return new Yytoken( keywordSymbol, yytext(), yyline, yycolumn); 
                      else 
                        return new Yytoken( sym.ID, yytext(), yyline, yycolumn); 
                    }

\~?[A-Za-z]+        { int keywordSymbol = getKW();
                      if(keywordSymbol != -1) 
                        return new Yytoken( keywordSymbol, yytext(), yyline, yycolumn); 
                      else 
                        System.err.println("ERROR: " + yytext() ); 
                    }

{INTEGER}           { return new Yytoken( sym.CONST, yytext(), yyline, yycolumn); }

{DOUBLE}            { return new Yytoken( sym.CONST, yytext(), yyline, yycolumn); }

\"                  { string = ""; yybegin(STRING); }

{WHITESPACE}        { ; }
{NEWLINE}           { ; }

.                   { System.err.println(" ERROR: " + yytext() ); }
