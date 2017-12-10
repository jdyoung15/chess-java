import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class RookMoves extends DirectionBasedPiecePossibleMoves {

  private static final Iterable<MoveDirection> moveDirections = Arrays.asList( 
    MoveDirection.UP,
    MoveDirection.RIGHT,
    MoveDirection.DOWN,
    MoveDirection.LEFT
  );

  public RookMoves(Board board, int fromPosition, Color color) {
    super(moveDirections, board, fromPosition, color);
  }

}
