package hexlet.code.conditions;

public interface Condition {
    <T> Boolean isMet(T t);
}
