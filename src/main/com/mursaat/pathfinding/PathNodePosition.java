package com.mursaat.pathfinding;

/**
 * Represents a position on a {@link PathFinderMap}
 */
public class PathNodePosition {

    /**
     * x position
     */
    public int x;

    /**
     * y position
     */
    public int y;

    public PathNodePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PathNodePosition that = (PathNodePosition) o;

        if (x != that.x) return false;
        return y == that.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
