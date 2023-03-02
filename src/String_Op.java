import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class String_Op {
    private final Deque<Operators> operators = new ArrayDeque<>();
    private final Deque<Double> numbers = new ArrayDeque<>();
    private final Stack<Double> result = new Stack<>();
    private final Mathematical_Op mathematical_op = new Mathematical_Op();

    protected String deleteChar(String text) {
        return text.substring(0, text.length() - 1);
    }

    protected String deleteAll(String text) {
        return text.substring(0, 0);
    }

    protected String parseExpressionToTokens(String expression) {
        String[] tokens = expression.split("(?<=[-+*/%√^()])|(?=[-+*/%√^()])");
        if (containsInvalidTokens(tokens)) {
            return "Invalid tokens";
        } else {
            return String.valueOf(parseAndEvaluate(tokens));
        }
    }

    protected Double parseAndEvaluate(String[] tokens) {
        for (String t : tokens) {
            if (isOperator(t)) {
                operators.push(Operators.fromString(t));
            } else if (!isOperator(t)) {
                numbers.push(Double.parseDouble(t));
            }
            if (t.equals(Operators.parC.toString())) {
                operators.pop();
                while (!operators.isEmpty() && operators.peek() != Operators.parO) {
                    result.push(mathematical_op.operate(numbers, operators));
                    if (numbers.size() >= 1) {
                        handleNumberOutsideParentheses(numbers, result, operators);
                    }
                }
                if (!operators.isEmpty()) {
                    operators.pop();
                }
            }
        }
        result.forEach(numbers::push);
        while (!result.isEmpty()) {
            result.pop();
        }

        while (!operators.isEmpty()) {
            Operators op = operators.pop();

            if (op == Operators.sqrt) {
                numbers.push(mathematical_op.evaluate(numbers.pop(), 0, op));
            } else if (operators.size() > 1) {
                while (!numbers.isEmpty() && op != Operators.parO) {
                    assert operators.peekLast() != null;
                    if (!(mathematical_op.hasHigherPrecedence(op, operators.peekLast()))) break;
                    numbers.push(mathematical_op.operate(numbers, operators));
                }
            } else {
                double op2 = numbers.pop();
                double op1 = numbers.pop();
                numbers.push(mathematical_op.evaluate(op1, op2, op));
            }
        }
        while (numbers.size() > 1) {
            numbers.push(mathematical_op.evaluate(numbers.pop(), numbers.pop(), Operators.mul));
        }

        return numbers.pop();
    }


    void handleNumberOutsideParentheses(Deque<Double> numbers, Stack<Double> result, Deque<Operators> operators) {
        if (numbers.size() == 1) {
            double op2 = numbers.pop();
            double op1 = result.pop();
            result.push(mathematical_op.evaluate(op1, op2, Operators.mul));
        } else if (operators.size() > 1) {
            operators.pop();
            double op2 = numbers.pop();
            double op1 = result.pop();
            result.push(mathematical_op.evaluate(op1, op2, Operators.mul));
            double op3 = numbers.pop();
            result.push(mathematical_op.evaluate(op3, result.pop(), operators.pop()));
        }
    }

    protected boolean isOperator(String token) {
        return switch (token) {
            case "+", "-", "/", "%", "*", "(", ")", "√", "^" -> true;
            default -> false;
        };
    }

    protected boolean containsInvalidTokens(String[] tokens) {
        String pattern = "[0-9-+*/%√^()]";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(Arrays.toString(tokens));
        return !m.find();
    }
}
