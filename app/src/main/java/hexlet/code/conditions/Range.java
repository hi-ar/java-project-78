package hexlet.code.conditions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class Range implements Condition { //linter: final
    private int from;
    private int to;
    @Override
    public <T> Boolean isMet(T t) {
        int num = (Integer) t;
        return (num >= from && num <= to);
    }
}
