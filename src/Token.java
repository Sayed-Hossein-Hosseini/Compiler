public class Token {
    private String tokenType;
    private String tokenName;

    public Token(String tokenType, String tokenName) {
        this.tokenName = tokenName;
        this.tokenType = tokenType;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getTokenName() {
        return tokenName;
    }

    @Override
    public String toString() {
        return ": " + this.tokenName + " -> " + this.tokenType;
    }
}
