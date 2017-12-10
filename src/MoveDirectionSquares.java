import java.util.List;
import java.util.ArrayList;

public class MoveDirectionSquares {

  private MoveDirection moveDirection;
  private int fromPosition;

  public MoveDirectionSquares(MoveDirection moveDirection, int fromPosition) {
    this.moveDirection = moveDirection;
    this.fromPosition = fromPosition;
  }

  public List<Integer> positions() {
    BoardDirection horizontal = moveDirection.getHorizontal();
    BoardDirection vertical = moveDirection.getVertical();

    List<Integer> positions = new ArrayList<Integer>();
    int position = fromPosition;

    while (Board.inBounds(position, horizontal, 1, vertical, 1)) {
      position = Board.calculateNewPosition(position, horizontal, 1, vertical, 1);
      positions.add(position);
    }

    return positions;
  }

}
