import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class KingPossibleMoves extends CoordinatesPiecePossibleMoves {

  private static final Iterable<MoveCoordinates> moveCoordinatesList = Arrays.asList(
    new MoveCoordinates(BoardDirection.RIGHT, 1, BoardDirection.UP, 1),
    new MoveCoordinates(BoardDirection.RIGHT, 1, BoardDirection.UP, 0),
    new MoveCoordinates(BoardDirection.RIGHT, 1, BoardDirection.DOWN, 1),
    new MoveCoordinates(BoardDirection.RIGHT, 0, BoardDirection.DOWN, 1),
    new MoveCoordinates(BoardDirection.LEFT, 1, BoardDirection.DOWN, 1),
    new MoveCoordinates(BoardDirection.LEFT, 1, BoardDirection.DOWN, 0),
    new MoveCoordinates(BoardDirection.LEFT, 1, BoardDirection.UP, 1),
    new MoveCoordinates(BoardDirection.LEFT, 0, BoardDirection.UP, 1)
  );

  public KingPossibleMoves(Board board, int fromPosition, Color color) {
    super(moveCoordinatesList, board, fromPosition, color);  
  }

}
