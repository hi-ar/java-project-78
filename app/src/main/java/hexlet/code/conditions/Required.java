package hexlet.code.conditions;

import hexlet.code.DataType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Required implements Condition {
    private Enum<DataType> dataType;

    @Override
    public <T> Boolean isMet(T t) {

        if (dataType == DataType.String) {
            return isMetStr((String) t);  //это не сработает с null?
        } else if (dataType == DataType.Number) {
            return isMetNum((Integer) t); // это не сработает с null?
        }
        throw new RuntimeException("err Required.isMet if dataType... (out of Str/Num)");
    }

    private Boolean isMetStr(String text) {
        return text != null && !text.equals("");
    }

    private Boolean isMetNum(Integer num) {
        return num != null;
    }
}
