package hexlet.code.conditions;

import hexlet.code.DataType;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class Required <K, V> implements Condition {
    private Enum<DataType> dataType;

    @Override
    public <T> Boolean isMet(T t) {

        if (dataType == DataType.String) {
            return isMetStr((String) t);
        } else if (dataType == DataType.Number) {
            return isMetNum((Integer) t);
        } else if (dataType == DataType.Map) {
            return isMetMap((Map<K, V>) t);
        }
        throw new RuntimeException("err Required.isMet if dataType... (out of Str/Num)");
    }

    private Boolean isMetStr(String text) {
        return text != null && !text.equals("");
    }

    private Boolean isMetNum(Integer num) {
        return num != null;
    }
    private Boolean isMetMap(Map<K, V> map) {
        return map != null;
    }
}
