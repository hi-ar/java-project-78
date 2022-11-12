package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema<K, V> extends BaseSchema {
    private static Map<String, BaseSchema> schemas = new HashMap<>(); //for saving conditions
    // schemas.put("name", v.string().required()); schemas.put("age", v.number().positive());

    @Override
    public Class validClass() {
        return HashMap.class; //HashMap
    }

    @Override
    public boolean hasValidFormat(Object data) {
        return data instanceof Map<?, ?>;
    }

    public MapSchema required() {
        setRequired(true);
        Predicate<Map<K, V>> req = value -> value != null;
        addCondition(0, req);
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Map<K, V>> sof = value -> value.size() == size;
        addCondition(sof);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> newSchemas) { //getting conditions map
        this.schemas = new HashMap<>(newSchemas); //saving conditions map
        Predicate<Map<String, V>> checkNested = mapForValidation -> {
            for (String key : mapForValidation.keySet()) {
                if (!schemas.containsKey(key)) { //if no conditions for this key
                    return false;
                }
                if (!schemas.get(key).isValid(mapForValidation.get(key))) {
                    //if not fulfilled  v.string().required()  .isValid   (Kolya)
                    return false;
                }
            }
            return true;
        };
        addCondition(checkNested);
        return this;
    }
}
