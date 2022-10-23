package hexlet.code.conditions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MinLength implements Condition {

    private int minLength;

    @Override
    public <T> Boolean isMet(T t) {
        String text = (String) t;
        return text.length() >= minLength;
    }
}
