package com.mursaat.pathfinding;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test {@link PathNodeTest}
 */
public class PathNodeTest {

    private PathNodePosition pos1;
    private PathNodePosition pos2;

    private PathNode node1;
    private PathNode node2;
    private PathNode node3;

    /**
     * Create all the objects used by the tests
     *
     * @throws Exception JUnit exception
     */
    @Before
    public void setUp() throws Exception {
        pos1 = new PathNodePosition(1, 4);
        pos2 = new PathNodePosition(4, 1);

        node1 = new PathNode(pos1, 2, 4);
        node2 = new PathNode(pos1, 1, 7);
        node3 = new PathNode(pos2, 2, 4);
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

        node1 = null;
        node2 = null;
        node3 = null;
    }

    /**
     * Test if constructor worked properly {@link PathNode#PathNode(PathNodePosition, float, float)}
     *
     * @throws Exception JUnit exception
     */
    @Test
    public void testPathNode() throws Exception {
        assertNotNull("The instance was not created", node1);
        assertEquals("The property 'pos' was not assigned correctly", node1.pos, pos1);
        assertEquals("The property 'cost' was not assigned correctly", node1.cost, 2d, 0.01d);
        assertEquals("The property 'heuristic' was not assigned correctly", node1.heuristic, 4d, 0.01d);
    }

    /**
     * Test {@link PathNode#hashCode()}
     *
     * @throws Exception JUnit exception
     */
    @Test
    public void testHashCode() throws Exception {
        assertEquals("These objects should have the same hashcode", node1.hashCode(), node2.hashCode());
        assertNotEquals("These objects should not have the same hashcode", node1.hashCode(), node3.hashCode());
    }

    /**
     * Test {@link PathNode#equals(Object)}
     *
     * @throws Exception JUnit exception
     */
    @Test
    public void testEquals() throws Exception {
        assertTrue("These objects should be equals", node1.equals(node2));
        assertTrue("The equals must be reflective", node2.equals(node1));
        assertTrue("These objects should not be equals", !node1.equals(node3));
    }

    /**
     * Test {@link PathNode#compareTo(PathNode)}
     *
     * @throws Exception JUnit exception
     */
    @Test
    public void testCompareTo() throws Exception {
        assertTrue("This comparison must returns 0.", node1.compareTo(node3) == 0);
        assertTrue("This comparison must returns a strictly positive value.", node2.compareTo(node1) < 0);
        assertTrue("This comparison must returns a negative value.", node1.compareTo(node2) > 0);
    }

}