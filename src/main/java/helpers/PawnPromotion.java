package helpers;

import containers.Board;
import containers.Color;
import containers.Move;
import containers.Piece;
import util.Positioning;

import java.util.EnumSet;

/**
 * Class to handle pawn promotion logic.
 */
public class PawnPromotion {

  public static final EnumSet<Piece.Type> CHOICES = EnumSet.of(
    Piece.Type.QUEEN,
    Piece.Type.ROOK,
    Piece.Type.BISHOP,
    Piece.Type.KNIGHT
  );

  /**
   * Returns whether the given move, which is assumed to have already been applied to the
   * given board, is eligible for pawn promotion.
   */
  public boolean isEligible(Board board, Color currentPlayer, Move move) {
    int toPosition = move.getToPosition();
    Piece piece = board.getPieceAt(toPosition);
    if (piece.getPieceType() != Piece.Type.PAWN) {
      return false;
    }

    return Positioning.inHomeRow(toPosition, Color.complementOf(currentPlayer));
  }

  /**
   * Updates the given board with the user's pawn promotion selection.
   */
  public void updateBoard(Board board, Color currentPlayer, Move move, String choice) {
    Piece.Type newPieceType = Piece.Type.findPieceType(choice).orElseThrow();
    Piece newPiece = new Piece(currentPlayer, newPieceType);
    board.setPiece(move.getToPosition(), newPiece);
  }

}
