package com.mursaat.pathfinding;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aurelien on 30/09/2016.
 */
public class PathNodePositionTest {

    private PathNodePosition pos1;
    private PathNodePosition pos2;
    private PathNodePosition pos3;

    @Before
    public void initialize() {
        pos1 = new PathNodePosition(1, 4);
        pos2 = new PathNodePosition(1, 4);
        pos3 = new PathNodePosition(4, 1);
    }

    @Test
    public void pathNodePosition() throws Exception {
        assertNotNull("The instance was not created", pos1);
        assertEquals("The property 'x' was not assigned correctly", pos1.x, 1);
        assertEquals("The property 'y' was not assigned correctly", pos1.y, 4);
    }

    @Test
    public void testEquals() throws Exception {
        assertTrue("These objects should be equals", pos1.equals(pos2));
        assertTrue("The equals must be reflective", pos2.equals(pos1));
        assertTrue("These objects should not be equals", !pos1.equals(pos3));
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals("These objects should have the same hashcode", pos1.hashCode(), pos2.hashCode());
        assertNotEquals("These objects should not have the same hashcode", pos1.hashCode(), pos3.hashCode());
    }

    @After
    public void clean() {
        pos1 = null;
        pos2 = null;
        pos3 = null;
    }
}