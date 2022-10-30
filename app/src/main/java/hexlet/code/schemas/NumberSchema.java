package hexlet.code.schemas;

import hexlet.code.DataType;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {
    public NumberSchema() {
        dataType = DataType.Number;
    }

    public NumberSchema required() {
        isRequired = true;
        Predicate<Integer> req = value -> value != null;
        addCondition(req);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> pos = value -> value >= 0;
        addCondition(pos);
        return this;
    }

    public NumberSchema range(int from, int to) {
        Predicate<Integer> rng = value -> value >= from && value <= to;
        addCondition(rng);
        return this;
    }

//    @Override
//    public <T> boolean isValid(T data) {
//        if (!conditions.contains(Required.class)) {
//            return true;
//        } else {
//
//            Integer num;
//            try {
//                num = data == null ? null : (Integer) data;
//            } catch (Exception e) {
//                return false;
//            }
//            for (Condition condition : conditions) {
//                if (!condition.isMet(num)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
}
