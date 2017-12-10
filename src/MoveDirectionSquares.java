import java.util.List;
import java.util.ArrayList;

public class MoveDirectionSquares {

  private BoardDirection horizontal;
  private BoardDirection vertical;
  private int fromPosition;

  public MoveDirectionSquares(MoveDirection moveDirection, int fromPosition) {
    this.horizontal = moveDirection.getHorizontal();
    this.vertical = moveDirection.getVertical();
    this.fromPosition = fromPosition;
  }

  public List<Integer> positions() {
    List<Integer> positions = new ArrayList<Integer>();
    int position = fromPosition;

    while (Board.inBounds(position, horizontal, vertical)) {
      position = Board.calculateNewPosition(position, horizontal, vertical);
      positions.add(position);
    }

    return positions;
  }

}
