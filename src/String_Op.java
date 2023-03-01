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
        Deque<Double> numbers = getNumbers(tokens);

        for (String t : tokens) {
            if (isOperator(t)) {
                operators.push(Operators.fromString(t));
            }
            if (t.equals(Operators.parC.toString())) {
                parseParentheses(operators,numbers);
            }
        }
        while (!operators.isEmpty()) {
            Operators op = operators.pop();

            if (op == Operators.sqrt) {
                double res = mathematical_op.evaluate(numbers.pop(), 0, op);
                numbers.push(res);
            } else if (operators.size() > 1) {
                while (!numbers.isEmpty() &&
                        op != Operators.parO) {
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
        while (numbers.size()>1){
            double op2 = numbers.pop();
            double op1 = numbers.pop();
            double res = mathematical_op.evaluate(op1, op2, Operators.mul);
            numbers.push(res);
        }
        return String.valueOf(numbers.pop());
    }
    void parseParentheses(Deque<Operators>operators, Deque<Double>numbers){
        operators.pop();
        while (operators.peek() != Operators.parO) {
            double op2 = numbers.pop();
            double op1 = numbers.pop();
            double res = mathematical_op.evaluate(op1, op2, operators.pop());
            numbers.addLast(res);
        }

        operators.pop();
    }

    Deque<Double> getNumbers(String[] tokens) {
        Deque<Double> numbers = new ArrayDeque<>();
        for (String t : tokens) {
            if (!isOperator(t)) numbers.push(Double.parseDouble(t.replaceAll(",", ".")));
        }
        return numbers;
    }



    protected boolean isOperator(String token) {
        return switch (token) {
            case "+", "-", "/", "%", "*", "(", ")", "√", "^" -> true;
            default -> false;
        };
    }
}
