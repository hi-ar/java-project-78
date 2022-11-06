package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorsTest {

    public static final int NUM5 = 5;
    public static final int NUM_5 = -5;
    public static final int NUM10 = 10;
    public static final int NUM_10 = -10;
    public static final int NUM100 = 100;
    public static final int NUM_100 = -100;
    public static final int NUM11 = 11;

    @Test
    public void stringTest1() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(0)).isTrue();

        schema.required();
        assertThat(schema.isValid("what does the fox say")).isTrue(); // true
        assertThat(schema.isValid(NUM10)).isFalse(); // false
        assertThat(schema.isValid(null)).isFalse(); // false
        assertThat(schema.isValid("")).isFalse(); // false

        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue(); // true
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue(); // true
        assertThat(schema.contains("abc").isValid("what does the fox say")).isFalse(); // false

        assertThat(schema.isValid("what does the fox say")).isFalse(); // false
    }

    @Test
    public void stringTest2() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        schema.minLength(NUM10);
        assertThat(schema.isValid("Hello!")).isFalse(); //тк нет рекваед
        assertThat(schema.isValid("Hello, World!")).isTrue();

        schema.required();
        assertThat(schema.isValid("Hello!")).isFalse();
        assertThat(schema.isValid("Hello, World!")).isTrue();
    }

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

    @Test
    public void mapTest1() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(0)).isTrue();
        assertThat(schema.isValid("")).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse(); // false
        assertThat(schema.isValid(new HashMap())).isTrue(); // true

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isTrue(); // true

        schema.sizeof(2);
        assertThat(schema.isValid(data)).isFalse();  // false
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue(); // true
    }

    @Test
    public void nestedTest1() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>(); //Мапа СХЕМЫ имя:валидатор
        //проверять не только сам объект Map, но и данные внутри него
        // позволяет описывать валидацию для значений объекта Map по ключам.
        //положили два условия, обязательное имя и возраст положительное число:
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas); //метод шейп валидация мапы СХЕМЫ, сохраняет набор требований к мапам


        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", NUM10);
        assertThat(schema.isValid(human1)).isTrue(); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null); //это поле не required и не integer
        assertThat(schema.isValid(human2)).isTrue(); // true

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(schema.isValid(human3)).isFalse(); // false  -test true

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", NUM_10); //это поле не required
        assertThat(schema.isValid(human4)).isFalse();
    }
}
