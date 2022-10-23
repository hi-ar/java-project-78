package hexlet.code.schemas;

import hexlet.code.DataType;
import hexlet.code.conditions.Condition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseSchema {
    public List<Condition> conditions = new ArrayList<>(); //public для потомков
    public DataType dataType; //public для потомков, switch в isValid()
    //у этого класса два наследника, можно ли заменить enum DataType логикой
    // если isValid() принял данные от потомка А делать... если от Б делать...

    public <T> boolean isValid(T data) {
        return switch (dataType) {
            case String -> isValidStr(data, conditions);
            case Number -> isValidNum(data, conditions);
            case Map -> isValidMap(data, conditions);
            default -> throw new RuntimeException("err BaseSchema.isValid switch dataType");
        };
    }

    private <T> boolean isValidStr(T data, List<Condition> conditions) {
        //что делать с налл, нет обработки, нужна ли?
        String text;
        try {
            text = data == null ? null : (String) data;
        } catch (Exception e) {
            return false;
//            throw new RuntimeException("(String) data: " + data + " Maybe false?  exc: " + e.toString()); //отладка
        }
        for (Condition condition : conditions) {
            if (!condition.isMet(text)) {
                return false;
            }
        }
        return true;
    }

    private <T> boolean isValidNum(T data, List<Condition> conditions) {
        Integer num;
        try {
            num = data == null ? null : (Integer) data;
        } catch (Exception e) {
            return false;
            //throw new RuntimeException("(Integer) data: " + data + " Maybe false? exc: " + e.toString()); //отладка
        }
        for (Condition condition : conditions) {
            if (!condition.isMet(num)) {
                return false;
            }
        }
        return true;
    }

    private <T, K, V> boolean isValidMap(T data, List<Condition> conditions) {
        Map<K, V> map;

        try {
            map = data == null ? null : (Map<K, V>) data;
        } catch (Exception e) {
            return false;
            //throw new RuntimeException("(Map) data: " + data + " Maybe false? exc: " + e.toString()); //отладка
        }
        for (Condition condition : conditions) {
            if (!condition.isMet(map)) {
                return false;
            }
        }
        return true;
    }
}
