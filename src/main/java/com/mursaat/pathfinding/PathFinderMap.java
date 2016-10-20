package com.mursaat.pathfinding;

/**
 * An interface which must be implemented to use {@link AStar#findPath}
 * We use it to determine if we can go to a position or not
 */
public interface PathFinderMap {

  /**
   * Returns a boolean which tells if we can walk at the given position.
   *
   * @param x The x position
   * @param y The y position
   * @return The boolean which tell if the position is traversable
   */
  boolean isTraversable(int x, int y);

  int getWidth();

  int getHeight();
}
