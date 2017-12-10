import java.util.List;
import java.util.ArrayList;

public class RookMoves {

  private Board board;
  private int fromPosition;
  private Color color;

  private static final MoveDirection[] moveDirections = {
    MoveDirection.UP,
    MoveDirection.RIGHT,
    MoveDirection.DOWN,
    MoveDirection.LEFT,
  };

  public RookMoves(Board board, int fromPosition, Color color) {
    this.board = board;
    this.fromPosition = fromPosition;
    this.color = color;
  }

  public List<Integer> positions() {
    List<Integer> positions = new ArrayList<Integer>();
    for (MoveDirection moveDirection : moveDirections) {
      List<Integer> directionPositions = new MoveDirectionSquares(moveDirection, fromPosition).positions();
      List<Square> directionSquares = board.findSquares(directionPositions);
      List<Integer> unblockedDirectionPositions = new UnblockedSquares(directionPositions, directionSquares, color).positions();
      positions.addAll(unblockedDirectionPositions);
    }
    return positions;
  }

}
