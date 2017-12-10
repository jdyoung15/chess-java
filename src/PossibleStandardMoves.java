import java.util.List;
import java.util.ArrayList;

public class PossibleStandardMoves {
  
  private Board board;
  private int fromPosition;
  private PieceType pieceType;
  private Color color;

  public PossibleStandardMoves(Board board, int fromPosition, PieceType pieceType, Color color) {
    this.board = board;
    this.fromPosition = fromPosition;
    this.pieceType = pieceType;
    this.color = color;
  }

  public List<Integer> positions() {
    return PiecePossibleMovesFactory.getPiecePossibleMoves(pieceType, board, fromPosition, color).positions();
  }

}
