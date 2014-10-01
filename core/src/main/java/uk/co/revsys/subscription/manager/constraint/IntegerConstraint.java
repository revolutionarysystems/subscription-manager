package uk.co.revsys.subscription.manager.constraint;

public class IntegerConstraint implements Constraint{

    @Override
    public void check(String constraint, String value) throws FailedConstraintException {
        try{
            Integer.valueOf(value);
        }catch(NumberFormatException ex){
            throw new FailedConstraintException("'" + value + "' is not an integer");
        }
    }

}
