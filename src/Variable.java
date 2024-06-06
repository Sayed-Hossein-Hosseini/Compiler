import java.util.ArrayList;

public class Variable {
    static  ArrayList<String> terminals;

    ArrayList<String> productionRules;
    ArrayList<String> first;
    ArrayList<String> follow;

    Variable() {
        productionRules = new ArrayList<>();
        first = new ArrayList<>();
        follow = new ArrayList<>();
    }

    public ArrayList<String> getProductionRules() {
        return productionRules;
    }

    public void setProductionRules(String rule) {
        this.productionRules.add(rule);
    }

    public ArrayList<String> getFirst() {
        return first;
    }

    public void setFirst(String firstStr) {
        this.first.add(firstStr);
    }

    public ArrayList<String> getFollow() {
        return follow;
    }

    public void setFollow(String followStr) {
        this.follow.add(followStr);
    }

    public void firstCalculator () {

    }

    public void followCalculator () {

    }
}
