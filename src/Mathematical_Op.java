

public class Mathematical_Op {

    final Operands addition = Double::sum;
    final Operands subtraction = (a, b) -> a - b;
    final Operands division = (a, b) -> a / b;
    final Operands modulus = (a, b) -> a % b;
    final Operands multiplication = (a, b) -> a * b;
    final Operands pow = Math::pow;


    //TODO add more functions

    protected double evaluate(double a, double b, Operators token) {
        switch (token) {
            case add -> {
                return addition.operate(a, b);
            }
            case div -> {
                return division.operate(a, b);
            }
            case mod -> {
                return modulus.operate(a, b);
            }
            case mul -> {
                return multiplication.operate(a, b);
            }
            case sub -> {
                return subtraction.operate(a, b);
            }
            case sqrt -> {
                return Math.sqrt(a);
            }
            case pow -> {
                return pow.operate(a,b);
            }
            default -> {
                return 0.0;
            }
        }
    }

}



