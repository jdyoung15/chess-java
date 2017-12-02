import java.util.List;
import java.util.ArrayList;

public class PossibleMoves {
  
  private Board board;
  private int fromPosition;
  private PieceType pieceType;
  private Color color;

  public PossibleMoves(Board board, int fromPosition, PieceType pieceType, Color color) {
    this.board = board;
    this.fromPosition = fromPosition;
    this.pieceType = pieceType;
    this.color = color;
  }

  public List<Integer> positions() {
    List<MoveDirection> moveDirections = new MoveDirections(pieceType, color).moveDirections();
    List<Integer> positions = new ArrayList<Integer>();

    for (MoveDirection moveDirection : moveDirections) {
      List<Integer> positionsDirection = 
        new DirectionSquares(fromPosition, moveDirection.getDirection()).positions();

      List<Square> squares = board.findSquares(positionsDirection);
      positionsDirection = 
        new FilteredDirectionSquares(positionsDirection, squares, moveDirection.getLimit(), color).positions();

      positions.addAll(positionsDirection);
    }

    return positions;
  }

}
