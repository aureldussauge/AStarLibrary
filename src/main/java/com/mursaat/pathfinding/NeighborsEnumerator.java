package com.mursaat.pathfinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Define a method to get all neighbors of a position
 */
public abstract class NeighborsEnumerator {

  /**
   * Enumerates all neighbors of a node
   *
   * @param node The node we want to enumerate neighbors
   * @param map  The map which describe which positions are accessible
   * @return The list of neighbors nodes
   */
  public abstract List<PathNode> enumerateNeighbors(PathFinderMap map, PathNode node);

  public NeighborsEnumerator() {

  }

  /**
   * Returns the neighbors in these directions : NORTH, SOUTH, EAST, WEST
   */
  public static final NeighborsEnumerator ORTHO_NEIGHBORS = new NeighborsEnumerator() {
    @Override
    public List<PathNode> enumerateNeighbors(PathFinderMap map, PathNode node) {
      ArrayList<PathNode> neighbors = new ArrayList<>(4);
      PathNodePosition pos = node.pos;

      if (pos.x != 0 && map.isTraversable(pos.x - 1, pos.y))
        neighbors.add(new PathNode(new PathNodePosition(pos.x - 1, pos.y), node.cost + 1, 0));
      if (pos.x != map.getWidth() - 1 && map.isTraversable(pos.x + 1, pos.y))
        neighbors.add(new PathNode(new PathNodePosition(pos.x + 1, pos.y), node.cost + 1, 0));
      if (pos.y != 0 && map.isTraversable(pos.x, pos.y - 1))
        neighbors.add(new PathNode(new PathNodePosition(pos.x, pos.y - 1), node.cost + 1, 0));
      if (pos.y != map.getHeight() - 1 && map.isTraversable(pos.x, pos.y + 1))
        neighbors.add(new PathNode(new PathNodePosition(pos.x, pos.y + 1), node.cost + 1, 0));

      return neighbors;
    }
  };

  /**
   * The square root of 2, used in {@link NeighborsEnumerator#ORTHO_DIAG_NEIGHBORS}
   */
  private static final double SQRT_2 = 1.41421356237d;

  /**
   * Returns the neighbors in these directions : N, S, E, W, NE, NO, SE, SO
   * The difference with ORTHOGONAL_DIAGONAL_NEIGHBORS is that this won't allows to pass through walls in diagonal
   */
  public static final NeighborsEnumerator ORTHO_DIAG_NEIGHBORS = new NeighborsEnumerator() {
    @Override
    public List<PathNode> enumerateNeighbors(PathFinderMap map, PathNode node) {
      ArrayList<PathNode> neighbors = new ArrayList<>(4);
      PathNodePosition pos = node.pos;

      boolean north = false;
      boolean south = false;
      boolean east = false;
      boolean west = false;

      if (pos.x != 0 && map.isTraversable(pos.x - 1, pos.y)) {
        neighbors.add(new PathNode(new PathNodePosition(pos.x - 1, pos.y), node.cost + 1, 0));
        west = true;
      }
      if (pos.x != map.getWidth() - 1 && map.isTraversable(pos.x + 1, pos.y)) {
        neighbors.add(new PathNode(new PathNodePosition(pos.x + 1, pos.y), node.cost + 1, 0));
        east = true;
      }
      if (pos.y != 0 && map.isTraversable(pos.x, pos.y - 1)) {
        neighbors.add(new PathNode(new PathNodePosition(pos.x, pos.y - 1), node.cost + 1, 0));
        north = true;
      }
      if (pos.y != map.getHeight() - 1 && map.isTraversable(pos.x, pos.y + 1)) {
        neighbors.add(new PathNode(new PathNodePosition(pos.x, pos.y + 1), node.cost + 1, 0));
        south = true;
      }

      if (west && north && map.isTraversable(pos.x - 1, pos.y - 1))
        neighbors.add(new PathNode(new PathNodePosition(pos.x - 1, pos.y - 1), node.cost + SQRT_2, 0));
      if (west && south && map.isTraversable(pos.x - 1, pos.y + 1))
        neighbors.add(new PathNode(new PathNodePosition(pos.x - 1, pos.y + 1), node.cost + SQRT_2, 0));
      if (east && north && map.isTraversable(pos.x + 1, pos.y - 1))
        neighbors.add(new PathNode(new PathNodePosition(pos.x + 1, pos.y - 1), node.cost + SQRT_2, 0));
      if (east && south && map.isTraversable(pos.x + 1, pos.y + 1))
        neighbors.add(new PathNode(new PathNodePosition(pos.x + 1, pos.y + 1), node.cost + SQRT_2, 0));

      return neighbors;
    }
  };

}
