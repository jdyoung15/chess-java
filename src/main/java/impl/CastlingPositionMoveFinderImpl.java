package impl;

import containers.*;
import core.PlayerLegalMoveFinder;
import core.PositionMoveFinder;
import util.Positioning;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for finding possible moves under the non-check requirements of the
 * Castling rule. We don't check if the king is ever exposed to check, as that will
 * be done in {@link PlayerLegalMoveFinder}.
 */
public class CastlingPositionMoveFinderImpl implements PositionMoveFinder {

  /**
   * Represents the positions associated with a king's castling in a direction.
   * These positions are relative to the king's start position.
   */
  private enum CastlingSide {

    RIGHT(
      new Coordinate(2, 0),
      new Coordinate(3, 0),
      new Coordinate(1, 0)),
    LEFT(
      new Coordinate(-2, 0),
      new Coordinate(-4, 0),
      new Coordinate(-1, 0));

    private Coordinate kingTo;
    private Coordinate rookStart;
    private Coordinate rookTo;

    CastlingSide(
      Coordinate kingTo,
      Coordinate rookStart,
      Coordinate rookTo)
    {
      this.kingTo = kingTo;
      this.rookStart = rookStart;
      this.rookTo = rookTo;
    }

  }

  @Override
  public List<Move> findMoves(Board board, int fromPosition, List<Move> previousMoves) {
    List<Move> moves = new ArrayList<>();
    Piece piece = board.getPieceAt(fromPosition);
    Color currentPlayer = piece.getColor();

    int kingStart = Positioning.getKingStartPosition(currentPlayer);
    boolean kingHasMoved = containsMoveFrom(previousMoves, kingStart) || fromPosition != kingStart;
    if (kingHasMoved) {
      return moves;
    }

    for (CastlingSide side : CastlingSide.values()) {
      int rookStart = Positioning.calculateNewPosition(kingStart, side.rookStart);
      boolean rookHasMoved = containsMoveFrom(previousMoves, rookStart);
      if (rookHasMoved) {
        continue;
      }

      List<Integer> between = Positioning.getRowPositionsBetween(kingStart, rookStart);
      boolean anyPieceBetween = between.stream().anyMatch(board::occupiedAt);
      if (anyPieceBetween) {
        continue;
      }

      int kingTo = Positioning.calculateNewPosition(kingStart, side.kingTo);

      List<Move> associatedMoves = new ArrayList<>();
      int rookTo = Positioning.calculateNewPosition(kingStart, side.rookTo);
      associatedMoves.add(new Move(rookStart, rookTo));

      List<Integer> kingPositionsTraveled =
        Positioning.getRowPositionsBetween(kingStart, kingTo);

      if (kingPositionsTraveled == null) {
        System.out.println("null kingpositionstraveled");
      }

      moves.add(new Move(kingStart, kingTo, associatedMoves, kingPositionsTraveled));
    }

    return moves;
  }

  /**
   * Returns whether the given moves contains a move from the given position.
   */
  private boolean containsMoveFrom(List<Move> moves, int fromPosition) {
    return moves.stream().anyMatch(m -> m.getFromPosition() == fromPosition);
  }

}
