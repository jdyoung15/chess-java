import java.util.List;
import java.util.stream.Collectors;

public enum CastlingSide {

  KINGSIDE(BoardDirection.RIGHT, 2),
  QUEENSIDE(BoardDirection.LEFT, 3);

  private static final int KING_TO_POSITION_OFFSET = 2;
  private static final int ROOK_TO_POSITION_OFFSET = 1;

  private BoardDirection horizontal;
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

  private int findKingToPosition(int kingStartPosition) {
    MoveCoordinates coordinates = 
      new MoveCoordinates(horizontal, KING_TO_POSITION_OFFSET, BoardDirection.NONE, 0);
    return coordinates.findPosition(kingStartPosition);
  }

  public Move findRookMove(int kingStartPosition) {
    return new Move(findRookFromPosition(kingStartPosition), findRookToPosition(kingStartPosition));
  }

  public int findRookFromPosition(int kingStartPosition) {
    MoveCoordinates coordinates = new MoveCoordinates(horizontal, numSquaresBetween + 1, BoardDirection.NONE, 0);
    return coordinates.findPosition(kingStartPosition);
  }

  public int findRookToPosition(int kingStartPosition) {
    MoveCoordinates coordinates = new MoveCoordinates(horizontal, ROOK_TO_POSITION_OFFSET, BoardDirection.NONE, 0);
    return coordinates.findPosition(kingStartPosition);
  }

  public boolean canCastle(int kingFromPosition, Color currentPlayer, Board board, Moves previousMoves) {
    int kingStartPosition = BoardPositioning.findKingStartPosition(currentPlayer);

    // check that the king is at start position
    if (kingFromPosition != kingStartPosition) {
      return false;
    }

    // check that the king has not moved
    if (previousMoves.containsMovesFromPosition(kingStartPosition)) {
      return false;
    }

    // check that king is not currently in check
    if (board.isChecked(currentPlayer)) {
      return false;
    }

    // check that castling rook has not moved (and by extension is currently at start position)
    if (previousMoves.containsMovesFromPosition(findRookFromPosition(kingStartPosition))) {
      return false;
    }

    // check that squares between king and rook are unoccupied
    for (int i = 1; i <= numSquaresBetween; i++) {
      MoveCoordinates coordinates = new MoveCoordinates(horizontal, i, BoardDirection.NONE, 0);
      int currentPosition = coordinates.findPosition(kingStartPosition);
      Square currentSquare = board.findSquare(currentPosition);
      if (currentSquare.isOccupied()) {
        return false;
      }
    }

    // check that king will not be in check at any square it travels through (including end square)
    for (int i = 1; i <= KING_TO_POSITION_OFFSET; i++) {
      MoveCoordinates coordinates = new MoveCoordinates(horizontal, i, BoardDirection.NONE, 0);
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
