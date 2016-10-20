package com.mursaat.pathfinding;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Test {@link AStar}
 */
public class AStarTest {

  private PathFinderMap map1;
  private PathFinderMap map2;
  private PathFinderMap map3;
  private PathFinderMap map4;

  /**
   * Create all the objects used by the tests
   *
   * @throws Exception JUnit exception
   */
  @Before
  public void setUp() throws Exception {

    map1 = new PathFinderMap() {

      final int WALL = 1;
      final int FLOOR = 0;

      int[][] grid = new int[][]{
              {WALL, WALL, WALL, WALL, WALL, WALL, FLOOR, WALL},
              {WALL, FLOOR, FLOOR, FLOOR, WALL, WALL, WALL, WALL},
              {FLOOR, FLOOR, WALL, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR},
              {FLOOR, WALL, FLOOR, WALL, WALL, WALL, FLOOR, WALL},
              {FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, WALL},
      };

      @Override
      public boolean isTraversable(int x, int y) {
        return grid[y][x] == FLOOR;
      }

      @Override
      public int getWidth() {
        return grid[0].length;
      }

      @Override
      public int getHeight() {
        return grid.length;
      }
    };

    map2 = new PathFinderMap() {

      final int WALL = 1;
      final int FLOOR = 0;

      int[][] grid = new int[][]{
              {FLOOR, WALL, WALL},
              {FLOOR, FLOOR, WALL},
              {WALL, FLOOR, FLOOR}
      };

      @Override
      public boolean isTraversable(int x, int y) {
        return grid[y][x] == FLOOR;
      }

      @Override
      public int getWidth() {
        return grid[0].length;
      }

      @Override
      public int getHeight() {
        return grid.length;
      }
    };

    map3 = new PathFinderMap() {

      final int WALL = 1;
      final int FLOOR = 0;

      int[][] grid = new int[][]{
              {FLOOR, FLOOR, WALL, WALL, WALL, WALL, WALL, WALL},
              {WALL, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, FLOOR, WALL},
              {FLOOR, WALL, WALL, WALL, FLOOR, FLOOR, FLOOR, FLOOR},
              {FLOOR, WALL, WALL, WALL, FLOOR, FLOOR, FLOOR, FLOOR},
              {FLOOR, FLOOR, WALL, WALL, FLOOR, FLOOR, FLOOR, FLOOR},
      };

      @Override
      public boolean isTraversable(int x, int y) {
        return grid[y][x] == FLOOR;
      }

      @Override
      public int getWidth() {
        return grid[0].length;
      }

      @Override
      public int getHeight() {
        return grid.length;
      }
    };

    map4 = new PathFinderMap() {

      @Override
      public boolean isTraversable(int x, int y) {
        return y < 25 || y > 25;
      }

      @Override
      public int getWidth() {
        return 50;
      }

      @Override
      public int getHeight() {
        return 50;
      }
    };
  }

  /**
   * Force destroy all the objects used by the tests
   *
   * @throws Exception JUnit exception
   */
  @After
  public void tearDown() throws Exception {
    map1 = null;
    map2 = null;
    map3 = null;
  }

  /**
   * Test {@link AStar#findPath(AStarParams)}
   *
   * @throws Exception JUnit exception
   */
  @Test(timeout = 1000)
  public void testFindPath() throws Exception {
    // 1 - Test a Basic case
    AStarParams params1 = new AStarParams(map1, new PathNodePosition(0, 2), new PathNodePosition(7, 2));

    LinkedList<PathNodePosition> positions = AStar.findPath(params1);
    LinkedList<PathNodePosition> expectedPositions = new LinkedList<>();
    expectedPositions.add(new PathNodePosition(1, 2));
    expectedPositions.add(new PathNodePosition(1, 1));
    expectedPositions.add(new PathNodePosition(2, 1));
    expectedPositions.add(new PathNodePosition(3, 1));
    expectedPositions.add(new PathNodePosition(3, 2));
    expectedPositions.add(new PathNodePosition(4, 2));
    expectedPositions.add(new PathNodePosition(5, 2));
    expectedPositions.add(new PathNodePosition(6, 2));
    expectedPositions.add(new PathNodePosition(7, 2));

    assertEquals("The path returned by findPath() is not the one expected (Diagonal movements not allowed)", expectedPositions, positions);

    // 2 - Use diagonal movements
    params1.setMap(map2);
    params1.setNeighborsEnumerator(NeighborsEnumerator.ORTHO_DIAG_NEIGHBORS);
    params1.setStartPos(new PathNodePosition(0, 0));
    params1.setEndPos(new PathNodePosition(2, 2));
    positions = AStar.findPath(params1);

    expectedPositions.clear();
    expectedPositions.add(new PathNodePosition(1, 1));
    expectedPositions.add(new PathNodePosition(2, 2));

    assertEquals("The path returned by findPath() is not the one expected (Diagonal movements allowed)", expectedPositions, positions);

    // 3 - Set unreachable position
    params1.setMap(map1);
    params1.setStartPos(new PathNodePosition(0, 2));
    params1.setEndPos(new PathNodePosition(6, 0));
    positions = AStar.findPath(params1);

    expectedPositions.clear();

    assertEquals("findPath() must returns empty array when unreachable position is given", expectedPositions, positions);

    // 4 - Set start position on a wall (with a neighbor reachable)
    params1.setStartPos(new PathNodePosition(0, 1));
    params1.setEndPos(new PathNodePosition(7, 2));
    positions = AStar.findPath(params1);

    expectedPositions.clear();

    assertEquals("findPath() must returns empty array when a not traversable start position is given", expectedPositions, positions);

    // 5 - Use soft diagonal movements
    params1.setMap(map3);
    params1.setNeighborsEnumerator(NeighborsEnumerator.ORTHO_DIAG_SOFT_NEIGHBORS);
    params1.setStartPos(new PathNodePosition(0, 0));
    params1.setEndPos(new PathNodePosition(7, 4));
    positions = AStar.findPath(params1);

    expectedPositions.clear();
    expectedPositions.add(new PathNodePosition(1, 0));
    expectedPositions.add(new PathNodePosition(1, 1));
    expectedPositions.add(new PathNodePosition(2, 1));
    expectedPositions.add(new PathNodePosition(3, 1));
    expectedPositions.add(new PathNodePosition(4, 1));
    expectedPositions.add(new PathNodePosition(5, 2));
    expectedPositions.add(new PathNodePosition(6, 3));
    expectedPositions.add(new PathNodePosition(7, 4));

    assertEquals("The path returned by findPath() is not the one expected (Soft diagonal movements allowed)", expectedPositions, positions);

    // 6 - Performance test
    params1.setMap(map4);
    params1.setNeighborsEnumerator(NeighborsEnumerator.ORTHO_NEIGHBORS);
    params1.setHeuristic(DistanceCalculator.MANHATTAN_DISTANCE);
    params1.setStartPos(new PathNodePosition(0, 0));
    params1.setEndPos(new PathNodePosition(49, 49));
    AStar.findPath(params1);
  }

}