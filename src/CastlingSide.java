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

  public int findKingToPosition(int kingStartPosition) {
    return BoardPositioning.findPosition(
      kingStartPosition,
      new MoveCoordinates(horizontal, KING_TO_POSITION_OFFSET, BoardDirection.NONE, 0));
  }

  public int findRookFromPosition(int kingStartPosition) {
    return BoardPositioning.findPosition(
      kingStartPosition, 
      new MoveCoordinates(horizontal, numSquaresBetween + 1, BoardDirection.NONE, 0));
  }

  public int findRookToPosition(int kingStartPosition) {
    return BoardPositioning.findPosition(
      kingStartPosition,
      new MoveCoordinates(horizontal, ROOK_TO_POSITION_OFFSET, BoardDirection.NONE, 0));
  }

  public boolean canCastle(int kingFromPosition, Color currentPlayer, Board board, List<Move> previousMoves) {
    int kingStartPosition = BoardPositioning.findKingStartPosition(currentPlayer);

    // check that the king is at start position
    if (kingFromPosition != kingStartPosition) {
      return false;
    }

    // check that the king has not moved
    List<Move> kingMoves = 
      previousMoves
        .stream()
        .filter(m -> m.getFromPosition() == kingStartPosition)
        .collect(Collectors.toList());

    if (!kingMoves.isEmpty()) {
      return false;
    }

    // check that king is not currently in check
    if (board.isChecked(currentPlayer)) {
      return false;
    }

    // check that castling rook has not moved (and by extension is currently at start position)
    List<Move> rookMoves = 
      previousMoves
        .stream()
        .filter(m -> m.getFromPosition() == findRookFromPosition(kingStartPosition))
        .collect(Collectors.toList());

    if (!rookMoves.isEmpty()) {
      return false;
    }

    // check that squares between king and rook are unoccupied
    for (int i = 1; i <= numSquaresBetween; i++) {
      int currentPosition = BoardPositioning.findPosition(
        kingStartPosition, 
        new MoveCoordinates(horizontal, i, BoardDirection.NONE, 0));
      Square currentSquare = board.findSquare(currentPosition);
      if (currentSquare.isOccupied()) {
        return false;
      }
    }

    // check that king will not be in check at any square it travels through (including end square)
    for (int i = 1; i <= KING_TO_POSITION_OFFSET; i++) {
      int currentPosition = BoardPositioning.findPosition(
        kingStartPosition, 
        new MoveCoordinates(horizontal, i, BoardDirection.NONE, 0));
      if (currentPosition == BoardPositioning.INVALID_POSITION) {
        return false;
      }
      Board copy = board.copy();
      copy.move(kingStartPosition, currentPosition);
      if (copy.isChecked(currentPlayer)) {
        return false;
      }
    }

    return true;
  }

}
