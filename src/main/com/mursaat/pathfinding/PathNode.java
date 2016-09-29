package com.mursaat.pathfinding;

import java.util.ArrayList;
import java.util.List;
import com.mursaat.pathfinding.AStarParams.*;

/**
 * This class represent a Node used in {@link AStar#findPath}
 */
class PathNode implements Comparable<PathNode> {
    /**
     * The square root of 2, used in {@link PathNode#getNeighbors}
     */
    static final float SQRT_2 = 1.41421356237f;

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
    float cost;

    /**
     * The cost to come on that node + The estimated cost to go to the end position
     */
    float heuristic;

    PathNode(PathNodePosition pos, float cost, float heuristic) {
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

    /**
     * Creates and returns a list which contains all the accessible neighbors of this node
     * @param map The map which describe which positions are accessible
     * @return The list of neighbors nodes
     */
    List<PathNode> getNeighbors(PathFinderMap map, MoveType moveType) {
        ArrayList<PathNode> neighbors = new ArrayList<>();

        if (pos.x != 0 && map.isTraversable(pos.x - 1, pos.y))
            neighbors.add(new PathNode(new PathNodePosition(pos.x - 1, pos.y), cost + 1, 0));
        if (pos.x != map.getWidth() - 1 && map.isTraversable(pos.x + 1, pos.y))
            neighbors.add(new PathNode(new PathNodePosition(pos.x + 1, pos.y), cost + 1, 0));
        if (pos.y != 0 && map.isTraversable(pos.x, pos.y - 1))
            neighbors.add(new PathNode(new PathNodePosition(pos.x, pos.y - 1), cost + 1, 0));
        if (pos.y != map.getHeight() - 1 && map.isTraversable(pos.x, pos.y + 1))
            neighbors.add(new PathNode(new PathNodePosition(pos.x, pos.y + 1), cost + 1, 0));

        if(moveType== MoveType.ORTHOGONAL_DIAGONAL) {
            if (pos.x != 0 && pos.y != 0 && map.isTraversable(pos.x - 1, pos.y - 1))
                neighbors.add(new PathNode(new PathNodePosition(pos.x - 1, pos.y - 1), cost + SQRT_2, 0));
            if (pos.x != 0 && pos.y != map.getHeight() - 1 && map.isTraversable(pos.x - 1, pos.y + 1))
                neighbors.add(new PathNode(new PathNodePosition(pos.x - 1, pos.y + 1), cost + SQRT_2, 0));
            if (pos.x != map.getWidth() - 1 && pos.y != 0 && map.isTraversable(pos.x + 1, pos.y - 1))
                neighbors.add(new PathNode(new PathNodePosition(pos.x + 1, pos.y - 1), cost + SQRT_2, 0));
            if (pos.x != map.getWidth() - 1 && pos.y != map.getHeight() - 1 && map.isTraversable(pos.x + 1, pos.y + 1))
                neighbors.add(new PathNode(new PathNodePosition(pos.x + 1, pos.y + 1), cost + SQRT_2, 0));
        }

        return neighbors;
    }
}
