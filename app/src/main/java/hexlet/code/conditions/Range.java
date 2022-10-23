package hexlet.code.conditions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Range implements Condition {
    int from;
    int to;
    @Override
    public <T> Boolean isMet(T t) {
        int num = (Integer) t;
        return (num >= from && num <= to);
    }
}
