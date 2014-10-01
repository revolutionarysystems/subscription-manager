package uk.co.revsys.subscription.manager.constraint;

public class BooleanConstraint implements Constraint{

    @Override
    public void check(String constraint, String value) throws FailedConstraintException {
        if(!(value.equals("true") || value.equals("false"))){
            throw new FailedConstraintException("Expected true or false");
        }
    }

}
