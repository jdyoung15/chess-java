import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardPieceRook extends BoardPieceDirectionBased {

  private static final Iterable<MoveDirection> moveDirections = Arrays.asList( 
    new MoveDirection(BoardDirection.UP),
    new MoveDirection(BoardDirection.RIGHT),
    new MoveDirection(BoardDirection.DOWN),
    new MoveDirection(BoardDirection.LEFT)
  );

  public BoardPieceRook(Color color, int position) {
    super(new Piece(color, PieceType.ROOK), position, moveDirections);
  }

}
