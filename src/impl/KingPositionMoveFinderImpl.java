package impl;

import containers.Board;
import containers.Coordinate;
import containers.Move;
import core.PositionMoveFinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for finding a king's possible moves.
 */
public class KingPositionMoveFinderImpl extends CoordinatePositionMoveFinder {

  private static final List<Coordinate> COORDINATES = List.of(
    new Coordinate(0, 1),
    new Coordinate(1, 1),
    new Coordinate(1, 0),
    new Coordinate(1, -1),
    new Coordinate(0, -1),
    new Coordinate(-1, -1),
    new Coordinate(-1, 0),
    new Coordinate(-1, 1)
  );

  private PositionMoveFinder castlingPositionMoveFinder;

  public KingPositionMoveFinderImpl() {
    super(COORDINATES);
    castlingPositionMoveFinder = new CastlingPositionMoveFinderImpl();
  }

  @Override
  public List<Move> findMoves(Board board, int fromPosition, List<Move> previousMoves) {
    List<Move> moves = new ArrayList<>();
    moves.addAll(super.findMoves(board, fromPosition, previousMoves));
    moves.addAll(castlingPositionMoveFinder.findMoves(board, fromPosition, previousMoves));
    return moves;
  }

}
