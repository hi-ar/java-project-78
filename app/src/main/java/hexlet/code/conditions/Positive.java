package hexlet.code.conditions;

public final class Positive implements Condition { //linter: final

    @Override
    public <T> Boolean isMet(T t) {
        return t == null ? true : (Integer) t >= 0;
    }
}
