package hexlet.code.conditions;

import lombok.AllArgsConstructor;
import java.util.Map;

@AllArgsConstructor
public class  SizeOf <K, V> implements Condition {
    private int size;
    @Override
    public <T> Boolean isMet(T t) {
        Map<K, V> map = (Map<K, V>) t;
        return map.size() == size;
    }
}
