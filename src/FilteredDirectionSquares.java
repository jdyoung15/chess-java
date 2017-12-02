import java.util.List;
import java.util.ArrayList;

public class FilteredDirectionSquares {
  
  private List<Integer> positions;
  private List<Square> squares;
  private int limit;
  private Color color;
  private boolean attackOnly;
  
  public FilteredDirectionSquares(List<Integer> positions, List<Square> squares, int limit, Color color, boolean attackOnly) {
    this.positions = positions;
    this.squares = squares;
    this.limit = limit;
    this.color = color;
    this.attackOnly = attackOnly;
  }

  public List<Integer> positions() {
    List<Integer> filteredPositions = new ArrayList<Integer>();

    int i = 0;
    while (i < limit && i < squares.size()) {
      Square square = squares.get(i);
      int position = positions.get(i);
      if (square.isOccupied()) {
        if (square.getPiece().getColor() != color) {
          filteredPositions.add(position);
        }
        break;
      }
      else if (!attackOnly) {
        filteredPositions.add(position);
      }
      i++;
    }

    return filteredPositions;
  }

}
