package uk.co.revsys.subscription.manager.constraint;

public class FailedConstraintException extends Exception{

    public FailedConstraintException(String message) {
        super(message);
    }

    public FailedConstraintException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedConstraintException(Throwable cause) {
        super(cause);
    }

}
