package hexlet.code.schemas;

import hexlet.code.DataType;
import hexlet.code.conditions.*;

public class NumberSchema extends BaseSchema {
    public NumberSchema() {
        dataType = DataType.Number;
    }

    public NumberSchema required() {
        Condition r = new Required(DataType.Number);
        conditions.add(r);
        return this;
    }

    public NumberSchema positive() {
        Condition p = new Positive();
        conditions.add(p);
        return this;
    }

    public NumberSchema range(int from, int to) {
        Condition c = new Range(from, to);
        conditions.add(c);
        return this;
    }
}
