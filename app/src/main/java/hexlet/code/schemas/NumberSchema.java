package hexlet.code.schemas;

import hexlet.code.DataType;
import hexlet.code.conditions.Condition;
import hexlet.code.conditions.Positive;
import hexlet.code.conditions.Range;
import hexlet.code.conditions.Required;

import java.util.ArrayList;
import java.util.List;

public final class NumberSchema extends BaseSchema {

    private List<Condition> conditions = new ArrayList<>();

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

    @Override
    public <T> boolean isValid(T data) {
        Integer num;
        try {
            num = data == null ? null : (Integer) data;
        } catch (Exception e) {
            return false;
            //throw new RuntimeException("(Integer) data: " + data + " Maybe false? exc: " + e.toString()); //отладка
        }
        for (Condition condition : conditions) {
            if (!condition.isMet(num)) {
                return false;
            }
        }
        return true;
    }
}
