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

  public abstract List<Integer> positions();

  public void setCanMoveHere(CheckSquare canMoveHere) {
    this.canMoveHere = canMoveHere;
  }

}
