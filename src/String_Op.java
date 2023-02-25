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
        String[] tokens = expression.split("(?<=[-+*/%√a^b])|(?=[-+*/%√a^b])");

        Deque<Double> deq = new ArrayDeque<>();
        for (String t : tokens) {
            if (!isOperator(t)) deq.push(Double.parseDouble(t.replaceAll(",", ".")));
        }
        for (String t : tokens) {
            if (isOperator(t)) {
                double op2 = deq.pop();
                double op1 = deq.pop();
                double res = mathematical_op.evaluate(op1, op2, Tokens.fromString(t));
                deq.push(res);
            }
            //TODO ordningsföljd matematisk
            //TODO krävs bara en siffra för sqrt
            //TODO pow tecken
        }
        return String.valueOf(deq.pop());

    }

    protected boolean isOperator(String token) {
        return switch (token) {
            case "+", "-", "/", "%", "*","(",")","√","a^b" -> true;
            default -> false;
        };
    }
}
