import java.util.List;
import java.util.ArrayList;

public class MoveCoordinatesSquares {

  private MoveCoordinates moveCoordinates;
  private int fromPosition;

  public MoveCoordinatesSquares(MoveCoordinates moveCoordinates, int fromPosition) {
    this.moveCoordinates = moveCoordinates;
    this.fromPosition = fromPosition;
  }

  public List<Integer> positions() {
    BoardDirection horizontal = moveCoordinates.getHorizontal();
    int horizontalAmount = moveCoordinates.getHorizontalAmount();
    BoardDirection vertical = moveCoordinates.getVertical();
    int verticalAmount = moveCoordinates.getVerticalAmount();

    List<Integer> positions = new ArrayList<Integer>();

    // find position at coordinates
    if (Board.inBounds(fromPosition, horizontal, horizontalAmount, vertical, verticalAmount)) {
      positions.add(Board.calculateNewPosition(fromPosition, horizontal, horizontalAmount, vertical, verticalAmount));
    }

    return positions;
  }

}
