import java.util.List;
import java.util.ArrayList;

public class ValidMoves {
  
  private int fromPosition;
  private List<Integer> toPositions;
  private Board board;
  private Color currentPlayer;

  public ValidMoves(int fromPosition, List<Integer> toPositions, Board board, Color currentPlayer) {
    this.fromPosition = fromPosition;
    this.toPositions = toPositions;
    this.board = board;
    this.currentPlayer = currentPlayer;
  }

  public List<Integer> positions() {
    List<Integer> validPositions = new ArrayList<Integer>();
    for (int toPosition : toPositions) {
      Board copy = board.copy();  
      copy.move(fromPosition, toPosition);
      Check check = new Check(currentPlayer, copy.findKingPosition(currentPlayer), copy);
      if (!check.isCheck()) {
        validPositions.add(toPosition);
      }
    }
    return validPositions;
  }

}
