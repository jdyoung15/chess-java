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
    List<Integer> squarePositions = new ArrayList<Integer>();

    for (MoveDirection moveDirection : moveDirections) {
      List<Integer> squarePositionsDirection = 
        new DirectionSquares(fromPosition, moveDirection.getDirection()).positions();

      List<Square> squares = board.findSquares(squarePositionsDirection);
      squarePositionsDirection = new FilteredDirectionSquares(
        squarePositionsDirection, 
        squares, 
        moveDirection.getLimit(),
        color).positions();

      squarePositions.addAll(squarePositionsDirection);
    }

    return squarePositions;
  }

}
