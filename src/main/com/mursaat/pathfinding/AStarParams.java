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
    DistanceCalculator heuristic;

    public AStarParams(PathFinderMap map, PathNodePosition startPos, PathNodePosition endPos) {
        this.map = map;
        this.startPos = startPos;
        this.endPos = endPos;
        this.moveType = MoveType.ORTHOGONAL_ONLY;
        this.heuristic = DistanceCalculator.MANHATTAN_DISTANCE;
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

    public DistanceCalculator getHeuristic() {
        return heuristic;
    }

    public AStarParams setHeuristic(DistanceCalculator heuristic) {
        this.heuristic = heuristic;
        return this;
    }
}
