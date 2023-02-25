import java.util.*;

public class String_Op {
    Mathematical_Op mathematical_op = new Mathematical_Op();

    protected String deleteChar(String text) {
        return text.substring(0, text.length() - 1);
    }

    protected String deleteAll(String text) {
        return text.substring(0, 0);
    }


    protected String parseExpression(String expression) {
        String[] tokens = expression.split("(?<=[-+*/%])|(?=[-+*/%])");

        Deque<Double> deq = new ArrayDeque<>();
        for (String t : tokens) {
            if (!isOperator(t)) {
                deq.push(Double.parseDouble(t.replaceAll(",",".")));
            }
        }
        for (String t : tokens) {
            if (isOperator(t)) {
                double op2 = deq.pop();
                double op1 = deq.pop();
                double res = mathematical_op.evaluate(op1, op2, Tokens.fromString(t));
                deq.push(res);
            }

        }
        return String.valueOf(deq.pop());

    }

    protected boolean isOperator(String token) {
        return switch (token) {
            case "+", "-", "/", "%", "*" -> true;
            default -> false;
        };
    }
}
//TODO ordningsföljd matematisk