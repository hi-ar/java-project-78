package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NumberTest {

    public static final int NUM10 = 10;
    public static final int NUM_10 = -10;
    public static final int NUM11 = 11;
    @Test
    public void numberTest1() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
//        assertThat(schema.isValid(NUM10)).isTrue(); // true
//        assertThat(schema.isValid(null)).isTrue(); // true
//        assertThat(schema.isValid("abc")).isTrue(); // true
//        assertThat(schema.isValid('A')).isTrue(); // true

        schema.required();
//        assertThat(schema.isValid(null)).isFalse(); // false
        assertThat(schema.isValid("")).isFalse(); // false
//        assertThat(schema.isValid(0)).isTrue(); // true
//
//        schema.positive();
//        assertThat(schema.positive().isValid(NUM10)).isTrue(); // true
//        assertThat(schema.positive().isValid("10")).isFalse(); // false
//        assertThat(schema.positive().isValid(-1)).isFalse(); // false
//
//        schema.range(NUM_10, NUM10);
//        assertThat(schema.isValid(0)).isTrue(); // true
//        assertThat(schema.isValid(NUM10)).isTrue(); // true
//        assertThat(schema.isValid(-1)).isFalse(); // false так как positive
//        assertThat(schema.isValid(NUM11)).isFalse(); // false
    }
    @Test
    public void numberTest2() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertThat(schema.positive().isValid(null)).isTrue(); // true
    }
}
