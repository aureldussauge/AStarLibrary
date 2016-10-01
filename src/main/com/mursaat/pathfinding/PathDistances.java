package com.mursaat.pathfinding;

/**
 * Created by Aurelien on 01/10/2016.
 */
public final class PathDistances {
    private PathDistances() {

    }

    /**
     * Computes and returns Manhattan Distance
     *
     * @param t1 First position
     * @param t2 Second position
     * @return An integer equals to the Manhattan Distance
     */
    public static int manhattanDistance(PathNodePosition t1, PathNodePosition t2) {
        return Math.abs(t2.x - t1.x) + Math.abs(t2.y - t1.y);
    }

    /**
     * Computes and returns Chebyshev Distance
     *
     * @param t1 First position
     * @param t2 Second position
     * @return An integer equals to the Chebyshev Distance
     */
    public static int chebyshevDistance(PathNodePosition t1, PathNodePosition t2) {
        return Math.max(Math.abs(t2.x - t1.x), Math.abs(t2.y - t1.y));
    }

    /**
     * Computes and returns Euclidian Distance
     *
     * @param t1 First position
     * @param t2 Second position
     * @return An integer equals to the Euclidian Distance
     */
    public static float euclidianDistance(PathNodePosition t1, PathNodePosition t2) {
        return (float) Math.sqrt(Math.pow(t2.x - t1.x, 2) + Math.pow(t2.y - t1.y, 2));
    }
}
