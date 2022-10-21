package hexlet.code;

import hexlet.code.conditions.Condition;
import hexlet.code.schemas.StringSchema;

import java.util.List;


public class Validator {

    public StringSchema string() {
        return new StringSchema();
    }
    public static Boolean isValid(String sentence, List<Condition> conditions) {
        for (Condition condition : conditions) {
            if (!condition.isMet(sentence)) {
                return false;
            }
        }
        return true;
    }
}
