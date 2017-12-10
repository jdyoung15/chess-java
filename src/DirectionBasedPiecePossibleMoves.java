import java.util.List;
import java.util.ArrayList;

public class DirectionBasedPiecePossibleMoves implements PiecePossibleMoves {

  private Iterable<MoveDirection> moveDirections;
  private Board board;
  private int fromPosition;
  private Color color;

  public DirectionBasedPiecePossibleMoves(Iterable<MoveDirection> moveDirections, Board board, int fromPosition, Color color) {
    this.moveDirections = moveDirections;
    this.board = board;
    this.fromPosition = fromPosition;
    this.color = color;
  }

  public List<Integer> positions() {
    List<Integer> positions = new ArrayList<Integer>();
    for (MoveDirection moveDirection : moveDirections) {
      List<Integer> directionPositions = new MoveDirectionSquares(moveDirection, fromPosition).positions();
      List<Square> squares = board.findSquares(directionPositions);
      positions.addAll(new UnblockedSquares(directionPositions, squares, color).positions());
    }
    return positions;
  }

}
