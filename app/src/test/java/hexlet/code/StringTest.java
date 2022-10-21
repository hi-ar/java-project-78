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

        schema.required();
        assertThat(schema.isValid("what does the fox say")).isTrue(); // true
        assertThat(schema.isValid("hexlet")).isTrue(); // true
//        System.out.println(schema.sentence + " " + schema.conditions);
        assertThat(schema.isValid(null)).isFalse(); // false
//        System.out.println(schema.sentence + " " + schema.conditions);
        assertThat(schema.isValid("")).isFalse(); // false
//        System.out.println(schema.sentence + " " + schema.conditions);

        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue(); // true НЕ РАБОТАЕТ
//        System.out.println(schema.sentence + " " + schema.conditions);
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue(); // true
//        System.out.println(schema.sentence + " " + schema.conditions);
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse(); // false
//        System.out.println(schema.sentence + " " + schema.conditions);

        assertThat(schema.isValid("what does the fox say")).isFalse(); // false
    }
}
