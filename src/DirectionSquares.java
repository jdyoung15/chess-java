import java.util.List;
import java.util.ArrayList;

public class DirectionSquares {

  private List<SquarePosition> squarePositions;

  public DirectionSquares(SquarePosition fromPosition, Direction direction) {
    squarePositions = calculateSquarePositions(fromPosition, direction);
  }

  private List<SquarePosition> calculateSquarePositions(SquarePosition fromPosition, Direction direction) {
    //System.out.println(String.format("direction: %s, fromPosition: %s", direction.name(), fromPosition.toString()));
    //System.out.println(String.format("position: %d", fromPosition.getPosition()));
    // init list of square positions
    List<SquarePosition> positions = new ArrayList<SquarePosition>();

    int vertical = direction.getVertical();
    int horizontal = direction.getHorizontal();
    SquarePosition currentPosition = 
        new SquarePosition(fromPosition.getRow() + vertical, fromPosition.getCol() + horizontal);
    while (currentPosition.inBounds()) {
      positions.add(currentPosition);
      currentPosition = 
        new SquarePosition(currentPosition.getRow() + vertical, currentPosition.getCol() + horizontal);
      //System.out.println(String.format("position: %d", currentPosition.getPosition()));
    }

    return positions;
  }

  public List<SquarePosition> getSquarePositions() {
    return squarePositions;
  }

}
