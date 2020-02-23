package main.java.impl;

import main.java.containers.Board;
import main.java.containers.Coordinate;
import main.java.containers.Move;
import main.java.containers.Piece;
import main.java.core.PositionMoveFinder;
import main.java.util.Positioning;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for finding coordinate-based moves.
 */
public abstract class CoordinatePositionMoveFinder implements PositionMoveFinder {

  private List<Coordinate> coordinates;

  public CoordinatePositionMoveFinder(List<Coordinate> coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  public List<Move> findMoves(Board board, int fromPosition, List<Move> previousMoves) {
    List<Move> moves = new ArrayList<>();
    coordinates.forEach(coord -> moves.addAll(findMoves(board, fromPosition, coord)));
    return moves;
  }

  private List<Move> findMoves(Board board, int fromPosition, Coordinate coord) {
    List<Move> moves = new ArrayList<>();
    Piece piece = board.getPieceAt(fromPosition);

    int toPosition = Positioning.calculateNewPosition(fromPosition, coord);

    if (Positioning.isValidPosition(toPosition) && !board.playerAt(toPosition, piece.getColor())) {
      moves.add(new Move(fromPosition, toPosition));
    }

    return moves;
  }

}
