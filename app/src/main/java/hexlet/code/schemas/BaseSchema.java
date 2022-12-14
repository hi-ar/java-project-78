package hexlet.code.schemas;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Setter
public abstract class BaseSchema {
    private List<Predicate> conditions = new ArrayList<>();

    public final void addCondition(int index, Predicate condition) { //
        conditions.add(index, condition);
    }

    public final void addCondition(Predicate condition) { //
        conditions.add(condition);
    }

    private boolean isRequired; //isRequired availability flag

    public abstract Object specifyClass(Object data);

    public abstract boolean hasValidFormat(Object data);

    public abstract BaseSchema required();

    /**
     * @param data for validation
     * @return does the data satisfy the specified conditions
     */
    public boolean isValid(Object data) {

        if (!isRequired && (data == null || !hasValidFormat(data))) { //not required & (null or incorrect class)
            return true;
        }

        Object convertedData;

        try {
            convertedData = data == null ? null : specifyClass(data);
        } catch (Exception e) {
            return false;
        }

        for (Predicate condition : conditions) {
            if (!condition.test(convertedData)) {
                return false;
            }
        }
        return true;
    }
}
