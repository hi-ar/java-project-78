package hexlet.code.schemas;

import hexlet.code.DataType;
import hexlet.code.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    public static List<Predicate> conditions = new ArrayList<>(); //priv
    public static boolean isRequired; //флаг наличия условия isRequired

    public static DataType dataType; //enum String, Number, Map, Nested

    public void addCondition(Predicate condition) { //
        conditions.add(condition);
    }
//    public static void clearConditions() {
//        conditions.clear();
//    }

    public abstract BaseSchema required();

    public <T> boolean isValid(T data) {

        Class correctClass; //класс данных, соответствующий enum (для приведения)
        Object convertedData; //данные, приведенные к правильному типу

        switch (dataType) {
            case String -> correctClass = String.class;
            case Number -> correctClass = Integer.class;
            case Map -> correctClass = HashMap.class;
            case Nested -> {
                return MapSchema.isValidNested(data);
            }
            default -> throw new RuntimeException("ERR: BaseSchema/isValid/switch dataType");
        }

        //если поле "не обязательно", принимаем любой тип данных
        if (!isRequired) return true;

        //до проверки соответствия предикатами, привести данные к типу данных валидатора
        //пробуем кастовать, если не приводится, фолз
        try {
            convertedData = (correctClass.cast(data));
        } catch (Exception e) {
//            throw new RuntimeException("Base/correctClass.cast(data) error"); //отладка
            return false;
        }
        //проверяем приведенные к правильному типу данные по списку условий
        for (Predicate condition : conditions) {
            if (!condition.test(convertedData)) {
                return false;
            }
        }
        return true;
    }
}
