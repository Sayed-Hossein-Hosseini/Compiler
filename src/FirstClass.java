import java.util.ArrayList;
import java.util.Scanner;

public class FirstClass {
    static int prodCount;
    static ArrayList<String> prod = new ArrayList<>();
    static ArrayList<Character> nt = new ArrayList<>();
    static ArrayList<Character> t = new ArrayList<>();
    static ArrayList<Character> frst = new ArrayList<>();
    static String[] table = {"T_Bool",
                             "T_Break",
                             "T_Char",
                             "T_Continue",
                             "T_Else",
                             "T_False",
                             "T_For",
                             "T_If",
                             "T_Int",
                             "T_Print",
                             "T_Return",
                             "T_True",
                             "T_AOp_PL",
                             "T_AOp_MN",
                             "T_AOp_ML",
                             "T_AOp_DV",
                             "T_AOp_RM",
                             "T_ROp_L",
                             "T_ROp_G",
                             "T_ROp_LE",
                             "T_ROp_GE",
                             "T_ROp_NE",
                             "T_ROp_E",
                             "T_LOp_AND",
                             "T_LOp_OR",
                             "T_LOp_NOT",
                             "T_Assign",
                             "T_LP",
                             "T_RP",
                             "T_LC",
                             "T_RC",
                             "T_LB",
                             "T_RB",
                             "T_Semicolon",
                             "T_Comma",
                             "T_Id",
                             "T_Decimal",
                             "T_Hexadecimal",
                             "T_String",
                             "T_Character",
                             "T_Comment",
                             "T_Whitespace",
                             };
    static ArrayList<String> first = new ArrayList<>();
    static int frc;
    static int prevr, prevc;
    static int ntc;

    private static void firstCalculator(char c, int hop) {
        if (c != '#') { // not a null
            for (int i = 0; i < prodCount; i++) {
                int prodIndex = i;
                if (prod.get(i).charAt(0) == c) { // checks if non-terminal symbol

                    hop = 0;
                    firstCalculator(prod.get(prodIndex).charAt(3 + hop), hop);
                    prevr = prodIndex;
                    prevc = 3 + hop;
                } else { // if terminal
                    if (!frst.contains(c) && !nt.contains(c)) { // if not already in set & not a Non-Terminal
                        frst.add(c);
                        if (Character.isDigit(c)) {
                            frst.add(prod.get(prodIndex).charAt(4 + hop));
                        }
                        frc++;
                    }
                }
            }
        } else { // if it is null
            if (!frst.contains(c)) {
                frst.add(c);
                frc++;
            }
            hop++;
            prevc = prevc + hop;
            char d = prod.get(prevr).charAt(prevc + 3);
            firstCalculator(d, hop);
        }

    }

    private static void firstTokenGenerator() {
        for (int i = 0; i < frst.size() - 1; i++) {
            if (Character.isDigit(frst.get(i)) && Character.isDigit(frst.get(i+1))) {
                String str = String.valueOf(frst.get(i)) + frst.get(i + 1);
                //System.out.println(str);
                first.add(table[Integer.parseInt(str)]);
            }
            else if (frst.get(i) ==  '#') {
                first.add("#");
            }
        }
    }

    private void testFirst() { // getting grammar and printing the first value for chosen variable
        Scanner scanner = new Scanner(System.in);
        frc = 0;
        System.out.print("Number of productions? ");
        prodCount = scanner.nextInt();
        scanner.nextLine(); // consume newline
        for (int i = 0; i < prodCount; i++) {
            String production = scanner.nextLine();
            prod.add(production);
            nt.add(production.charAt(0));
            ntc++;
        }
        System.out.print("Enter char symbol ?");
        String ip = scanner.nextLine();

        System.out.print("\nfirst of " + ip.charAt(0) + " is : {");
        firstCalculator(ip.charAt(0), 0);
        for (String c : first) {
            System.out.print(" " + c);
        }
        System.out.print(" }");
    }


    public static void main(char c, int hop) {

        firstCalculator(c, hop);
        firstTokenGenerator();
    }
}

