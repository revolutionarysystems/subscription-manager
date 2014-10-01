package uk.co.revsys.subscription.manager.constraint;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ColourCodeConstraintTest {

    public ColourCodeConstraintTest() {
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
        ColourCodeConstraint constraint = new ColourCodeConstraint();
        constraint.check("colorCode", "#ffc40d");
        constraint.check("colorCode", "#fff");
        try {
            constraint.check("colorCode", "#xyz");
            fail("Excepted FailedConstraintException to be thrown");
        } catch (FailedConstraintException ex) {
            //pass
        }
    }

}
