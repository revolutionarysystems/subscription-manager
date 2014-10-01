package uk.co.revsys.subscription.manager.constraint;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntegerConstraintTest {

    public IntegerConstraintTest() {
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

    /**
     * Test of check method, of class IntegerConstraint.
     */
    @Test
    public void testCheck() throws Exception {
        IntegerConstraint constraint = new IntegerConstraint();
        constraint.check("integer", "1");
        constraint.check("integer", "123");
        try {
            constraint.check("integer", "abc");
            fail("Expected FailedConstraintException to be thrown");
        } catch (FailedConstraintException ex) {
            // pass
        }
        try {
            constraint.check("integer", "1.2");
            fail("Expected FailedConstraintException to be thrown");
        } catch (FailedConstraintException ex) {
            // pass
        }
    }

}
