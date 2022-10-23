package hexlet.code.schemas;

import hexlet.code.DataType;
import hexlet.code.conditions.Condition;
import hexlet.code.conditions.Required;
import hexlet.code.conditions.SizeOf;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema {
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

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        this.schemas = schemas;
        dataType = DataType.Nested;
        return this;
    }
}
