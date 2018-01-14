import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardPieceRook extends BoardPieceDirectionBased {

  private static final Iterable<MoveDirection> moveDirections = Arrays.asList( 
    new MoveDirection(BoardDirection.UP, BoardDirection.NONE),
    new MoveDirection(BoardDirection.NONE, BoardDirection.RIGHT),
    new MoveDirection(BoardDirection.DOWN, BoardDirection.NONE),
    new MoveDirection(BoardDirection.NONE, BoardDirection.LEFT)
  );

  public BoardPieceRook(Color color, int position) {
    super(new Piece(color, PieceType.ROOK), position, moveDirections);
  }

}
