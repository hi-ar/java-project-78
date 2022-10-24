package hexlet.code.conditions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class Contains implements Condition { //linter: final
    private String searchingValue;

    @Override
    public <T> Boolean isMet(T t) {
        String text = (String) t;
        return text.contains(searchingValue);
    }
}
