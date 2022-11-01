package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class NestedTest {
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
        human1.put("age", NumberTest.NUM10);
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
        human4.put("age", NumberTest.NUM_10); //это поле не required
        assertThat(schema.isValid(human4)).isFalse();
    }
}
