public enum Tokens {
    add,
    sub,
    div,
    mul,
    mod,
    parO,
    parC,
    pow,
    sqrt;



    @Override
    public String toString() {
        return switch (this) {
            case add ->  "+";
            case sub -> "-";
            case mul -> "*";
            case mod -> "%";
            case div -> "/";
            case parO -> "(";
            case parC -> ")";
            case sqrt -> "√";
            case pow -> "a^b";
        };
    }

    public static Tokens fromString(String s){
        return switch (s) {
            case "+" -> Tokens.add;
            case "-" -> Tokens.sub;
            case "*" -> Tokens.mul;
            case "%" -> Tokens.mod;
            case "/" -> Tokens.div;
            case "(" -> Tokens.parO;
            case ")" -> Tokens.parC;
            case "√" -> Tokens.sqrt;
            case "a^b" -> Tokens.pow;
            default -> Tokens.fromString(s);
        };
    }
}


