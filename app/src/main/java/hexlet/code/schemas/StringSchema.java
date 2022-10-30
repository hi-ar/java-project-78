package hexlet.code.schemas;

import hexlet.code.DataType;
import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {
    public StringSchema() {
        dataType = DataType.String;
    }

    public StringSchema required() {
        isRequired = true;
        Predicate<String> req = value -> value != null && !value.equals("");
        addCondition(req);
        return this;
    }

    public StringSchema minLength(int minLength) {
        Predicate<String> min = value -> value.length() >= minLength;
        addCondition(min);
        return this;
    }

    public StringSchema contains(String searchingValue) {
        Predicate<String> con = value -> value.contains(searchingValue);
        addCondition(con);
        return this;
    }
}
