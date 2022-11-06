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

    public abstract Class validClass();

    public abstract BaseSchema required();

    /**
     *
     * @param data for validation
     * @return does the data satisfy the specified conditions
     */
    public boolean isValid(Object data) {

        if (!isRequired && data == null //if the field is optional and has non-valid data type
                || !isRequired && !data.getClass().equals(validClass())) {
            return true;
        }

        Object convertedData;

        try {
            convertedData = data == null ? null : (validClass().cast(data));
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
