package hexlet.code.conditions;

public class Positive implements Condition {

    @Override
    public <T> Boolean isMet(T t) {
        return (Integer) t >= 0;
    }
}
