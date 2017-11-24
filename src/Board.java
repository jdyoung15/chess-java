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
    squares = new ArrayList<Square>();
    for (int i = 0; i < NUM_SQUARES; i++) {
      squares.add(new Square());
    }
    Piece piece = new Piece(Color.WHITE, PieceType.PAWN);
    squares.set(0, new Square(piece));
  }

  public boolean isValidMove(String fromCoords, String toCoords) {
    // TODO
    return true;
  }

  // assumes this is a valid move
  public void move(String fromCoords, String toCoords) {
    Square fromSquare = getSquare(fromCoords);
    Square toSquare = getSquare(toCoords);
    toSquare.setPiece(fromSquare.getPiece());
    fromSquare.clear();
  }

  public Square getSquare(String coords) {
    return squares.get(Integer.parseInt(coords));
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int row  = 0; row < NUM_ROWS; row++) {
      for (int col = 0; col < NUM_COLS; col++) {
        sb.append(squares.get(row * NUM_COLS + col).toString());
        sb.append(" ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }

}
