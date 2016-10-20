package com.mursaat.pathfinding;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test {@link DistanceCalculator}
 */
public class DistanceCalculatorTest {

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
   * Test {@link DistanceCalculator#MANHATTAN_DISTANCE}
   *
   * @throws Exception JUnit exception
   */
  @Test
  public void testManhattanDistance() throws Exception {
    assertEquals("This distance is not correct", 0, DistanceCalculator.MANHATTAN_DISTANCE.calculate(pos1, pos1), 0.01);
    assertEquals("This distance is not correct", 8, DistanceCalculator.MANHATTAN_DISTANCE.calculate(pos1, pos2), 0.01);
    assertEquals("This distance is not correct", 7, DistanceCalculator.MANHATTAN_DISTANCE.calculate(pos2, pos3), 0.01);
  }

  /**
   * Test {@link DistanceCalculator#CHEBYSHEV_DISTANCE}
   *
   * @throws Exception JUnit exception
   */
  @Test
  public void testChebyshevDistance() throws Exception {
    assertEquals("This distance is not correct", 0, DistanceCalculator.CHEBYSHEV_DISTANCE.calculate(pos1, pos1), 0.01);
    assertEquals("This distance is not correct", 6, DistanceCalculator.CHEBYSHEV_DISTANCE.calculate(pos1, pos2), 0.01);
    assertEquals("This distance is not correct", 6, DistanceCalculator.CHEBYSHEV_DISTANCE.calculate(pos2, pos3), 0.01);
  }

  /**
   * Test {@link DistanceCalculator#EUCLIDEAN_DISTANCE}
   *
   * @throws Exception JUnit exception
   */
  @Test
  public void testEuclidianDistance() throws Exception {
    assertEquals("This distance is not correct", 0, DistanceCalculator.EUCLIDEAN_DISTANCE.calculate(pos1, pos1), 0.01);
    assertEquals("This distance is not correct", 6.324555, DistanceCalculator.EUCLIDEAN_DISTANCE.calculate(pos1, pos2), 0.01);
    assertEquals("This distance is not correct", 6.082762, DistanceCalculator.EUCLIDEAN_DISTANCE.calculate(pos2, pos3), 0.01);
  }

}