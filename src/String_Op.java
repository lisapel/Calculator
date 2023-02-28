import java.util.*;

public class String_Op {
    final Mathematical_Op mathematical_op = new Mathematical_Op();

    protected String deleteChar(String text) {
        return text.substring(0, text.length() - 1);
    }

    protected String deleteAll(String text) {
        return text.substring(0, 0);
    }


    protected String parseExpressionToTokens(String expression) {
        String[] tokens = expression.split("(?<=[-+*/%√^()])|(?=[-+*/%√^()])");
        Deque<Operators> operators = new ArrayDeque<>();
        Deque<Double> numbers = new ArrayDeque<>();

        for (String t : tokens) {
            if (isOperator(t)) {
                operators.push(Operators.fromString(t));
            }else if(!isOperator(t)){
                numbers.push(Double.parseDouble(t));
            }}
        for (String t:tokens){
            if (t.equals(Operators.parC.toString())) {
               parseWithinParentheses(operators,numbers);

            }
        }
        while (!operators.isEmpty()) {
            parseTheRest(numbers,operators);
        }
        if (numbers.size()> 1){
            handleRemainingNumbers(numbers);
        }
        return String.valueOf(numbers.pop());
    }

    void parseWithinParentheses(Deque<Operators> operators, Deque<Double>numbers){
        operators.pop();
        while (operators.peek() != Operators.parO && operators.peek()!=Operators.parC) {
            double op2 = numbers.pop();
            double op1 = numbers.pop();
            double res = mathematical_op.evaluate(op1, op2, operators.pop());
            numbers.addLast(res);
        }
        operators.pop();

    }
    void handleRemainingNumbers(Deque<Double>numbers){
        double res = mathematical_op.evaluate(numbers.pop(),numbers.pop(),Operators.mul);
        numbers.addLast(res);

    }
    void parseTheRest(Deque<Double>numbers, Deque<Operators> operators){
        Operators op = operators.pop();

        if (op == Operators.sqrt) {
            double res = mathematical_op.evaluate(numbers.pop(), 0, op);
            numbers.push(res);
        } else if (operators.size() > 1) {
            while (!numbers.isEmpty() && op != Operators.parO) {
                assert operators.peekLast() != null;
                if (!(op.getPrecedence() <= operators.peekLast().getPrecedence())) break;
                double op2 = numbers.pop();
                double op1 = numbers.pop();
                double res = mathematical_op.evaluate(op1, op2, operators.pop());
                numbers.push(res);
            }
        } else {
            double op2 = numbers.pop();
            double op1 = numbers.pop();
            double res = mathematical_op.evaluate(op1, op2, op);
            numbers.push(res);
        }
    }

    protected boolean isOperator(String token) {
        return switch (token) {
            case "+", "-", "/", "%", "*", "(", ")", "√", "^" -> true;
            default -> false;
        };
    }
}
