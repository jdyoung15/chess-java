import java.util.List;
import java.util.ArrayList;

public class PossibleMoves {
  
  private Board board;
  private int fromPosition;
  private PieceType pieceType;
  private Color color;
  private List<Move> previousMoves;

  public PossibleMoves(Board board, int fromPosition, PieceType pieceType, Color color, List<Move> previousMoves) {
    this.board = board;
    this.fromPosition = fromPosition;
    this.pieceType = pieceType;
    this.color = color;
    this.previousMoves = previousMoves;
  }

  public List<Integer> positions() {
    List<Integer> positions = new ArrayList<Integer>();

    positions.addAll(new PossibleStandardMoves(board, fromPosition, pieceType, color).positions());
    System.out.println(positions.size());

    if (pieceType == PieceType.PAWN) {
      positions.addAll(new EnPassant(fromPosition, board, color, previousMoves).positions());
    }
    if (pieceType == PieceType.KING) {
      positions.addAll(new Castling(fromPosition, board, color, previousMoves).positions());
    }

    return positions;
  }

}
