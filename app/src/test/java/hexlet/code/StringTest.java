package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.NumberSchema;
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
        assertThat(schema.isValid(0)).isTrue();

        assertThat(schema.required().isValid("Kolya")).isTrue(); // true

        assertThat(schema.isValid("what does the fox say")).isTrue(); // true
        assertThat(schema.isValid(NumberTest.NUM10)).isFalse(); // false
        assertThat(schema.isValid(null)).isFalse(); // false
        assertThat(schema.isValid("")).isFalse(); // false

        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue(); // true
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue(); // true
        assertThat(schema.contains("abc").isValid("what does the fox say")).isFalse(); // false

        assertThat(schema.isValid("what does the fox say")).isFalse(); // false
    }

    @Test
    public void stringTest2() {
        Validator v2 = new Validator();
        StringSchema schema2 = v2.string();
        System.out.println("Number of conditions: " + BaseSchema.conditions.size());

        schema2.minLength(NumberTest.NUM10);
        assertThat(schema2.isValid("Hello!")).isTrue();
        assertThat(schema2.isValid("Hello, World!")).isTrue();
        System.out.println("Number of conditions: " + BaseSchema.conditions.size());
        schema2.required();
        assertThat(schema2.isValid("Hello!")).isFalse();
        System.out.println("Number of conditions: " + BaseSchema.conditions.size());
        assertThat(schema2.isValid("what does the fox say")).isTrue();
        System.out.println("Number of conditions: " + BaseSchema.conditions.size());
    }
}
