import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class String_OpTest {
    private final String_Op string_op = new String_Op();
    @Test
    public void testDeleteChar() {
        assertEquals("Hello", string_op.deleteChar("Hello!"));
        assertEquals("",string_op.deleteChar(""));
    }
    @Test
    public void testDeleteAll() {
        assertEquals("", string_op.deleteAll("Hello"));
        assertEquals("", string_op.deleteAll(""));
    }
    @Test
    public void testParseExpressionToTokens() {
        assertEquals("Invalid tokens", string_op.parseExpressionToTokens("a"));
        assertEquals("4.0", string_op.parseExpressionToTokens("2+2"));
        assertEquals("18.0", string_op.parseExpressionToTokens("2*8+1"));
        assertEquals("3.0", string_op.parseExpressionToTokens("6/2"));
        assertEquals("4.0", string_op.parseExpressionToTokens("2^2"));
        assertEquals("2.0", string_op.parseExpressionToTokens("√4"));
        assertEquals("-1.0", string_op.parseExpressionToTokens("3-4"));
        assertEquals("0.0", string_op.parseExpressionToTokens("5%5"));
        assertEquals("3.0", string_op.parseExpressionToTokens("(2+1)"));
        assertEquals("9.0", string_op.parseExpressionToTokens("(2+1)*3"));
        assertEquals("9.0", string_op.parseExpressionToTokens("(2+1)^2"));
        assertEquals("9.0",string_op.parseExpressionToTokens("(2+1)(2+1)"));
        assertEquals("8.0",string_op.parseExpressionToTokens("2(2*1)+2(2*1)"));
    }

}
