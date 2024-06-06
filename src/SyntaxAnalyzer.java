import java.util.ArrayList;

public class SyntaxAnalyzer {

    static ArrayList<Variable> grammar = new ArrayList<>();

    void grammarDefinition() {

    }

    void grammarFirst () {
        for(Variable v : grammar)
            v.firstCalculator();
    }

    void grammarFollow () {
        for(Variable v : grammar)
            v.followCalculator();
    }
}
