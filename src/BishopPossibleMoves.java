import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class BishopPossibleMoves extends DirectionBasedPiecePossibleMoves {

  private static final Iterable<MoveDirection> moveDirections = Arrays.asList( 
    new MoveDirection(BoardDirection.UP, BoardDirection.RIGHT),
    new MoveDirection(BoardDirection.DOWN, BoardDirection.RIGHT),
    new MoveDirection(BoardDirection.DOWN, BoardDirection.LEFT),
    new MoveDirection(BoardDirection.UP, BoardDirection.LEFT)
  );

  public BishopPossibleMoves(Board board, int fromPosition, Color color) {
    super(moveDirections, board, fromPosition, color);
  }

}
