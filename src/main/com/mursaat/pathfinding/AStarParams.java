package com.mursaat.pathfinding;

/**
 * Parameters required by {@link AStar#findPath}
 */
public class AStarParams {

    /**
     * The map which determines where we are able to travel
     */
    private PathFinderMap map;

    /**
     * The start position
     */
    private PathNodePosition startPos;

    /**
     * The end position
     */
    private PathNodePosition endPos;

    /**
     * The movements allowed
     */
    private MoveType moveType;

    /**
     * The function used when estimating distance between a position and the end position
     */
    Heuristic heuristic;

    public AStarParams(PathFinderMap map, PathNodePosition startPos, PathNodePosition endPos) {
        this.map = map;
        this.startPos = startPos;
        this.endPos = endPos;
        this.moveType = MoveType.ORTHOGONAL_ONLY;
        this.heuristic = Heuristic.MANHATTAN_DISTANCE;
    }

    /**
     * Defines a group of movements (used by {@link AStar#findPath})
     */
    public enum MoveType {
        /**
         * Represents the movements ↑ ↓ ← →
         */
        ORTHOGONAL_ONLY,

        /**
         * Represents the movements ↑ ↓ ← → ↖ ↗ ↙ ↘
         */
        ORTHOGONAL_DIAGONAL
    }

    /**
     * Defines a function used to estimate the distance from a position to the end position (used by {@link AStar#findPath})
     */
    public enum Heuristic {
        /**
         * Fast, but this is not accurate when using {@link MoveType#ORTHOGONAL_DIAGONAL}
         */
        MANHATTAN_DISTANCE,

        /**
         * Fast, but this is not accurate when using {@link MoveType#ORTHOGONAL_DIAGONAL}
         */
        CHEBYSHEV_DISTANCE,

        /**
         * Slower, but this is the true distance if you're using {@link MoveType#ORTHOGONAL_DIAGONAL}
         */
        EUCLIDIAN_DISTANCE,


    }

    public PathFinderMap getMap() {
        return map;
    }

    public AStarParams setMap(PathFinderMap map) {
        this.map = map;
        return this;
    }

    public PathNodePosition getStartPos() {
        return startPos;
    }

    public AStarParams setStartPos(PathNodePosition startPos) {
        this.startPos = startPos;
        return this;
    }

    public PathNodePosition getEndPos() {
        return endPos;
    }

    public AStarParams setEndPos(PathNodePosition endPos) {
        this.endPos = endPos;
        return this;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public AStarParams setMoveType(MoveType moveType) {
        this.moveType = moveType;
        return this;
    }

    public Heuristic getHeuristic() {
        return heuristic;
    }

    public AStarParams setHeuristic(Heuristic heuristic) {
        this.heuristic = heuristic;
        return this;
    }
}
