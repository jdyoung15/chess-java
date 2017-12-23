import java.util.List;
import java.util.ArrayList;

public class UnblockedSquares {
  
  private List<Integer> positions;
  private Board board;
  private Color color;
  private CheckSquare canMove;
  
  public UnblockedSquares(List<Integer> positions, Board board, Color color, CheckSquare canMove) {
    this.positions = positions;
    this.board = board;
    this.color = color;
    this.canMove = canMove;
  }

  public List<Integer> positions() {
    List<Integer> unblockedPositions = new ArrayList<Integer>();

    for (int position : positions) {
      Square square = board.findSquare(position);
      if (canMove.test(square, color)) {
        unblockedPositions.add(position);
      }
      if (new CheckSquareIsBlocking().test(square, color)) {
        break;
      }
    }

    return unblockedPositions;
  }

}
