import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for finding the moves possible under the En Passant rule.
 */
public class EnPassantPositionMoveFinderImpl implements PositionMoveFinder {

  @Override
  public List<Move> findMoves(Board board, int fromPosition, List<Move> previousMoves) {
    List<Move> moves = new ArrayList<>();

    if (previousMoves.isEmpty()) {
      return moves;
    }

    Move previousMove = previousMoves.get(previousMoves.size() - 1);
    int opponentTo = previousMove.getToPosition();
    Piece opponentPiece = board.getPieceAt(opponentTo);
    if (opponentPiece.getPieceType() != Piece.Type.PAWN) {
      return moves;
    }

    if (!Positioning.isAdjacent(fromPosition, opponentTo)) {
      return moves;
    }

    int opponentFrom = previousMove.getFromPosition();
    if (!Positioning.inStartRow(previousMove.getFromPosition(), opponentPiece)) {
      return moves;
    }

    List<Integer> between = Positioning.getColPositionsBetween(opponentFrom, opponentTo);

    if (between.size() != 1) {
      return moves;
    }

    int toPosition = between.get(0);
    List<Move> associatedMoves = new ArrayList<>();
    associatedMoves.add(new Move(opponentTo, Move.OFF_POSITION));
    moves.add(new Move(fromPosition, toPosition, associatedMoves));

    return moves;
  }

}
