import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class LexicalAnalyzer {
    // test String.
    static private String lang = "";
//            "int test_function(int a, int b, bool c){\n" +
//            "\t// this is a function\n" +
//            "\tif (c == true){\n" +
//            "\t\treturn a+b;\n" +
//            "\t}\n" +
//            "\telse {\n" +
//            "\t\treturn a-b;\n" +
//            "\t}\n" +
//            "}\n" +
//            "\n" +
//            "int main(){\n" +
//            "\tbool add = true;\n" +
//            "\tchar _assign1 = '=';\n" +
//            "\tchar String_1[] = \" + \";\n" +
//            "\tchar String_2[] = \" - \";\n" +
//            "\tfor(int i = 0; i <= (+10 / 2); i = i + 1){\n" +
//            "\t\tfor (int j = 0x0; j != (5 * -1)  ; j = j - 1){\n" +
//            "\t\t\tprint(i);\n" +
//            "\t\t\tprint(String_1);\n" +
//            "\t\t\tprint(j);\n" +
//            "\t\t\tprint(_assign1);\n" +
//            "\t\t\tprint(test_function(i,j,add));\n" +
//            "\t\t}\n" +
//            "\t}\n" +
//            "\tadd = false;\n" +
//            "\tfor (int i = 0; !(i == +5); i = i + 1) {\n" +
//            "\t\tfor (int j = 0x0; j >= -5; j = j - 1) {\n" +
//            "\t\t\tif((i % 4) == 0 || (i % 3) == 0)\n" +
//            "\t\t\t\tcontinue;\n" +
//            "\t\t\tif(j < -4 && i > 3)\n" +
//            "\t\t\t\tbreak;\n" +
//            "\t\t\tprint(i);\n" +
//            "\t\t\tprint(String_2);\n" +
//            "\t\t\tprint(j);\n" +
//            "\t\t\tprint(_assign1);\n" +
//            "\t\t\tprint(test_function(i, j, add));\n" +
//            "\t\t}\n" +
//            "\t}\n" +
//            "\tprint(\"this is\\\" a whole string no other token like '=' or 'else' or even \\\\\\\\comment should be recognized\");\n" +
//            "\tchar back = '\\\\';\n" +
//            "\tchar quote = '\\'';\n" +
//            "\tint _123 = 0XABCdef1230;\n" +
//            "}";

    static private Map<Integer, Token> allTokens = new HashMap<>();
    static private int numberProgramme = 0; // number programme file

    public static void analyze() {
        input();
    }

    private static void tokenizer() {
        keywordIdentifier();
        idIdentifier();
        scopeIdentifier();
        decimalIdentifier();
        hexadecimalIdentifier();
        operatorIdentifier();
        whiteSpaceIdentifier();
        constCharsIdentifier();

        output();
    }

    private static void input() { // programm input
        try {
            String dir = "D:\\University\\Term 6\\Compiler design\\Project\\Compiler\\data\\input" ;
            File directory = new File("D:\\University\\Term 6\\Compiler design\\Project\\Compiler\\data\\input");
            String [] input = directory.list();
            String fileName = "";
            numberProgramme = input.length;

            for (int lengthFile = 0 ; lengthFile < numberProgramme ; lengthFile++)
                fileName = "D:\\University\\Term 6\\Compiler design\\Project\\Compiler\\data\\input\\" + input[lengthFile];
                File address = new File(fileName);
                Scanner programm = new Scanner(address);

                while (programm.hasNextLine())
                    lang += programm.nextLine().strip();

                tokenizer(); // tokenizer programm

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void output(){ // token output
        ArrayList<Integer> sortedKeys = new ArrayList<>(allTokens.keySet());

        Collections.sort(sortedKeys);

        try {
            String dir = "D:\\University\\Term 6\\Compiler design\\Project\\Compiler\\data\\output" ;
            File directory = new File("D:\\University\\Term 6\\Compiler design\\Project\\Compiler\\data\\output");
            String fileName = "";

            for (int lengthFile = 0 ; lengthFile < numberProgramme ; lengthFile++)
                fileName = "D:\\University\\Term 6\\Compiler design\\Project\\Compiler\\data\\output\\" + "output " + (lengthFile + 1) + ".txt";
                FileWriter address = new FileWriter(fileName);

                for (int x : sortedKeys) {
                    address.write(x + allTokens.get(x).toString());
                    System.out.println(x + allTokens.get(x).toString());
                }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void keywordIdentifier() {
        int start = 0;
        int pointer = 0;
        while (start < lang.length()) {
            boolean flag = true;
            if (lang.charAt(pointer) == 'b') {
                pointer++;
                if (checkPointer(pointer))
                    return;
                if (lang.charAt(pointer) == 'o') {
                    pointer++;
                    if (checkPointer(pointer))
                        return;
                    if (lang.charAt(pointer) == 'o') {
                        pointer++;
                        if (checkPointer(pointer))
                            return;
                        if (lang.charAt(pointer) == 'l') {
                            flag = false;
                            allTokens.put(start, new Token("T_Bool", "bool")); // bool
                        }
                    }
                } else if (lang.charAt(pointer) == 'r') {
                    pointer++;
                    if (checkPointer(pointer))
                        return;
                    if (lang.charAt(pointer) == 'e') {
                        pointer++;
                        if (checkPointer(pointer))
                            return;
                        if (lang.charAt(pointer) == 'a') {
                            pointer++;
                            if (checkPointer(pointer))
                                return;
                            if (lang.charAt(pointer) == 'k') {
                                flag = false;
                                allTokens.put(start, new Token("T_Break", "break")); // break
                            }
                        }
                    }
                }
            } else if (lang.charAt(start) == 'c') {
                pointer++;
                if (checkPointer(pointer))
                    return;
                if (lang.charAt(pointer) == 'h') {
                    pointer++;
                    if (checkPointer(pointer))
                        return;
                    if (lang.charAt(pointer) == 'a') {
                        pointer++;
                        if (checkPointer(pointer))
                            return;
                        if (lang.charAt(pointer) == 'r') {
                            flag = false;
                            allTokens.put(start, new Token("T_Char", "char")); // char
                        }
                    }
                } else if (lang.charAt(pointer) == 'o') {
                    pointer++;
                    if (checkPointer(pointer))
                        return;
                    if (lang.charAt(pointer) == 'n') {
                        pointer++;
                        if (checkPointer(pointer))
                            return;
                        if (lang.charAt(pointer) == 't') {
                            pointer++;
                            if (checkPointer(pointer))
                                return;
                            if (lang.charAt(pointer) == 'i') {
                                pointer++;
                                if (checkPointer(pointer))
                                    return;
                                if (lang.charAt(pointer) == 'n') {
                                    pointer++;
                                    if (checkPointer(pointer))
                                        return;
                                    if (lang.charAt(pointer) == 'u') {
                                        pointer++;
                                        if (checkPointer(pointer))
                                            return;
                                        if (lang.charAt(pointer) == 'e') {
                                            flag = false;
                                            allTokens.put(start, new Token("T_Continue", "continue")); // continue
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (lang.charAt(start) == 'e') {
                pointer++;
                if (checkPointer(pointer))
                    return;
                if (lang.charAt(pointer) == 'l') {
                    pointer++;
                    if (checkPointer(pointer))
                        return;
                    if (lang.charAt(pointer) == 's') {
                        pointer++;
                        if (checkPointer(pointer))
                            return;
                        if (lang.charAt(pointer) == 'e') {
                            flag = false;
                            allTokens.put(start, new Token("T_Else", "else")); // else
                        }
                    }
                }
            } else if (lang.charAt(start) == 'f') {
                pointer++;
                if (checkPointer(pointer))
                    return;
                if (lang.charAt(pointer) == 'a') {
                    pointer++;
                    if (checkPointer(pointer))
                        return;
                    if (lang.charAt(pointer) == 'l') {
                        pointer++;
                        if (checkPointer(pointer))
                            return;
                        if (lang.charAt(pointer) == 's') {
                            pointer++;
                            if (checkPointer(pointer))
                                return;
                            if (lang.charAt(pointer) == 'e') {
                                flag = false;
                                allTokens.put(start, new Token("T_False", "false")); // false
                            }
                        }
                    }
                } else if (lang.charAt(pointer) == 'o') {
                    pointer++;
                    if (checkPointer(pointer))
                        return;
                    if (lang.charAt(pointer) == 'r') {
                        flag = false;
                        allTokens.put(start, new Token("T_For", "for")); // for
                    }
                }
            } else if (lang.charAt(start) == 'i') {
                pointer++;
                if (checkPointer(pointer))
                    return;
                if (lang.charAt(pointer) == 'f') {
                    flag = false;
                    allTokens.put(start, new Token("T_If", "if")); // if
                } else if (lang.charAt(pointer) == 'n') {
                    pointer++;
                    if (checkPointer(pointer))
                        return;
                    if (lang.charAt(pointer) == 't') {
                        flag = false;
                        allTokens.put(start, new Token("T_Int", "int")); // int
                    }
                }
            } else if (lang.charAt(start) == 'p') {
                pointer++;
                if (checkPointer(pointer))
                    return;
                if (lang.charAt(pointer) == 'r') {
                    pointer++;
                    if (checkPointer(pointer))
                        return;
                    if (lang.charAt(pointer) == 'i') {
                        pointer++;
                        if (checkPointer(pointer))
                            return;
                        if (lang.charAt(pointer) == 'n') {
                            pointer++;
                            if (checkPointer(pointer))
                                return;
                            if (lang.charAt(pointer) == 't') {
                                flag = false;
                                allTokens.put(start, new Token("T_Print", "print")); // print
                            }
                        }
                    }
                }
            } else if (lang.charAt(start) == 'r') {
                pointer++;
                if (checkPointer(pointer))
                    return;
                if (lang.charAt(pointer) == 'e') {
                    pointer++;
                    if (checkPointer(pointer))
                        return;
                    if (lang.charAt(pointer) == 't') {
                        pointer++;
                        if (checkPointer(pointer))
                            return;
                        if (lang.charAt(pointer) == 'u') {
                            pointer++;
                            if (checkPointer(pointer))
                                return;
                            if (lang.charAt(pointer) == 'r') {
                                pointer++;
                                if (checkPointer(pointer))
                                    return;
                                if (lang.charAt(pointer) == 'n') {
                                    flag = false;
                                    allTokens.put(start, new Token("T_Return", "return")); // return
                                }
                            }
                        }
                    }
                }
            } else if (lang.charAt(start) == 't') {
                pointer++;
                if (checkPointer(pointer))
                    return;
                if (lang.charAt(pointer) == 'r') {
                    pointer++;
                    if (checkPointer(pointer))
                        return;
                    if (lang.charAt(pointer) == 'u') {
                        pointer++;
                        if (checkPointer(pointer))
                            return;
                        if (lang.charAt(pointer) == 'e') {
                            flag = false;
                            allTokens.put(start, new Token("T_True", "true")); // true
                        }
                    }
                }
            }

            if (flag) { // next word athuntication
                while (pointer < lang.length()) {
                    if (lang.charAt(pointer) == ' ' || lang.charAt(pointer) == '\n' || lang.charAt(pointer) == '\t' || lang.charAt(pointer) == '(' || lang.charAt(pointer) == ',' || lang.charAt(pointer) == '/') { // start keyword
                        if (checkPointer(pointer + 1))
                            return;
                        if (lang.charAt(pointer + 1) == '/') { // check commend
                            while (lang.charAt(pointer) != '\n' && pointer < lang.length()){ // end commend
                                pointer++;
                                if (checkPointer(pointer))
                                    return;
                            }
                        }
                        pointer++;
                        start = pointer;
                        break;
                    } else if (lang.charAt(pointer) == '\'' || lang.charAt(pointer) == '\"') { // check qutation
                        while (lang.charAt(pointer) != '\n' && pointer < lang.length()){ // end commend
                            pointer++;
                            if (checkPointer(pointer))
                                return;
                        }
                    }
                    pointer++;
                    start = pointer;
                }
            } else {
                pointer += 2;
                start = pointer;
            }
        }

    }

    private static void idIdentifier() {
        StringBuilder name = new StringBuilder();
        int start = 0;
        boolean inString = false; // Flag to track if we are inside a string
        boolean inComment = false; // Flag to track if we are inside a comment
        boolean isHex = false; // Flag to track if we are inside a hex
        for (int i = 0; i < lang.length(); i++) {
            char currentChar = lang.charAt(i);
            if (currentChar == '\"') {
                if (lang.charAt(i - 1) != '\\')
                    inString = !inString; // Toggle the inString flag when encountering a double quote
            }
            if (currentChar == '/' && lang.charAt(i + 1) == '/') {
                inComment = true;
                i++;
            }
            if (currentChar == '\n') {
                inComment = false;
            }
            if (currentChar == '0' && (lang.charAt(i + 1) == 'x' || lang.charAt(i + 1) == 'X')) {
                isHex = true;
                i++;
            }
            if (currentChar == ' ' && isHex) {
                isHex = false;
            }
            if (!inString && !inComment && !isHex && ((currentChar == '_' || Character.isLetter(currentChar)) && name.length() == 0)) {
                name.append(currentChar);
            } else if (!inString && !inComment && !isHex && ((Character.isLetterOrDigit(currentChar) || currentChar == '_') && name.length() > 0)) {
                name.append(currentChar);
            } else {
                if (name.length() > 0) {
                    if (!isKeyword(name.toString()) && !isComment(name.toString()) && !isString(name.toString())) {
                        allTokens.put(i - name.length(), new Token("T_Id", name.toString()));
                    }
                    name.setLength(0);
                }
            }
        }
    }

    private static void scopeIdentifier() {
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

    private static void decimalIdentifier() {
        StringBuilder name = new StringBuilder();
        boolean isNumber = false;
        boolean isHex = false;
        boolean isId = false;

        for (int i = 0; i < lang.length(); i++) {
            char currentChar = lang.charAt(i);
            if (currentChar == '0' && (lang.charAt(i+1) == 'x' || lang.charAt(i+1) == 'X')) {
                isHex = true;
                i++;
            }
            if (currentChar == ' ' && isHex) {
                isHex = false;
            }
            if (currentChar == '_' ) {
                isId = true;
            }
            if (currentChar == ' ' && isId) {
                isId = false;
            }
            if (Character.isDigit(currentChar) && !isHex && !isId && lang.charAt(i - 1) != '_') {
                name.append(currentChar);
                isNumber = true;
            }
            if ((currentChar == '+' || currentChar == '-') && Character.isDigit(lang.charAt(i + 1))) {
                name.append(currentChar);
            }
            if ((currentChar == ' ' || currentChar == ';' || currentChar == ')' || currentChar == ']' || currentChar == ',') && isNumber) {
                allTokens.put(i - name.length(), new Token("T_Decimal", name.toString()));
                isHex = false;
                isNumber = false;
                name.setLength(0);
            }
        }
    }

    private static void hexadecimalIdentifier() {
        StringBuilder name = new StringBuilder();
        boolean isHex = false;
        for (int i = 0; i < lang.length(); i++) {
            if (lang.charAt(i) == '0' && i + 1 < lang.length() && (lang.charAt(i + 1) == 'x' || lang.charAt(i + 1) == 'X')) {
                name.append("0x");
                i++; // Skip 'x' or 'X'
                isHex = true;
            }
            if (isHex && (Character.isDigit(lang.charAt(i)) || (lang.charAt(i) >= 'A' && lang.charAt(i) <= 'F') || (lang.charAt(i) >= 'a' && lang.charAt(i) <= 'f'))) {
                name.append(lang.charAt(i));
            }
            if (isHex && !name.isEmpty() && (lang.charAt(i) == ' ' || lang.charAt(i) == ';' || lang.charAt(i) == ')' || lang.charAt(i) == ']')) {
                allTokens.put(i - name.length(), new Token("T_Hexadecimal", name.toString()));
                isHex = false;
                name.setLength(0);
            }
        }
    }

    private static void constCharsIdentifier() {

    }

    private static void operatorIdentifier() {
        for (int i = 0; i < lang.length(); i++) {
            if (lang.charAt(i) == '=') {
                if (lang.charAt(i + 1) == '=') {
                    allTokens.put(i, new Token("T_ROp_E", "=="));
                    i++;
                }
                else {
                    allTokens.put(i, new Token("T_Assign", "="));
                }
            }
            if (lang.charAt(i) == '!') {
                if (lang.charAt(i + 1) == '=') {
                    allTokens.put(i, new Token("T_ROp_NE", "!="));
                    i++;
                }
                else {
                    allTokens.put(i, new Token("T_LOp_NOT", "!"));
                }
            }
            if (lang.charAt(i) == '>') {
                if (lang.charAt(i + 1) == '=') {
                    allTokens.put(i, new Token("T_ROp_GE", ">="));
                    i++;
                }
                else {
                    allTokens.put(i, new Token("T_ROp_G", ">"));
                }
            }
            if (lang.charAt(i) == '<') {
                if (lang.charAt(i + 1) == '=') {
                    allTokens.put(i, new Token("T_ROp_LE", "<="));
                    i++;
                }
                else {
                    allTokens.put(i, new Token("T_ROp_L", "<"));
                }
            }
            if (lang.charAt(i) == '&' && lang.charAt(i + 1) == '&') {
                allTokens.put(i, new Token("T_LOp_AND", "&&"));
                i++;
            }
            if (lang.charAt(i) == '|' && lang.charAt(i + 1) == '|') {
                allTokens.put(i, new Token("T_LOp_OR", "||"));
                i++;
            }
            if (lang.charAt(i) == '+' && !Character.isDigit(lang.charAt(i + 1))) {
                allTokens.put(i, new Token("T_AOp_PL", "+"));
            }
            if (lang.charAt(i) == '-' && !Character.isDigit(lang.charAt(i + 1))) {
                allTokens.put(i, new Token("T_AOp_MN", "-"));
            }
            if (lang.charAt(i) == '*') {
                allTokens.put(i, new Token("T_AOp_ML", "*"));
            }
            if (lang.charAt(i) == '/' && lang.charAt(i + 1) != '/') {
                allTokens.put(i, new Token("T_AOp_DV", "/"));
            }
            if (lang.charAt(i) == '%') {
                allTokens.put(i, new Token("T_AOp_RM", "%"));
            }
        }
    }

    private static void whiteSpaceIdentifier() {
        for (int i = 0; i < lang.length(); i++) {
            if (lang.charAt(i) == ' ')
                allTokens.put(i, new Token("T_Whitespace", " "));
            else if (lang.charAt(i) == '\n')
                allTokens.put(i, new Token("T_Whitespace", "\\n"));
            else if (lang.charAt(i) == '\t')
                allTokens.put(i, new Token("T_Whitespace", "\\t"));
        }
    }

    private static boolean checkPointer (int pointer) {
        if (pointer == lang.length())
            return true;
        return false;
    }

    private static boolean isKeyword(String identifier) {
        String[] keywords = {"bool", "break", "char", "continue", "else", "false", "for", "if", "int", "print", "return", "true"};
        for (String keyword : keywords) {
            if (identifier.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isComment(String identifier) {

        return identifier.startsWith("//");
    }

    private static boolean isString(String identifier) {
        return identifier.startsWith("\"") && identifier.endsWith("\"");
    }
}
