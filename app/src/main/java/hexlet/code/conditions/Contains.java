package hexlet.code.conditions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Contains implements Condition {
    private String searchingValue;

    @Override
    public Boolean isMet(String s) {
        return s.contains(searchingValue);
    }
}
