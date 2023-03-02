import java.util.Deque;

public class Mathematical_Op {

    final Operands addition = Double::sum;
    final Operands subtraction = (a, b) -> a - b;
    final Operands division = (a, b) -> a / b;
    final Operands modulus = (a, b) -> a % b;
    final Operands multiplication = (a, b) -> a * b;
    final Operands pow = Math::pow;

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





