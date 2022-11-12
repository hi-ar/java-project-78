package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    @Override
    public Class validClass(Object data) {
        return Integer.class;
    }

    @Override
    public boolean hasValidFormat(Object data) {
        return data instanceof Integer;
    }

    public NumberSchema required() {
        setRequired(true);
        Predicate<Object> req = value -> value != null;  //instanceof Integer;
        addCondition(0, req);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> pos = value -> value > 0; //converted to Int in BaseSchema:42
        addCondition(pos);
        return this;
    }

    public NumberSchema range(int from, int to) {
        Predicate<Integer> rng = value -> value >= from && value <= to;
        addCondition(rng);
        return this;
    }
}
