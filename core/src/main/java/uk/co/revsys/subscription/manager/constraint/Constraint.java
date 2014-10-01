package uk.co.revsys.subscription.manager.constraint;

public interface Constraint {

    public void check(String constraint, String value) throws FailedConstraintException;
    
}
