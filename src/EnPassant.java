import java.util.List;
import java.util.ArrayList;

public class EnPassant {

  private int fromPosition;
  private Board board;
  private Color currentPlayer;
  private List<Move> previousMoves;

  public EnPassant(int fromPosition, Board board, Color currentPlayer, List<Move> previousMoves) {
    this.fromPosition = fromPosition;
    this.board = board;
    this.currentPlayer = currentPlayer;
    this.previousMoves = previousMoves;
  }

  public List<Integer> positions() {
    List<Integer> positions = new ArrayList<Integer>();
    if (previousMoves.isEmpty()) {
      return positions;
    }

    // check if opponent moved to adjacent square in previous move
    Move opponentPreviousMove = previousMoves.get(previousMoves.size() - 1);
    int opponentToPosition = opponentPreviousMove.getToPosition();
    if (!BoardPositioning.isAdjacent(fromPosition, opponentToPosition)) {
      return positions;
    }

    // check if adjacent square is occupied by opponent pawn
    Square opponentToSquare = board.findSquare(opponentToPosition);
    if (!opponentToSquare.isOccupied()) {
      return positions;
    }
    Piece opponentPiece = opponentToSquare.getPiece();
    if (opponentPiece.getColor() == currentPlayer || opponentPiece.getPieceType() != PieceType.PAWN) {
      return positions;
    }

    // check if opponent pawn moved two rows in previous move
    int opponentFromPosition = opponentPreviousMove.getFromPosition();
    int rowsMoved = Math.abs(BoardPositioning.findRow(opponentFromPosition) - BoardPositioning.findRow(opponentToPosition));
    if (rowsMoved < 2) {
      return positions;
    }

    int toRow = (BoardPositioning.findRow(opponentFromPosition) + BoardPositioning.findRow(opponentToPosition)) / 2;
    int toCol = BoardPositioning.findCol(opponentToPosition);
    int toPosition = BoardPositioning.findPosition(toRow, toCol);
    if (toPosition == BoardPositioning.INVALID_POSITION) {
      return positions;
    }

    positions.add(toPosition);
    return positions;
  }

}
