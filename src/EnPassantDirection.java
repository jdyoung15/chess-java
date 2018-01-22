import java.util.List;
import java.util.ArrayList;

public enum EnPassantDirection {

  LEFT(BoardDirection.LEFT),
  RIGHT(BoardDirection.RIGHT);

  private BoardDirection boardDirectionHorizontal;

  private EnPassantDirection(BoardDirection boardDirectionHorizontal) {
    this.boardDirectionHorizontal = boardDirectionHorizontal;
  }

  public Move findAttackingPawnMove(int fromPosition, Color currentPlayer) {
    return new Move(fromPosition, findAttackingPawnToPosition(fromPosition, currentPlayer));
  }

  private int findAttackingPawnToPosition(int fromPosition, Color currentPlayer) {
    BoardDirection vertical = BoardPositioning.findPawnDirection(currentPlayer);
    MoveCoordinates coordinates = new MoveCoordinates(boardDirectionHorizontal, 1, vertical, 1);
    return coordinates.findPosition(fromPosition);
  }

  public int findVictimPawnFromPosition(int fromPosition, Color currentPlayer) {
    BoardDirection vertical = BoardPositioning.findPawnDirection(currentPlayer);
    MoveCoordinates coordinates = new MoveCoordinates(boardDirectionHorizontal, 1, vertical, 2);
    return coordinates.findPosition(fromPosition);
  }

  public int findVictimPawnToPosition(int fromPosition, Color currentPlayer) {
    BoardDirection vertical = BoardPositioning.findPawnDirection(currentPlayer);
    MoveCoordinates coordinates = new MoveCoordinates(boardDirectionHorizontal, 1);
    return coordinates.findPosition(fromPosition);
  }

  public boolean canEnPassant(int fromPosition, Color currentPlayer, Board board, Moves previousMoves) {
    if (previousMoves.isEmpty()) {
      return false;
    }

    Square fromSquare = board.findSquare(fromPosition);
    // check if piece at this position is a pawn
    if (!fromSquare.isOccupied() 
      || fromSquare.containsOpponent(currentPlayer)
      || fromSquare.getPiece().getPieceType() != PieceType.PAWN) 
    {
      return false;
    }

    // check if opponent moved to adjacent square in previous move
    Move opponentPreviousMove = previousMoves.get(previousMoves.size() - 1);
    int opponentToPosition = opponentPreviousMove.getToPosition();
    if (opponentToPosition != findVictimPawnToPosition(fromPosition, currentPlayer)) {
      return false;
    }

    // check if opponent moved pawn in previous move
    Square opponentToSquare = board.findSquare(opponentToPosition);
    Piece opponentPiece = opponentToSquare.getPiece();
    if (opponentPiece.getPieceType() != PieceType.PAWN) {
      return false;
    }

    // check if opponent pawn moved two rows in previous move
    int opponentFromPosition = opponentPreviousMove.getFromPosition();
    if (opponentFromPosition != findVictimPawnFromPosition(fromPosition, currentPlayer)) {
      return false;
    }

    return true;
  }

}
