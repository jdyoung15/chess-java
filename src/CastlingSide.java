import java.util.List;
import java.util.stream.Collectors;

/**
 * The castling details for each side of the king,
 */
public enum CastlingSide {

  KINGSIDE(BoardDirection.RIGHT, 2),
  QUEENSIDE(BoardDirection.LEFT, 3);

  /* Number of squares between king's current position and king's new position after castling. */
  private static final int KING_TO_POSITION_DISTANCE = 2;
  /* Number of squares between king's current position and rook's new position after castling. */
  private static final int ROOK_TO_POSITION_DISTANCE = 1;

  /* The direction of the king when castling, left or right. */
  private BoardDirection horizontal;
  /* Number of squares between king and the castling rook. */
  private int numSquaresBetween;

  private CastlingSide(
    BoardDirection horizontal,
    int numSquaresBetween)
  {
    this.horizontal = horizontal;
    this.numSquaresBetween = numSquaresBetween;
  }

  public Move findKingMove(int kingStartPosition) {
    return new Move(kingStartPosition, findKingToPosition(kingStartPosition));
  }

  /* King's new position after castling. */
  private int findKingToPosition(int kingStartPosition) {
    MoveCoordinates coordinates = new MoveCoordinates(horizontal, KING_TO_POSITION_DISTANCE);
    return coordinates.findPosition(kingStartPosition);
  }

  public Move findRookMove(int kingStartPosition) {
    return new Move(findRookFromPosition(kingStartPosition), findRookToPosition(kingStartPosition));
  }

  /* Rook's original position before castling. */
  public int findRookFromPosition(int kingStartPosition) {
    // number of squares between king's start position and rook's position before castling
    int rookFromPositionDistance = numSquaresBetween + 1;

    MoveCoordinates coordinates = new MoveCoordinates(horizontal, rookFromPositionDistance);
    return coordinates.findPosition(kingStartPosition);
  }

  /* Rook's new position after castling. */
  public int findRookToPosition(int kingStartPosition) {
    MoveCoordinates coordinates = new MoveCoordinates(horizontal, ROOK_TO_POSITION_DISTANCE);
    return coordinates.findPosition(kingStartPosition);
  }

  public boolean canCastle(int kingFromPosition, Color currentPlayer, Board board, Moves previousMoves) {
    int kingStartPosition = BoardPositioning.findKingStartPosition(currentPlayer);

    // verify king is at start position
    if (kingFromPosition != kingStartPosition) {
      return false;
    }

    // verify king has not moved
    if (previousMoves.containsMovesFromPosition(kingStartPosition)) {
      return false;
    }

    // verify king is not currently in check
    if (board.isChecked(currentPlayer)) {
      return false;
    }

    // verify castling rook has not moved (and thus is currently at start position)
    if (previousMoves.containsMovesFromPosition(findRookFromPosition(kingStartPosition))) {
      return false;
    }

    // verify squares between king and rook are unoccupied
    for (int i = 1; i <= numSquaresBetween; i++) {
      MoveCoordinates coordinates = new MoveCoordinates(horizontal, i);
      int currentPosition = coordinates.findPosition(kingStartPosition);
      Square currentSquare = board.findSquare(currentPosition);
      if (currentSquare.isOccupied()) {
        return false;
      }
    }

    // verify king will not be in check at any square it travels through (including end square)
    for (int i = 1; i <= KING_TO_POSITION_DISTANCE; i++) {
      MoveCoordinates coordinates = new MoveCoordinates(horizontal, i);
      int currentPosition = coordinates.findPosition(kingStartPosition);
      Board copy = board.copy();
      copy.executeMove(new Move(kingStartPosition, currentPosition));
      if (copy.isChecked(currentPlayer)) {
        return false;
      }
    }

    return true;
  }

}
