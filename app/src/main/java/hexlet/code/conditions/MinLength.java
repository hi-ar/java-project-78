package hexlet.code.conditions;

import lombok.AllArgsConstructor;

//@RequiredArgsConstructor(of={"minLength"}) //как правильно?
@AllArgsConstructor
public class MinLength implements Condition {

    private int minLength;

    @Override
    public Boolean isMet(String s) {
        return s.length() >= minLength;
    }
}
