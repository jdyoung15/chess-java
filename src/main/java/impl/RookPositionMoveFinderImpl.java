package main.java.impl;

import main.java.containers.Direction;

import java.util.List;

/**
 * Responsible for finding a rook's possible moves.
 */
public class RookPositionMoveFinderImpl extends DirectionPositionMoveFinder {

  private static final List<Direction> DIRECTIONS = List.of(
    Direction.UP,
    Direction.RIGHT,
    Direction.DOWN,
    Direction.LEFT
  );

  public RookPositionMoveFinderImpl() {
    super(DIRECTIONS);
  }

}
