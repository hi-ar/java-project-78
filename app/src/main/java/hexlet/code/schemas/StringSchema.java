package hexlet.code.schemas;

import hexlet.code.DataType;
import hexlet.code.conditions.Condition;
import hexlet.code.conditions.Contains;
import hexlet.code.conditions.MinLength;
import hexlet.code.conditions.Required;

public class StringSchema extends BaseSchema {
    public StringSchema() {
        dataType = DataType.String;
    }

    public StringSchema required() {
        Condition r = new Required(DataType.String);
        conditions.add(r);
        return this;
    }

    public StringSchema minLength(int minLength) {
        Condition m = new MinLength(minLength);
        conditions.add(m);
        return this;
    }

    public StringSchema contains(String searchingValue) {
        Condition c = new Contains(searchingValue);
        conditions.add(c);
        return this;
    }
}
