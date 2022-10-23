package hexlet.code.conditions;

public class Positive implements Condition {

    @Override
    public <T> Boolean isMet(T t) {
        return t == null ? true : (Integer) t >= 0;
    }
}
