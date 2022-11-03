package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberTest {

    public static final int NUM5 = 5;
    public static final int NUM_5 = -5;
    public static final int NUM10 = 10;
    public static final int NUM_10 = -10;
    public static final int NUM100 = 100;
    public static final int NUM_100 = -100;
    public static final int NUM11 = 11;

    @Test
    public void numberTest1() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertThat(schema.isValid(NUM10)).isTrue(); // true
        assertThat(schema.isValid(null)).isTrue(); // true
        assertThat(schema.isValid("abc")).isTrue(); // true
        assertThat(schema.isValid('A')).isTrue(); // true

        schema.required();
        assertThat(schema.isValid(null)).isFalse(); // false
        assertThat(schema.isValid("")).isFalse(); // false
        assertThat(schema.isValid("abc")).isFalse(); // false
        assertThat(schema.isValid(0)).isTrue(); // true
        assertThat(schema.isValid(NUM_10)).isTrue(); // true

        schema.positive();
        assertThat(schema.positive().isValid(NUM10)).isTrue(); // true
        assertThat(schema.positive().isValid("10")).isFalse(); // false
        assertThat(schema.positive().isValid(NUM_10)).isFalse(); // false

        schema.range(NUM_10, NUM10);
        assertThat(schema.isValid(0)).isFalse(); // FFF
        assertThat(schema.isValid(NUM10)).isTrue(); // true
        assertThat(schema.isValid(-1)).isFalse(); // false так как positive
        assertThat(schema.isValid(NUM11)).isFalse(); // false
    }

    @Test
    public void numberTest2() { //without required
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertThat(schema.positive().isValid(null)).isTrue(); // true
        assertThat(schema.isValid("abc")).isTrue(); // true
        assertThat(schema.positive().isValid(NUM_10)).isFalse(); // false

        schema.range(NUM_10, NUM10);
        assertThat(schema.isValid(NUM_100)).isFalse(); // false
    }

    @Test
    public void numberTest3() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.range(NUM_10, NUM10).range(NUM_5, NUM100).positive().range(NUM_100, NUM5);
        assertTrue(schema.isValid(NUM5));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(NUM_5));
        assertFalse(schema.isValid(NUM10));
        assertFalse(schema.isValid(NUM_10));
        assertTrue(schema.isValid("hello"));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(Math.PI));
        assertTrue(schema.isValid('A'));

        schema.required();
        assertTrue(schema.isValid(NUM5));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(NUM_5));
        assertFalse(schema.isValid(NUM10));
        assertFalse(schema.isValid(NUM_10));
        assertFalse(schema.isValid("hello"));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(Math.PI));
        assertFalse(schema.isValid('A'));
    }
}
