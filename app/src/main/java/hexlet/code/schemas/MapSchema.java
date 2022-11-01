package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public final class MapSchema<K, V> extends BaseSchema {

    private static Map<String, BaseSchema> schemas = new HashMap<>(); //for saving conditions
    // schemas.put("name", v.string().required()); schemas.put("age", v.number().positive());

    @Override
    public Class validClass() {
        return Map.class;
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

    private boolean isNested = false; //specific isValid() for nested (shape)
    public MapSchema shape(Map<String, BaseSchema> newSchemas) { //getting conditions map
        isNested = true;
        this.schemas = newSchemas;
        return this;
    }
    public <T> boolean isValid(T data) {
        if (isNested) {
            return isValidNested(data);
        }
        return super.isValid(data);
    }

    public <T, V> boolean isValidNested(T data) {
        Map<String, V> mapForValidation;
        try {
            //data  {name=Kolya, age=10}
            mapForValidation = data == null ? null : (Map<String, V>) data;
        } catch (Exception e) {
            return false;
        }

        Set<String> keySet = mapForValidation.keySet();

        for (String key : keySet) { //[name, age]
            if (!schemas.containsKey(key)) { //if no conditions for this key
                return false;
            }
            if (!schemas.get(key).isValid(mapForValidation.get(key))) {
                //если не выполняется v.string().required()  .isValid   (Kolya)
                return false;
            }
        }
        return true;
    }
}
