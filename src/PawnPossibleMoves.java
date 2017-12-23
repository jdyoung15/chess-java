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

    BoardDirection pawnDirection = Board.findDirection(color);
    Iterable<MoveCoordinates> attackCoordinates = Arrays.asList(
      new MoveCoordinates(BoardDirection.LEFT, 1, pawnDirection, 1),
      new MoveCoordinates(BoardDirection.RIGHT, 1, pawnDirection, 1));

    CoordinatesPiecePossibleMoves attackPossibleMoves = 
      new CoordinatesPiecePossibleMoves(attackCoordinates, board, fromPosition, color);
    attackPossibleMoves.setCanMoveHere(new CheckSquareIsAttackable());
    positions.addAll(attackPossibleMoves.positions());

    MoveDirection pawnMoveDirection = new MoveDirection(BoardDirection.UP, BoardDirection.NONE);
    DirectionBasedPiecePossibleMoves pawnForwardMoves = 
      new DirectionBasedPiecePossibleMoves(Arrays.asList(pawnMoveDirection), board, fromPosition, color);
    pawnForwardMoves.setLimitPerDirection(2);

    positions.addAll(
      new UnblockedSquares(pawnForwardMoves.positions(), board, color, new CheckSquareIsOccupiable()).positions());
    return positions;

  }

}
