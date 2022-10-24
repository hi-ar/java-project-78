package hexlet.code.schemas;

public abstract class BaseSchema {

    public abstract <T> boolean isValid(T data);
}
