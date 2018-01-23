import java.util.List;
import java.util.ArrayList;

/* The en passant details for each side of the attacking pawn. */
public enum EnPassantDirection {

  LEFT(BoardDirection.LEFT),
  RIGHT(BoardDirection.RIGHT);

  private BoardDirection horizontal;

  private EnPassantDirection(BoardDirection horizontal) {
    this.horizontal = horizontal;
  }

  public Move findAttackingPawnMove(int fromPosition, Color currentPlayer) {
    return new Move(fromPosition, findAttackingPawnToPosition(fromPosition, currentPlayer));
  }

  /* The attacking pawn's position after en passant. */
  private int findAttackingPawnToPosition(int fromPosition, Color currentPlayer) {
    BoardDirection vertical = BoardPositioning.findPawnDirection(currentPlayer);
    MoveCoordinates coordinates = new MoveCoordinates(horizontal, 1, vertical, 1);
    return coordinates.findPosition(fromPosition);
  }

  /* The victim pawn's position before en passant. */
  public int findVictimPawnFromPosition(int fromPosition, Color currentPlayer) {
    BoardDirection vertical = BoardPositioning.findPawnDirection(currentPlayer);
    MoveCoordinates coordinates = new MoveCoordinates(horizontal, 1, vertical, 2);
    return coordinates.findPosition(fromPosition);
  }

  /* The victim pawn's position after en passant. */
  public int findVictimPawnToPosition(int fromPosition, Color currentPlayer) {
    BoardDirection vertical = BoardPositioning.findPawnDirection(currentPlayer);
    MoveCoordinates coordinates = new MoveCoordinates(horizontal, 1);
    return coordinates.findPosition(fromPosition);
  }

  public boolean canEnPassant(int fromPosition, Color currentPlayer, Board board, Moves previousMoves) {
    if (previousMoves.isEmpty()) {
      return false;
    }

    Square fromSquare = board.findSquare(fromPosition);
    // check if piece at this position is pawn belonging to current player
    if (!fromSquare.containsCurrentPlayer(currentPlayer) 
      || fromSquare.getPiece().getPieceType() != PieceType.PAWN) 
    {
      return false;
    }

    // verify opponent moved to adjacent square in previous move
    Move opponentPreviousMove = previousMoves.get(previousMoves.size() - 1);
    int opponentToPosition = opponentPreviousMove.getToPosition();
    if (opponentToPosition != findVictimPawnToPosition(fromPosition, currentPlayer)) {
      return false;
    }

    // verify opponent previous move piece was a pawn
    Square opponentToSquare = board.findSquare(opponentToPosition);
    Piece opponentPiece = opponentToSquare.getPiece();
    if (opponentPiece.getPieceType() != PieceType.PAWN) {
      return false;
    }

    // verify opponent pawn moved two rows in previous move (and thus moved from start row)
    int opponentFromPosition = opponentPreviousMove.getFromPosition();
    if (opponentFromPosition != findVictimPawnFromPosition(fromPosition, currentPlayer)) {
      return false;
    }

    return true;
  }

}
