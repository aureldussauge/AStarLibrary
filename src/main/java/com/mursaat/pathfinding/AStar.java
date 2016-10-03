package com.mursaat.pathfinding;

import java.util.*;

/**
 * This class allows the users to use the static method {@link AStar#findPath} to
 * determine shortest path between two position, using A* algorithm
 */
public final class AStar {

    private AStar() {
    }

    public static void main(String[] args){

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

            for (PathNode v : params.getNeighborsEnumerator().enumerateNeighbors(params.getMap(), u)) {
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

}
