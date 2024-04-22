import java.util.HashMap;
import java.util.Map;

public class LexicalAnalyzer {
    // test String.
    static String lang = "int test_function(int a, int b, bool c){\n" +
            "\t// this is a function\n" +
            "\tif (c == true){\n" +
            "\t\treturn a+b;\n" +
            "\t}\n" +
            "\telse {\n" +
            "\t\treturn a-b;\n" +
            "\t}\n" +
            "}\n" +
            "\n" +
            "int main(){\n" +
            "\tbool add = true;\n" +
            "\tchar _assign1 = '=';\n" +
            "\tchar String_1[] = \" + \";\n" +
            "\tchar String_2[] = \" - \";\n" +
            "\tfor(int i = 0; i <= (+10 / 2); i = i + 1){\n" +
            "\t\tfor (int j = 0x0; j != (5 * -1)  ; j = j - 1){\n" +
            "\t\t\tprint(i);\n" +
            "\t\t\tprint(String_1);\n" +
            "\t\t\tprint(j);\n" +
            "\t\t\tprint(_assign1);\n" +
            "\t\t\tprint(test_function(i,j,add));\n" +
            "\t\t}\n" +
            "\t}\n" +
            "\tadd = false;\n" +
            "\tfor (int i = 0; !(i == +5); i = i + 1) {\n" +
            "\t\tfor (int j = 0x0; j >= -5; j = j - 1) {\n" +
            "\t\t\tif((i % 4) == 0 || (i % 3) == 0)\n" +
            "\t\t\t\tcontinue;\n" +
            "\t\t\tif(j < -4 && i > 3)\n" +
            "\t\t\t\tbreak;\n" +
            "\t\t\tprint(i);\n" +
            "\t\t\tprint(String_2);\n" +
            "\t\t\tprint(j);\n" +
            "\t\t\tprint(_assign1);\n" +
            "\t\t\tprint(test_function(i, j, add));\n" +
            "\t\t}\n" +
            "\t}\n" +
            "\tprint(\"this is\\\" a whole string no other token like '=' or 'else' or even \\\\\\\\comment should be recognized\");\n" +
            "\tchar back = '\\\\';\n" +
            "\tchar quote = '\\'';\n" +
            "\tint _123 = 0XABCdef1230;\n" +
            "}";

    static Map<Integer, Token> allTokens = new HashMap<>();

    public static void analyze() {

    }

    //hossein
    public static void keywordIdentifier() {
        int start = 0;
        int pointer = 0;
        while (start < lang.length()) {
            if (lang.charAt(pointer) == 'b'){
                pointer++;
                if (lang.charAt(pointer) == 'o' && pointer < lang.length()){
                    pointer++;
                    if (lang.charAt(pointer) == 'o' && pointer < lang.length()){
                        pointer++;
                        if (lang.charAt(pointer) == 'l' && pointer < lang.length()) {
                            pointer++;
                            allTokens.put(start, new Token("T_Bool", "bool"));
                        }
                    }
                }
            }
        }

    }

    //reza
    public static void idIdentifier() {

    }

    public static void scopeIdentifier() {
        for (int i = 0; i < lang.length(); i++) {
            if (lang.charAt(i) == '{')
                allTokens.put(i, new Token("T_LC", "{"));
            else if (lang.charAt(i) == '}')
                allTokens.put(i, new Token("T_RC", "}"));
            else if (lang.charAt(i) == '(')
                allTokens.put(i, new Token("T_LP", "("));
            else if (lang.charAt(i) == ')')
                allTokens.put(i, new Token("T_RP", ")"));
            else if (lang.charAt(i) == '[')
                allTokens.put(i, new Token("T_LB", "["));
            else if (lang.charAt(i) == ']')
                allTokens.put(i, new Token("T_RB", "]"));
            else if (lang.charAt(i) == ';')
                allTokens.put(i, new Token("T_Semicolon", ";"));
            else if (lang.charAt(i) == ',')
                allTokens.put(i, new Token("T_Comma", ","));

        }
    }

    //reza
    public static void numericIdentifier() {

    }

    //hossein
    public static void constCharsIdentifier() {

    }

    //reza
    public static void operatorIdentifier() {

    }

    public static void whiteSpaceIdentifier() {
        for(int i = 0 ; i < lang.length() ; i++){
            if (lang.charAt(i) == ' ')
                allTokens.put(i, new Token("T_Whitespace", " "));
            else if (lang.charAt(i) == '\n')
                allTokens.put(i, new Token("T_Whitespace", "\n"));
            else if (lang.charAt(i) == '\t')
                allTokens.put(i, new Token("T_Whitespace", "\t"));
        }
    }
}
