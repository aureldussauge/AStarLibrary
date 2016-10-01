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
        LinkedList<PathNode> closedList = new LinkedList<>();
        SortedArrayList<PathNode> openList = new SortedArrayList<>();
        openList.add(startNode);
        while (openList.size() != 0) {
            PathNode u = openList.remove(openList.size() - 1);
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

            for (PathNode v : getNeighbors(params.getMap(), u, params.getMoveType())) {
                boolean mustAdd = true;
                for (PathNode closed : closedList) {
                    if (v.equals(closed) && closed.cost < v.cost) {
                        mustAdd = false;
                    }
                }
                for (int i = 0; i < openList.size(); i++) {
                    if (v.equals(openList.get(i)) && openList.get(i).cost < v.cost) {
                        mustAdd = false;
                    }
                }
                if (mustAdd) {
                    v.heuristic = v.cost + params.heuristic.calculate(v.pos, params.getEndPos());
                    v.parent = u;
                    openList.insertSorted(v);
                }
            }

            closedList.add(u);
        }
        return new LinkedList<>();
    }



    /**
     * This list extends ArrayList
     * The added item is automatically sorted when using {@link SortedArrayList#insertSorted}
     * That class comes from @see <a href="http://stackoverflow.com/questions/4031572/sorted-array-list-in-java">Stack Overflow</a>
     *
     * @param <T>
     */
    private static class SortedArrayList<T> extends ArrayList<T> {

        @SuppressWarnings("unchecked")
        private void insertSorted(T value) {
            add(value);
            Comparable<T> cmp = (Comparable<T>) value;
            for (int i = size() - 1; i > 0 && cmp.compareTo(get(i - 1)) < 0; i--)
                Collections.swap(this, i, i - 1);
        }
    }

    /**
     * The square root of 2, used in {@link AStar#getNeighbors}
     */
    static final double SQRT_2 = 1.41421356237d;

    /**
     * Creates and returns a list which contains all the accessible neighbors of this node
     *
     * @param map The map which describe which positions are accessible
     * @return The list of neighbors nodes
     */
    private static List<PathNode> getNeighbors(PathFinderMap map, PathNode node, AStarParams.MoveType moveType) {
        ArrayList<PathNode> neighbors = new ArrayList<>();
        PathNodePosition pos = node.pos;

        if (pos.x != 0 && map.isTraversable(pos.x - 1, pos.y))
            neighbors.add(new PathNode(new PathNodePosition(pos.x - 1, pos.y), node.cost + 1, 0));
        if (pos.x != map.getWidth() - 1 && map.isTraversable(pos.x + 1, pos.y))
            neighbors.add(new PathNode(new PathNodePosition(pos.x + 1, pos.y), node.cost + 1, 0));
        if (pos.y != 0 && map.isTraversable(pos.x, pos.y - 1))
            neighbors.add(new PathNode(new PathNodePosition(pos.x, pos.y - 1), node.cost + 1, 0));
        if (pos.y != map.getHeight() - 1 && map.isTraversable(pos.x, pos.y + 1))
            neighbors.add(new PathNode(new PathNodePosition(pos.x, pos.y + 1), node.cost + 1, 0));

        if (moveType == AStarParams.MoveType.ORTHOGONAL_DIAGONAL) {
            if (pos.x != 0 && pos.y != 0 && map.isTraversable(pos.x - 1, pos.y - 1))
                neighbors.add(new PathNode(new PathNodePosition(pos.x - 1, pos.y - 1), node.cost + SQRT_2, 0));
            if (pos.x != 0 && pos.y != map.getHeight() - 1 && map.isTraversable(pos.x - 1, pos.y + 1))
                neighbors.add(new PathNode(new PathNodePosition(pos.x - 1, pos.y + 1), node.cost + SQRT_2, 0));
            if (pos.x != map.getWidth() - 1 && pos.y != 0 && map.isTraversable(pos.x + 1, pos.y - 1))
                neighbors.add(new PathNode(new PathNodePosition(pos.x + 1, pos.y - 1), node.cost + SQRT_2, 0));
            if (pos.x != map.getWidth() - 1 && pos.y != map.getHeight() - 1 && map.isTraversable(pos.x + 1, pos.y + 1))
                neighbors.add(new PathNode(new PathNodePosition(pos.x + 1, pos.y + 1), node.cost + SQRT_2, 0));
        }

        return neighbors;
    }

}
