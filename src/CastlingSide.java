import java.util.List;
import java.util.stream.Collectors;

public enum CastlingSide {

  KINGSIDE(new MoveDirection(BoardDirection.NONE, BoardDirection.RIGHT), 2, 2, 3, 1),
  QUEENSIDE(new MoveDirection(BoardDirection.NONE, BoardDirection.LEFT), 2, 3, 4, 1);

  private MoveDirection moveDirection;
  private int kingToPosition;
  private int numSquaresBetween;
  private int rookFromPosition;
  private int rookToPosition;

  private CastlingSide(MoveDirection moveDirection, int kingToPosition, int numSquaresBetween, int rookFromPosition, int rookToPosition) 
  {
    this.moveDirection = moveDirection;
    this.kingToPosition = kingToPosition;
    this.numSquaresBetween = numSquaresBetween;
    this.rookFromPosition = rookFromPosition;
    this.rookToPosition = rookToPosition;
  }

  public int findKingToPosition(int kingStartPosition) {
    return BoardPositioning.findPosition(kingStartPosition, moveDirection, kingToPosition);
  }

  public int findRookFromPosition(int kingStartPosition) {
    return BoardPositioning.findPosition(kingStartPosition, moveDirection, rookFromPosition);
  }

  public int findRookToPosition(int kingStartPosition) {
    return BoardPositioning.findPosition(kingStartPosition, moveDirection, rookToPosition);
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
    for (int i = 1; i < numSquaresBetween + 1; i++) {
      int currentPosition = BoardPositioning.findPosition(kingStartPosition, moveDirection, i);
      Square currentSquare = board.findSquare(currentPosition);
      if (currentSquare.isOccupied()) {
        return false;
      }
    }

    // check that king will not be in check at any square it travels through (including end square)
    for (int i = 1; i < kingToPosition + 1; i++) {
      int currentPosition = BoardPositioning.findPosition(kingStartPosition, moveDirection, i);
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
