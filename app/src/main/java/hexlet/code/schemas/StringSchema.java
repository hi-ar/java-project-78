package hexlet.code.schemas;

import hexlet.code.DataType;
import hexlet.code.conditions.Condition;
import hexlet.code.conditions.Contains;
import hexlet.code.conditions.MinLength;
import hexlet.code.conditions.Required;

import java.util.ArrayList;
import java.util.List;

public final class StringSchema extends BaseSchema {

    private List<Condition> conditions = new ArrayList<>();

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

    @Override
    public <T> boolean isValid(T data) {
        String text;
        try {
            text = data == null ? null : (String) data;
        } catch (Exception e) {
            return false;
//            throw new RuntimeException("(String) data: " + data + " Maybe false?  exc: " + e.toString()); //отладка
        }
        for (Condition condition : conditions) {
            if (!condition.isMet(text)) {
                return false;
            }
        }
        return true;
    }
}
