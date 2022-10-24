package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTest {
    @Test
    public void stringTest1() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(0)).isFalse();

        schema.required();
        assertThat(schema.isValid("what does the fox say")).isTrue(); // true
        assertThat(schema.isValid(NumberTest.NUM10)).isFalse(); // false
        assertThat(schema.isValid(null)).isFalse(); // false
        assertThat(schema.isValid("")).isFalse(); // false

        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue(); // true
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue(); // true
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse(); // false

        assertThat(schema.isValid("what does the fox say")).isFalse(); // false
    }

    @Test
    public void stringTest2() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        schema.minLength(NumberTest.NUM10);
        assertThat(schema.isValid("Hello!")).isFalse();
        assertThat(schema.isValid("Hello, World!")).isTrue();
    }
}
