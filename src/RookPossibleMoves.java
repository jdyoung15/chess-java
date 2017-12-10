import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class RookPossibleMoves extends DirectionBasedPiecePossibleMoves {

  private static final Iterable<MoveDirection> moveDirections = Arrays.asList( 
    new MoveDirection(BoardDirection.UP, BoardDirection.NONE),
    new MoveDirection(BoardDirection.NONE, BoardDirection.RIGHT),
    new MoveDirection(BoardDirection.DOWN, BoardDirection.NONE),
    new MoveDirection(BoardDirection.NONE, BoardDirection.LEFT)
  );

  public RookPossibleMoves(Board board, int fromPosition, Color color) {
    super(moveDirections, board, fromPosition, color);
  }

}
