import java.util.List;
import java.util.ArrayList;

public class Check {

  private Color currentPlayer;
  private Board board;

  public Check(Color currentPlayer, Board board) {
    this.currentPlayer = currentPlayer;
    this.board = board;
  }
  
  public boolean isCheck() {
    int kingPosition = board.findKingPosition(currentPlayer);

    List<Integer> opponentPositions = board.findOpponentPositions(currentPlayer);
    for (int position : opponentPositions) {
      Piece piece = board.findSquare(position).getPiece();
      List<Integer> possiblePositions = 
        new PossibleStandardMoves(board, position, piece.getPieceType(), piece.getColor()).positions();

      if (possiblePositions.contains(kingPosition)) {
        return true;
      }
    }
    return false;
  }

}
