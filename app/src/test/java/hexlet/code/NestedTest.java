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
//        assertThat(schema.isValid(null)).isTrue(); // true
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>(); //Мапа имя-валидатор СХЕМЫ
        schemas.put("name", v.string().required()); //положили имя-строковый валидатор (не пустой)
        schemas.put("age", v.number().positive()); //положили возраст-числовой валидатор (положительное)
        //коллекция условий для энтри 1, коллекция условий для энтри 2, коллекция условий для энтри 3


        //проверять не только сам объект Map, но и данные внутри него
        // позволяет описывать валидацию для значений объекта Map по ключам.
        schema.shape(schemas); //метод шейп валидация мапы СХЕМЫ, сохраняет набор требований к мапам

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        schema.isValid(human1); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        schema.isValid(human2); // true

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        schema.isValid(human3); // false

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        schema.isValid(human4); // false
    }
}
