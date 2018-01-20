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

  private List<Integer> findAllSquarePositions(int fromPosition, int limit) {
    List<Integer> positions = new ArrayList<Integer>();

    int amount = 1;
    int position = BoardPositioning.findPosition(fromPosition, vertical, amount, horizontal, amount);
    while (position != BoardPositioning.INVALID_POSITION && amount <= limit) {
      positions.add(position);
      amount++;
      position = BoardPositioning.findPosition(fromPosition, vertical, amount, horizontal, amount);
    }

    return positions;
  }

  public List<Move> findUnblockedMoves(int fromPosition, Board board, Color color, CheckSquare canMove, int limit) {
    List<Move> unblockedMoves = new ArrayList<Move>();

    List<Integer> positionsInDirection = findAllSquarePositions(fromPosition, limit);
    for (int toPosition : positionsInDirection) {
      Square square = board.findSquare(toPosition);
      if (canMove.test(square, color)) {
        unblockedMoves.add(new Move(fromPosition, toPosition));
      }
      if (new CheckSquareIsBlocking().test(square, color)) {
        break;
      }
    }

    return unblockedMoves;
  }

}
