package uk.co.revsys.subscription.manager.constraint;

public class EnumerationConstraint implements Constraint{

    @Override
    public void check(String constraint, String value) throws FailedConstraintException {
        String validValuesString = constraint.substring(constraint.indexOf("(")+1, constraint.lastIndexOf(")"));
        String[] validValues = validValuesString.split(",");
        boolean match = false;
        for(String validValue: validValues){
            if(validValue.trim().equals(value)){
                match = true;
                break;
            }
        }
        if(!match){
            throw new FailedConstraintException("Expected one of [" + validValuesString + "], found " + value);
        }
    }

}
