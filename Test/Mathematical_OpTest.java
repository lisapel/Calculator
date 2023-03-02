
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayDeque;
import java.util.Deque;

   public class Mathematical_OpTest {

        private final Mathematical_Op mathematicalOp = new Mathematical_Op();
        private final Deque<Double> numbers = new ArrayDeque<>();

        @Test
        void testEvaluate_addition() {
            double result = mathematicalOp.evaluate(2, 3, Operators.add);
            Assertions.assertEquals(5, result);
        }

        @Test
        void testEvaluate_subtraction() {
            double result = mathematicalOp.evaluate(3, 2, Operators.sub);
            Assertions.assertEquals(1, result);
        }

        @Test
        void testEvaluate_division() {
            double result = mathematicalOp.evaluate(6, 2, Operators.div);
            Assertions.assertEquals(3, result);
        }

        @Test
        void testEvaluate_modulus() {
            double result = mathematicalOp.evaluate(7, 2, Operators.mod);
            Assertions.assertEquals(1, result);
        }

        @Test
        void testEvaluate_multiplication() {
            double result = mathematicalOp.evaluate(3, 4, Operators.mul);
            Assertions.assertEquals(12, result);
        }

        @Test
        void testEvaluate_sqrt() {
            double result = mathematicalOp.evaluate(4, 0, Operators.sqrt);
            Assertions.assertEquals(2, result);
        }

        @Test
        void testEvaluate_pow() {
            double result = mathematicalOp.evaluate(2, 3, Operators.pow);
            Assertions.assertEquals(8, result);
        }

        @Test
        void testHasHigherPrecedence() {
            boolean result = mathematicalOp.hasPrecedence(Operators.mul, Operators.add);
            Assertions.assertTrue(result);
        }

        @Test
        void testOperate() {
            numbers.push(3.0);
            numbers.push(2.0);
            Deque<Operators> operators = new ArrayDeque<>();
            operators.push(Operators.mul);
            double result = mathematicalOp.operate(numbers, operators);
            Assertions.assertEquals(6, result);
        }
    }

