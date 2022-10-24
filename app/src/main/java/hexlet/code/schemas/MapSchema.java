package hexlet.code.schemas;

import hexlet.code.DataType;
import hexlet.code.conditions.Condition;
import hexlet.code.conditions.Required;
import hexlet.code.conditions.SizeOf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class MapSchema extends BaseSchema {

    private List<Condition> conditions = new ArrayList<>();
    private DataType dataType;
    private Map<String, BaseSchema> schemas = new HashMap<>();
    public MapSchema() {
        dataType = DataType.Map;
    }

    public MapSchema required() {
        Condition r = new Required(DataType.Map);
        conditions.add(r);
        return this;
    }

    public MapSchema sizeof(int size) {
        Condition s = new SizeOf(size);
        conditions.add(s);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> newSchemas) {
        this.schemas = newSchemas;
        dataType = DataType.Nested;
        return this;
    }

    @Override
    public <T> boolean isValid(T data) {
        return switch (dataType) {
            case Map -> isValidMap(data);
            case Nested -> isValidNested(data);
            default -> throw new RuntimeException("err MapSchema.isValid in switch dataType");
        };
    }

    private <T, K, V> boolean isValidMap(T data) {
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
    private <T, V> boolean isValidNested(T data) {
        Map<String, V> mapForValidation;
        try {
            mapForValidation = data == null ? null : (Map<String, V>) data;
        } catch (Exception e) {
            return false;
            //throw new RuntimeException("(Map) data: " + data + " Maybe false? exc: " + e.toString()); //отладка
        }
        Set<String> keySet = mapForValidation.keySet();
        for (String key : keySet) {
            if (!schemas.containsKey(key)) {
                return false;
            }
            if (!schemas.get(key).isValid(mapForValidation.get(key))) {
                return false;
            }
        }
        return true;
    }
}
