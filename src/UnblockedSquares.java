import java.util.List;
import java.util.ArrayList;

public class UnblockedSquares {
  
  private List<Integer> positions;
  private List<Square> squares;
  private Color color;
  
  public UnblockedSquares(List<Integer> positions, List<Square> squares, Color color) {
    this.positions = positions;
    this.squares = squares;
    this.color = color;
  }

  public List<Integer> positions() {
    List<Integer> unblockedPositions = new ArrayList<Integer>();

    for (int i = 0; i < squares.size(); i++) {
      Square square = squares.get(i);
      int position = positions.get(i);
      if (square.isOccupied()) {
        if (square.getPiece().getColor() != color) {
          unblockedPositions.add(position);
        }
        break;
      }
      else {
        unblockedPositions.add(position);
      }

    }

    return unblockedPositions;
  }

}
