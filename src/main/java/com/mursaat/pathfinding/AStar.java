package com.mursaat.pathfinding;

import java.util.*;

/**
 * This class allows the users to use the static method {@link AStar#findPath} to
 * determine shortest path between two position, using A* algorithm
 */
public final class AStar {

  private AStar() {
  }

  /**
   * Use A* algorithm to compute the shortest path between two positions
   * If the end position is unreachable, it will returns an empty List
   * If the start position is not defined as traversable on the map, it will returns an empty List
   *
   * @param params The parameters used to compute the path
   * @return A LinkedList containing all positions in the shortest path
   */
  public static LinkedList<PathNodePosition> findPath(AStarParams params) {
    // If the start position is not traversable, we return an empty list
    if (!params.getMap().isTraversable(params.getStartPos().x, params.getStartPos().y)) {
      return new LinkedList<>();
    }

    PathNode startNode = new PathNode(params.getStartPos(), 0, 0);
    HashMap<PathNode, PathNode> closedMap = new HashMap<>();
    HashMap<PathNode, PathNode> openMap = new HashMap<>();
    PriorityQueue<PathNode> openSet = new PriorityQueue<>();
    openMap.put(startNode, startNode);
    openSet.add(startNode);
    while (!openSet.isEmpty()) {
      PathNode u = openSet.poll();
      openMap.remove(u);
      if (u.pos.equals(params.getEndPos())) {
        LinkedList<PathNodePosition> path = new LinkedList<>();
        PathNode parent = u.parent;
        while (parent != null) {
          path.add(parent.pos);
          parent = parent.parent;
        }
        Collections.reverse(path);
        path.add(params.getEndPos());
        path.removeFirst();
        return path;
      }

      for (PathNode v : params.getNeighborsEnumerator().enumerateNeighbors(params.getMap(), u)) {
        boolean mustAdd = true;
        PathNode closed = closedMap.get(v);
        if (closed != null && closed.cost < v.cost) {
          mustAdd = false;
        }
        if (mustAdd) {
          PathNode open = openMap.get(v);
          if (open != null && open.cost < v.cost) {
            mustAdd = false;
          }
          if (mustAdd) {
            v.heuristic = v.cost + params.heuristic.calculate(v.pos, params.getEndPos());
            v.parent = u;

            openMap.put(v, v);
            openSet.add(v);
          }
        }
      }

      closedMap.put(u, u);
    }
    return new LinkedList<>();
  }
}
