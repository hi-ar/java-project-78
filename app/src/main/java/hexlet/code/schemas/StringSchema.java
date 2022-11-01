package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    @Override
    public Class validClass() {
        return String.class;
    }

    public StringSchema required() {
        setRequired(true);
        Predicate<String> req = value -> value != null && !value.equals("");
        addCondition(0, req);
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
