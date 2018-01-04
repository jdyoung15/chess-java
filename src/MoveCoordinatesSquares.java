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
    List<Integer> positions = new ArrayList<Integer>();

    // find position at coordinates
    int position = BoardPositioning.findPosition(fromPosition, moveCoordinates);
    if (position == BoardPositioning.INVALID_POSITION) {
      return positions;
    }
    positions.add(position);

    return positions;
  }

}
