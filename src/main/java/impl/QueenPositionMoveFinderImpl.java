package main.java.impl;

import main.java.containers.Direction;

import java.util.List;

/**
 * Responsible for finding a queen's possible moves.
 */
public class QueenPositionMoveFinderImpl extends DirectionPositionMoveFinder {

  private static final List<Direction> DIRECTIONS = List.of(
    Direction.UP,
    Direction.DIAGONAL_UP_RIGHT,
    Direction.RIGHT,
    Direction.DIAGONAL_DOWN_RIGHT,
    Direction.DOWN,
    Direction.DIAGONAL_DOWN_LEFT,
    Direction.LEFT,
    Direction.DIAGONAL_UP_LEFT
  );

  public QueenPositionMoveFinderImpl() {
    super(DIRECTIONS);
  }

}
