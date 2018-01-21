import java.util.List;
import java.util.ArrayList;

public class MoveDirection {
  
  private BoardDirection vertical;
  private BoardDirection horizontal;

  public MoveDirection(BoardDirection vertical, BoardDirection horizontal) {
    this.vertical = vertical;
    this.horizontal = horizontal;
  }

  public BoardDirection getVertical() {
    return vertical;
  }

  public BoardDirection getHorizontal() {
    return horizontal;
  }

  private List<Integer> findAllPositions(int fromPosition, int limit) {
    List<Integer> positions = new ArrayList<Integer>();

    int amount = 1;
    int position = BoardPositioning.findPosition(fromPosition, horizontal, amount, vertical, amount);
    while (position != BoardPositioning.INVALID_POSITION && amount <= limit) {
      positions.add(position);
      amount++;
      position = BoardPositioning.findPosition(fromPosition, horizontal, amount, vertical, amount);
    }

    return positions;
  }

  private List<Integer> filterUnblockedPositions(
    List<Integer> positionsInDirection, 
    Board board, 
    Color color, 
    CheckSquare canMove) 
  {
    List<Integer> unblockedPositions = new ArrayList<Integer>();

    for (int toPosition : positionsInDirection) {
      Square square = board.findSquare(toPosition);
      if (canMove.test(square, color)) {
        unblockedPositions.add(toPosition);
      }
      if (new CheckSquareIsBlocking().test(square, color)) {
        break;
      }
    }

    return unblockedPositions;
  }

  public Moves findUnblockedMoves(int fromPosition, Board board, Color color, CheckSquare canMove, int limit) {
    Moves unblockedMoves = new Moves();

    List<Integer> positionsInDirection = findAllPositions(fromPosition, limit);
    List<Integer> unblockedPositions = filterUnblockedPositions(positionsInDirection, board, color, canMove);

    for (int toPosition : unblockedPositions) {
      unblockedMoves.add(new Move(fromPosition, toPosition));
    }

    return unblockedMoves;
  }

}
