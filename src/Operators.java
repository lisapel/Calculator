
public enum Operators {
    add(4),
    sub(4),
    div(3),
    mul(3),
    mod(3),
    openingParenthesis(1),
    closingParenthesis(1),
    pow(2),
    sqrt(2);

     private final int precedence;
     Operators(int precedence){
         this.precedence=precedence;
     }

    public int getPrecedence() {
        return precedence;
    }

    @Override
    public String toString() {
        return switch (this) {
            case add ->  "+";
            case sub -> "-";
            case mul -> "*";
            case mod -> "%";
            case div -> "/";
            case openingParenthesis -> "(";
            case closingParenthesis -> ")";
            case sqrt -> "√";
            case pow -> "^";
        };
    }

    public static Operators fromString(String s){
        return switch (s) {
            case "+" -> Operators.add;
            case "-" -> Operators.sub;
            case "*" -> Operators.mul;
            case "%" -> Operators.mod;
            case "/" -> Operators.div;
            case "(" -> Operators.openingParenthesis;
            case ")" -> Operators.closingParenthesis;
            case "√" -> Operators.sqrt;
            case "^" -> Operators.pow;
            default -> Operators.fromString(s);
        };
    }



}


