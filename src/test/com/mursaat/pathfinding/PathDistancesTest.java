package com.mursaat.pathfinding;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test {@link PathDistances}
 */
public class PathDistancesTest {

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
        pos1 = new PathNodePosition(0, 0);
        pos2 = new PathNodePosition(2, 6);
        pos3 = new PathNodePosition(-4, 5);
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
     * Test {@link PathDistances#manhattanDistance(PathNodePosition, PathNodePosition)}
     *
     * @throws Exception JUnit exception
     */
    @Test
    public void testManhattanDistance() throws Exception {
        assertEquals("This distance is not correct", 0, PathDistances.manhattanDistance(pos1, pos1));
        assertEquals("This distance is not correct", 8, PathDistances.manhattanDistance(pos1, pos2));
        assertEquals("This distance is not correct", 7, PathDistances.manhattanDistance(pos2, pos3));
    }

    /**
     * Test {@link PathDistances#chebyshevDistance(PathNodePosition, PathNodePosition)}
     *
     * @throws Exception JUnit exception
     */
    @Test
    public void testChebyshevDistance() throws Exception {
        assertEquals("This distance is not correct", 0, PathDistances.chebyshevDistance(pos1, pos1));
        assertEquals("This distance is not correct", 6, PathDistances.chebyshevDistance(pos1, pos2));
        assertEquals("This distance is not correct", 6, PathDistances.chebyshevDistance(pos2, pos3));
    }

    /**
     * Test {@link PathDistances#euclidianDistance(PathNodePosition, PathNodePosition)}
     *
     * @throws Exception JUnit exception
     */
    @Test
    public void testEuclidianDistance() throws Exception {
        assertEquals("This distance is not correct", 0, PathDistances.euclidianDistance(pos1, pos1), 0.01);
        assertEquals("This distance is not correct", 6.324555, PathDistances.euclidianDistance(pos1, pos2), 0.01);
        assertEquals("This distance is not correct", 6.082762, PathDistances.euclidianDistance(pos2, pos3), 0.01);
    }

}