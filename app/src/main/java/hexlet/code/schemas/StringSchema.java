package hexlet.code.schemas;

import hexlet.code.Validator;
import hexlet.code.conditions.Condition;
import hexlet.code.conditions.Contains;
import hexlet.code.conditions.MinLength;
import hexlet.code.conditions.Required;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class StringSchema {
    public String sentence; //убрать (только для отладки)
    public List<Condition> conditions = new ArrayList<>(); //сделать priv (только для отладки)

    public StringSchema required() {
        Condition r = new Required();
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

    public Boolean isValid(String sentence) {
        this.sentence = sentence; //убрать (только для отладки)
        return Validator.isValid(sentence, conditions);
    }
}
