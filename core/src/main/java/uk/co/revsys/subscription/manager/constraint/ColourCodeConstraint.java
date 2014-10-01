package uk.co.revsys.subscription.manager.constraint;

import java.util.regex.Pattern;

public class ColourCodeConstraint implements Constraint {

    private final Pattern pattern;

    public ColourCodeConstraint() {
        pattern = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
    }

    @Override
    public void check(String constraint, String value) throws FailedConstraintException {
        if(!pattern.matcher(value).matches()){
            throw new FailedConstraintException("Invalid colour code '" + value + "'");
        }
    }

}
