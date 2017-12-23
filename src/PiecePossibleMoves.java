import java.util.List;
import java.util.ArrayList;

public abstract class PiecePossibleMoves {

  protected Board board;
  protected int fromPosition;
  protected Color color;
  protected CheckSquare canMoveHere = new CheckSquareIsOccupiableOrAttackable();

  public PiecePossibleMoves(Board board, int fromPosition, Color color) {
    this.board = board;
    this.fromPosition = fromPosition;
    this.color = color;
  }

  public PiecePossibleMoves(Board board, int fromPosition, Color color, CheckSquare canMoveHere) {
    this.board = board;
    this.fromPosition = fromPosition;
    this.color = color;
    this.canMoveHere = canMoveHere;
  }

  public abstract List<Integer> positions();

}
