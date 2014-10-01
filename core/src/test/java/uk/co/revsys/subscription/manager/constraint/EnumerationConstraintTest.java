package uk.co.revsys.subscription.manager.constraint;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class EnumerationConstraintTest {

    public EnumerationConstraintTest() {
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
        EnumerationConstraint constraint = new EnumerationConstraint();
        constraint.check("enumeration(abc, def, ghi)", "abc");
        constraint.check("enumeration(abc, def, ghi)", "def");
        constraint.check("enumeration(abc, def, ghi)", "ghi");
        try {
            constraint.check("enumeration(abc, def, ghi)", "xyz");
            fail("Expected FailedConstraintException to be thrown");
        } catch (FailedConstraintException ex) {
            // pass
        }
    }

}
