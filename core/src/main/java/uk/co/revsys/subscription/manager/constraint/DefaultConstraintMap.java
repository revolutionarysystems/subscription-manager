package uk.co.revsys.subscription.manager.constraint;

import java.util.HashMap;

public class DefaultConstraintMap extends HashMap<String, Constraint>{

    public DefaultConstraintMap() {
        put("boolean", new BooleanConstraint());
        put("enumeration", new EnumerationConstraint());
        put("colorCode", new ColourCodeConstraint());
        put("integer", new IntegerConstraint());
        put("string", new AllowAnyConstraint());
        put("csl", new AllowAnyConstraint());
        put("langdict", new AllowAnyConstraint());
        put("resource", new AllowAnyConstraint());
    }

}
