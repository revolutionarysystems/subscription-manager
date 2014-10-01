package uk.co.revsys.subscription.manager.constraint;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BooleanConstraintTest {

    public BooleanConstraintTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCheck() throws Exception {
        BooleanConstraint constraint = new BooleanConstraint();
        constraint.check("boolean", "true");
        constraint.check("boolean", "false");
        try {
            constraint.check("boolean", "other");
            fail("Expected FailedConstraintException to be thrown");
        } catch (FailedConstraintException ex) {
            // pass
        }
    }

}
