import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DirectionBasedPiecePossibleMoves extends PiecePossibleMoves {

  private Iterable<MoveDirection> moveDirections;
  private int limitPerDirection = Integer.MAX_VALUE;

  public DirectionBasedPiecePossibleMoves(
    Iterable<MoveDirection> moveDirections, 
    Board board, 
    int fromPosition, 
    Color color)
  {
    super(board, fromPosition, color);
    this.moveDirections = moveDirections;
  }

  public DirectionBasedPiecePossibleMoves(
    Iterable<MoveDirection> moveDirections, 
    Board board, 
    int fromPosition, 
    Color color,
    int limitPerDirection,
    CheckSquare canMoveHere)
  {
    super(board, fromPosition, color, canMoveHere);
    this.moveDirections = moveDirections;
    this.limitPerDirection = limitPerDirection;
  }

  public List<Integer> positions() {
    List<Integer> positions = new ArrayList<Integer>();
    for (MoveDirection moveDirection : moveDirections) {
      List<Integer> directionPositions = new MoveDirectionSquares(moveDirection, fromPosition).positions();
      directionPositions = directionPositions
        .stream()
        .limit(limitPerDirection)
        .collect(Collectors.toList());

      positions.addAll(new UnblockedSquares(directionPositions, board, color, canMoveHere).positions());
    }
    return positions;
  }

}
