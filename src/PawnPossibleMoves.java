import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class PawnPossibleMoves extends PiecePossibleMoves {

  public PawnPossibleMoves(
    Board board, 
    int fromPosition, 
    Color color) 
  {
    super(board, fromPosition, color);
  }

  public List<Integer> positions() {
    List<Integer> positions = new ArrayList<Integer>();

    BoardDirection pawnDirection = BoardPositioning.findDirection(color);
    Iterable<MoveCoordinates> attackCoordinates = Arrays.asList(
      new MoveCoordinates(BoardDirection.LEFT, 1, pawnDirection, 1),
      new MoveCoordinates(BoardDirection.RIGHT, 1, pawnDirection, 1));

    positions.addAll(
      new CoordinatesPiecePossibleMoves(
        attackCoordinates, 
        board, 
        fromPosition, 
        color, 
        new CheckSquareIsAttackable()).positions());

    MoveDirection pawnMoveDirection = new MoveDirection(pawnDirection, BoardDirection.NONE);
    int pawnStartRow = 
      color == Color.WHITE ? BoardPositioning.PAWN_WHITE_START_ROW : BoardPositioning.PAWN_BLACK_START_ROW;
    int numSquaresCanAdvance = 
      BoardPositioning.findRow(fromPosition) == pawnStartRow ? 2 : 1;
    positions.addAll(
      new DirectionBasedPiecePossibleMoves(
        Arrays.asList(pawnMoveDirection), 
        board, 
        fromPosition, 
        color, 
        numSquaresCanAdvance,
        new CheckSquareIsOccupiable()).positions());

    return positions;

  }

}
