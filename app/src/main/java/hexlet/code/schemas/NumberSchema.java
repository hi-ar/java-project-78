package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    @Override
    public Class validClass() {
        return Integer.class;
    }

    public NumberSchema required() {
        setRequired(true);
        Predicate<Integer> req = value -> value != null;
        addCondition(req);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> pos = value -> (Integer) value >= 0;
        addCondition(pos);
        return this;
    }

    public NumberSchema range(int from, int to) {
        Predicate<Integer> rng = value -> value >= from && value <= to;
        addCondition(rng);
        return this;
    }
}
