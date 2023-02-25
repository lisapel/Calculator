

public class Mathematical_Op {


    static double addition(double a, double b){
        return a+b;
    }
    static double subtraction(double a,double b){
        return a-b;
    }
    static double division(double a,double b){
        return a/b;
    }
    static double modulus(double a,double b){
        return a%b;
    }
    static double multiplication(double a, double b){
        return a*b;
    }

    protected double evaluate(double a, double b, Tokens token){
        switch (token){
            case add -> {
                return addition(a,b);
            }
            case div -> {
                return division(a,b);
            }
            case mod -> {
                return modulus(a,b);
            }
            case mul -> {
                return multiplication(a,b);
            }
            case sub -> {
                return subtraction(a,b);
            }
            default -> {
                return 0.0;
            }
        }}}



