import java.util.List;
import java.util.ArrayList;

public class Board {

  private ArrayList<Square> squares;

  public Board() {
    initializeSquares();
  }

  private void initializeSquares() {
    this.squares = new ArrayList<Square>();
    for (int i = 0; i < 64; i++) {
      this.squares.add(new Square());
    }
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int row  = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        sb.append(this.squares.get(row * 8 + col).toString());
      }
      sb.append("\n");
    }
    return sb.toString();
  }

}
