import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class KnightPossibleMoves extends CoordinatesPiecePossibleMoves {

  private static final Iterable<MoveCoordinates> moveCoordinatesList = Arrays.asList(
    new MoveCoordinates(BoardDirection.RIGHT, 1, BoardDirection.UP, 2),
    new MoveCoordinates(BoardDirection.RIGHT, 2, BoardDirection.UP, 1),
    new MoveCoordinates(BoardDirection.RIGHT, 2, BoardDirection.DOWN, 1),
    new MoveCoordinates(BoardDirection.RIGHT, 1, BoardDirection.DOWN, 2),
    new MoveCoordinates(BoardDirection.LEFT, 1, BoardDirection.UP, 2),
    new MoveCoordinates(BoardDirection.LEFT, 2, BoardDirection.UP, 1),
    new MoveCoordinates(BoardDirection.LEFT, 2, BoardDirection.DOWN, 1),
    new MoveCoordinates(BoardDirection.LEFT, 1, BoardDirection.DOWN, 2)
  );

  public KnightPossibleMoves(Board board, int fromPosition, Color color) {
    super(moveCoordinatesList, board, fromPosition, color);  
  }

}
