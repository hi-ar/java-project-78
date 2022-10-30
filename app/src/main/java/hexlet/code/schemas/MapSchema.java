package hexlet.code.schemas;

import hexlet.code.DataType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public final class MapSchema<K, V> extends BaseSchema {

    public MapSchema() {
        dataType = DataType.Map;
    }

    private static Map<String, BaseSchema> schemas = new HashMap<>(); //для nested
    // schemas.put("name", v.string().required()); schemas.put("age", v.number().positive());

    public MapSchema required() {
        isRequired = true;
        Predicate<Map<K, V>> req = value -> value != null;
        addCondition(req);
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Map<K, V>> sof = value -> value.size() == size;
        addCondition(sof);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> newSchemas) { //получили список условий (мапу)
        dataType = DataType.Nested;
        this.schemas = newSchemas;
        return this;
    }

    //    @Override
//    public <T> boolean isValid(T data) {
//        return switch (dataType) {
//            case Map -> isValidMap(data);
//            case Nested -> isValidNested(data);  //если происходит вызов шейп которому передается мапа, то
//            //к проверкам должна добавляться проверка Set<String> keySet = mapForValidation.keySet(); итд
//            default -> throw new RuntimeException("ERR MapSchema/isValid/switch dataType");
//        };
//    }
//    private <T, K, V> boolean isValidMap(T data) {
//        Map<K, V> map;
//
//        try {
//            map = data == null ? null : (Map<K, V>) data;
//        } catch (Exception e) {
//            return false;
//            //throw new RuntimeException("(Map) data: " + data + " Maybe false? exc: " + e.toString()); //отладка
//        }
//        for (Predicate condition : conditions) {
//            if (!condition.test(map)) {
//                return false;
//            }
//        }
//        return true;
//    }
    public static <T, V> boolean isValidNested(T data) {
        Map<String, V> mapForValidation;
        try {
            //data это мапа name:vasya, age:10
            mapForValidation = data == null ? null : (Map<String, V>) data;
        } catch (Exception e) {
            return false;
            //throw new RuntimeException("(Map) data: " + data + " Maybe false? exc: \n" + e.toString()); //отладка
        }
        System.out.println("mapForValidation: " + mapForValidation);
        Set<String> keySet = mapForValidation.keySet();

        System.out.println(keySet);
        System.out.println(schemas.size() + " conditions in List: " + schemas);

        for (String key : keySet) { //name, age
            if (!schemas.containsKey(key)) { //если нет правил для такого поля
                return false;
            }
            if (!schemas.get(key).isValid(mapForValidation.get(key))) {
                //если не выполняется v.string().required()  .isValid   (Kolya)
                System.out.println("crash on: " + schemas.get(key).getClass() + " .isValid " + mapForValidation.get(key));
                return false;
//test out:
//mapForValidation: {name=Kolya, age=10}
//[name, age]
//2 conditions in List: {name=hexlet.code.schemas.StringSchema@3c49fab6, age=hexlet.code.schemas.NumberSchema@515f4131}
//crash on: class hexlet.code.schemas.StringSchema .isValid Kolya
            }
        }
        System.out.println("Ok!");
        return true;
    }
}
