package impl;

import containers.*;
import core.PositionMoveFinder;
import util.Positioning;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for finding direction-based moves.
 */
public abstract class DirectionPositionMoveFinder implements PositionMoveFinder {

  private List<Direction> directions;

  public DirectionPositionMoveFinder(List<Direction> directions) {
    this.directions = directions;
  }

  @Override
  public List<Move> findMoves(Board board, int fromPosition, List<Move> previousMoves) {
    List<Move> moves = new ArrayList<>();
    directions.forEach(direction -> moves.addAll(findMoves(board, fromPosition, direction)));
    return moves;
  }

  private List<Move> findMoves(Board board, int fromPosition, Direction direction) {
    List<Move> moves = new ArrayList<>();
    Piece piece = board.getPieceAt(fromPosition);
    Color currentPlayer = piece.getColor();

    int toPosition = Positioning.calculateNewPosition(fromPosition, direction.getCoordinate());
    while (Positioning.isValidPosition(toPosition)) {
      if (!board.playerAt(toPosition, currentPlayer)) {
        moves.add(new Move(fromPosition, toPosition));
      }

      if (board.occupiedAt(toPosition)) {
        break;
      }

      toPosition = Positioning.calculateNewPosition(toPosition, direction.getCoordinate());
    }

    return moves;
  }

}
