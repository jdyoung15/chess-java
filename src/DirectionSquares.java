import java.util.List;
import java.util.ArrayList;

public class DirectionSquares {

  private int fromPosition;
  private Direction direction;

  public DirectionSquares(int fromPosition, Direction direction) {
    this.fromPosition = fromPosition;
    this.direction = direction;
  }

  public List<Integer> positions() {
    List<Integer> positions = new ArrayList<Integer>();

    int position = fromPosition;
    int horizontal = direction.getHorizontal();
    int vertical = direction.getVertical();

    while (Board.inBounds(position, horizontal, vertical)) {
      position = Board.calculateNewPosition(position, horizontal, vertical);
      positions.add(position);
    }

    return positions;
  }

}
