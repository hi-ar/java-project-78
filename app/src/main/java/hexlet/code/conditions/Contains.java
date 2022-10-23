package hexlet.code.conditions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Contains implements Condition {
    private String searchingValue;

    @Override
    public <T> Boolean isMet(T t) {
        String text = (String) t;
        return text.contains(searchingValue);
    }
}
