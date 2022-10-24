package hexlet.code.conditions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class MinLength implements Condition { //linter: final

    private int minLength;

    @Override
    public <T> Boolean isMet(T t) {
        String text = (String) t;
        return text.length() >= minLength;
    }
}
