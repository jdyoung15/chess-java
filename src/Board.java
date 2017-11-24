import java.util.List;
import java.util.ArrayList;

public class Board {

  public static final int NUM_ROWS = 8;
  public static final int NUM_COLS = 8;
  public static final int NUM_SQUARES = NUM_ROWS * NUM_COLS;

  private ArrayList<Square> squares;

  public Board() {
    initializeSquares();
  }

  private void initializeSquares() {
    this.squares = new ArrayList<Square>();
    for (int i = 0; i < NUM_SQUARES; i++) {
      Piece piece = new Piece(Color.WHITE, PieceType.PAWN);
      this.squares.add(new Square(piece));
    }
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int row  = 0; row < NUM_ROWS; row++) {
      for (int col = 0; col < NUM_COLS; col++) {
        sb.append(this.squares.get(row * NUM_COLS + col).toString());
        sb.append(" ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }

}
