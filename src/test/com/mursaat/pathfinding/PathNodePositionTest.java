package com.mursaat.pathfinding;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test {@link PathNodePosition}
 */
public class PathNodePositionTest {

    private PathNodePosition pos1;
    private PathNodePosition pos2;
    private PathNodePosition pos3;

    /**
     * Create all the objects used by the tests
     *
     * @throws Exception JUnit exception
     */
    @Before
    public void setUp() throws Exception {
        pos1 = new PathNodePosition(1, 4);
        pos2 = new PathNodePosition(1, 4);
        pos3 = new PathNodePosition(4, 1);
    }

    /**
     * Force destroy all the objects used by the tests
     *
     * @throws Exception JUnit exception
     */
    @After
    public void tearDown() throws Exception {
        pos1 = null;
        pos2 = null;
        pos3 = null;
    }

    /**
     * Test if constructor worked properly {@link PathNode#PathNode(PathNodePosition, float, float)}
     * @throws Exception JUnit exception
     */
    @Test
    public void testPathNodePosition() throws Exception {
        assertNotNull("The instance was not created", pos1);
        assertEquals("The property 'x' was not assigned correctly", pos1.x, 1);
        assertEquals("The property 'y' was not assigned correctly", pos1.y, 4);
    }

    /**
     * Test {@link PathNodePosition#equals(Object)}
     * @throws Exception JUnit exception
     */
    @Test
    public void testEquals() throws Exception {
        assertTrue("These objects should be equals", pos1.equals(pos2));
        assertTrue("The equals must be reflective", pos2.equals(pos1));
        assertTrue("These objects should not be equals", !pos1.equals(pos3));
    }

    /**
     * Test {@link PathNodePosition#hashCode()}
     * @throws Exception JUnit exception
     */
    @Test
    public void testHashCode() throws Exception {
        assertEquals("These objects should have the same hashcode", pos1.hashCode(), pos2.hashCode());
        assertNotEquals("These objects should not have the same hashcode", pos1.hashCode(), pos3.hashCode());
    }

}