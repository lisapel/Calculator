import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class String_Op {
    private final Deque<Operators> operators = new ArrayDeque<>();
    private final Deque<Double> numbers = new ArrayDeque<>();
    private final Stack<Double> result = new Stack<>();
    private final Mathematical_Op mathematical_op = new Mathematical_Op();

    protected String deleteChar(String text) {
        if (text.length() > 0) {
            return text.substring(0, text.length() - 1);
        } else {
            return "";
        }
    }

    protected String deleteAll(String text) {
        return text.substring(0, 0);
    }
    // Method to parse an expression string into tokens, evaluate them, and return the result as a string
    protected String parseExpressionToTokens(String expression) {
        String[] tokens = expression.split("(?<=[-+*/%√^()])|(?=[-+*/%√^()])");
        if (containsInvalidTokens(tokens)) {
            return "Invalid tokens";
        } else {
            return String.valueOf(parseAndEvaluate(tokens));
        }
    }

    // Method to parse an array of tokens and evaluate them, returning the result as a double
    protected Double parseAndEvaluate(String[] tokens) {
        for (String t : tokens) {
            if (isOperator(t)) {
                operators.push(Operators.fromString(t));
            } else if (!isOperator(t)) {
                numbers.push(Double.parseDouble(t));
            }
            // If the token is a closing parenthesis, evaluate the expression inside the parentheses
            if (t.equals(Operators.closingParenthesis.toString())) {
                // Pop operators and numbers until the matching opening parenthesis is found
                operators.pop();
                while (!operators.isEmpty() && operators.peek() != Operators.openingParenthesis) {
                    // Evaluate the topmost operator and two numbers and push the result onto the result stack
                    result.push(mathematical_op.operate(numbers, operators));
                    if (numbers.size() >= 1) {
                        // If there are still numbers left on the numbers stack, evaluate
                        handleOperandOutsideParentheses(numbers, result, operators);
                    }
                }
                // Pop the opening parenthesis
                if (!operators.isEmpty()) {
                    operators.pop();
                }
            }
        }

        // After all tokens have been processed, evaluate any remaining operators and numbers
        while (!result.isEmpty()) {
            numbers.addLast(result.pop());
        }
        while (!operators.isEmpty()) {
            Operators op = operators.pop();

            if (op == Operators.sqrt) {
                numbers.push(mathematical_op.evaluate(numbers.pop(), 0, op));
            } else if (operators.size() > 1) {
                // If there are still more operators left, evaluate them according to their precedence
                while (!numbers.isEmpty() && op != Operators.openingParenthesis) {
                    assert operators.peekLast() != null;
                    if ((mathematical_op.hasPrecedence(op, operators.peekLast()))) break;
                    numbers.push(mathematical_op.operate(numbers, operators));
                }
            } else {
                // If there is only one operator left, evaluate it with two operands
                double op2 = numbers.pop();
                double op1 = numbers.pop();
                numbers.push(mathematical_op.evaluate(op1, op2, op));
            }
        }
        // If there are still more than one number left, multiply them together
        while (numbers.size() > 1) {
            numbers.push(mathematical_op.evaluate(numbers.pop(), numbers.pop(), Operators.mul));
        }

        return numbers.pop();
    }


   protected void handleOperandOutsideParentheses(Deque<Double> numbers, Stack<Double> result, Deque<Operators> operators) {
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
