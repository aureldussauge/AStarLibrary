package com.mursaat.pathfinding;

/**
 * Created by Aurelien on 01/10/2016.
 */
public abstract class DistanceCalculator {

    /**
     * Compute and returns distance
     *
     * @param x1 first position x
     * @param y1 first position y
     * @param x2 second position x
     * @param y2 second position y
     * @return double distance
     */
    public abstract double calculate(int x1, int y1, int x2, int y2);

    /**
     * Compute and returns distance
     *
     * @param pos1 first position
     * @param pos2 second position
     * @return double distance
     */
    public double calculate(PathNodePosition pos1, PathNodePosition pos2) {
        return calculate(pos1.x, pos1.y, pos2.x, pos2.y);
    }

    public DistanceCalculator() {

    }

    /**
     * Allows to calculate the @see <a href="https://en.wikipedia.org/wiki/Taxicab_geometry">Manhattan distance</a>
     */
    public static final DistanceCalculator MANHATTAN_DISTANCE = new DistanceCalculator() {
        @Override
        public double calculate(int x1, int y1, int x2, int y2) {
            return Math.abs(x2 - x1) + Math.abs(y2 - y1);
        }
    };

    /**
     * Allows to calculate the @see <a href="https://en.wikipedia.org/wiki/Chebyshev_distance">Chebyshev distance</a>
     */
    public static final DistanceCalculator CHEBYSHEV_DISTANCE = new DistanceCalculator() {
        @Override
        public double calculate(int x1, int y1, int x2, int y2) {
            return Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
        }
    };


    /**
     * Allows to calculate the @see <a href="https://en.wikipedia.org/wiki/Euclidean_distance">Euclidean distance</a>
     */
    public static final DistanceCalculator EUCLIDEAN_DISTANCE = new DistanceCalculator() {
        @Override
        public double calculate(int x1, int y1, int x2, int y2) {
            return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        }
    };
}
