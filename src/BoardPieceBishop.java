import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardPieceBishop extends BoardPieceDirectionBased {

  private static final Iterable<MoveDirection> moveDirections = Arrays.asList( 
    new MoveDirection(BoardDirection.UP, BoardDirection.RIGHT),
    new MoveDirection(BoardDirection.DOWN, BoardDirection.RIGHT),
    new MoveDirection(BoardDirection.DOWN, BoardDirection.LEFT),
    new MoveDirection(BoardDirection.UP, BoardDirection.LEFT)
  );

  public BoardPieceBishop(Color color, int position) {
    super(new Piece(color, PieceType.BISHOP), position, moveDirections);
  }

}
