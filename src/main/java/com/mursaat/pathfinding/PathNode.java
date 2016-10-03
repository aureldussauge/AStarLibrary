package com.mursaat.pathfinding;

/**
 * This class represent a Node used in {@link AStar#findPath}
 */
class PathNode implements Comparable<PathNode> {

    /**
     * The position of the node
     */
    PathNodePosition pos;

    /**
     * The parent of this node
     */
    PathNode parent = null;

    /**
     * The cost to come on that node
     */
    double cost;

    /**
     * The cost to come on that node + The estimated cost to go to the end position
     */
    double heuristic;

    PathNode(PathNodePosition pos, double cost, double heuristic) {
        this.pos = pos;
        this.cost = cost;
        this.heuristic = heuristic;
    }

    @Override
    public int hashCode() {
        return pos.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PathNode other = (PathNode) obj;
        return !(this.pos != other.pos && (this.pos == null || !this.pos.equals(other.pos)));
    }

    @Override
    public int compareTo(PathNode p) {
        if (this.heuristic < p.heuristic) {
            return 1;
        } else if (this.heuristic == p.heuristic) {
            return 0;
        }
        return -1;
    }

}
