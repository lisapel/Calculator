import java.util.Deque;

public class Mathematical_Op {

    final Operate addition = Double::sum;
    final Operate subtraction = (a, b) -> a - b;
    final Operate division = (a, b) -> a / b;
    final Operate modulus = (a, b) -> a % b;
    final Operate multiplication = (a, b) -> a * b;
    final Operate pow = Math::pow;

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
                return pow.operate(a, b);
            }
            default -> {
                return 0.0;
            }
        }
    }
   protected boolean hasPrecedence(Operators op1, Operators op2){
        return op1.getPrecedence()<op2.getPrecedence();
    }
   protected double operate(Deque<Double> numbers,Deque<Operators>operators){
        double b = numbers.pop();
        double a = numbers.pop();
        return evaluate(a,b,operators.pop());
    }

}





