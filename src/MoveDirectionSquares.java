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
    List<Integer> positions = new ArrayList<Integer>();

    int amount = 1;
    int position = BoardPositioning.findPosition(fromPosition, moveDirection, amount);
    while (position != BoardPositioning.INVALID_POSITION) {
      positions.add(position);
      amount++;
      position = BoardPositioning.findPosition(fromPosition, moveDirection, amount);
    }

    return positions;
  }

}
