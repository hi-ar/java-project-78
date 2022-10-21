package hexlet.code.conditions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Required implements Condition {

    @Override
    public Boolean isMet(String s) {
        if (s == null) {
            return false;
        } else return !s.equals("");
    }
}
//return (sentence != null || !sentence.isEmpty());