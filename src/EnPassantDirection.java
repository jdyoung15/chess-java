import java.util.List;
import java.util.ArrayList;

public enum EnPassantDirection {

  LEFT(BoardDirection.LEFT),
  RIGHT(BoardDirection.RIGHT);

  private BoardDirection boardDirectionHorizontal;

  private EnPassantDirection(BoardDirection boardDirectionHorizontal) {
    this.boardDirectionHorizontal = boardDirectionHorizontal;
  }

  public int findAttackingPawnToPosition(int fromPosition, Color currentPlayer) {
    BoardDirection vertical = BoardPositioning.findDirection(currentPlayer);
    MoveCoordinates coordinates = new MoveCoordinates(boardDirectionHorizontal, 1, vertical, 1);
    return BoardPositioning.findPosition(fromPosition, coordinates);
  }

  public int findVictimPawnFromPosition(int fromPosition, Color currentPlayer) {
    BoardDirection vertical = BoardPositioning.findDirection(currentPlayer);
    MoveCoordinates coordinates = new MoveCoordinates(boardDirectionHorizontal, 1, vertical, 2);
    return BoardPositioning.findPosition(fromPosition, coordinates);
  }

  public int findVictimPawnToPosition(int fromPosition, Color currentPlayer) {
    BoardDirection vertical = BoardPositioning.findDirection(currentPlayer);
    MoveCoordinates coordinates = new MoveCoordinates(boardDirectionHorizontal, 1, vertical, 0);
    return BoardPositioning.findPosition(fromPosition, coordinates);
  }

  public boolean canEnPassant(int fromPosition, Color currentPlayer, Board board, List<Move> previousMoves) {
    if (previousMoves.isEmpty()) {
      return false;
    }

    Square fromSquare = board.findSquare(fromPosition);
    // check if piece at this position is a pawn
    if (!fromSquare.isOccupied() 
      || fromSquare.getPiece().getColor() != currentPlayer
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

    // check if adjacent square is occupied by opponent pawn
    Square opponentToSquare = board.findSquare(opponentToPosition);
    if (!opponentToSquare.isOccupied()) {
      return false;
    }
    Piece opponentPiece = opponentToSquare.getPiece();
    if (opponentPiece.getColor() == currentPlayer || opponentPiece.getPieceType() != PieceType.PAWN) {
      return false;
    }

    // check if opponent pawn moved two rows in previous move
    int opponentFromPosition = opponentPreviousMove.getFromPosition();
    //int opponentFromRow = BoardPositioning.findRow(opponentFromPosition);
    if (opponentFromPosition != findVictimPawnFromPosition(fromPosition, currentPlayer)) {
      return false;
    }

    return true;
  }

}
