import java.util.List;
import java.util.ArrayList;

public class Board {

  public static final int NUM_ROWS = 8;
  public static final int NUM_COLS = 8;
  public static final int NUM_SQUARES = NUM_ROWS * NUM_COLS;
  public static final int ASCII_OFFSET = 65;

  private List<Square> squares;

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
    // fromCoords
    //   in bounds
    //   piece at those coords
    //   piece belongs to player
    //   not in check
    //
    return true;
  }

  // assumes this is a valid move
  public void move(String fromCoords, String toCoords) {
    Square fromSquare = getSquareByCoords(fromCoords);
    Square toSquare = getSquareByCoords(toCoords);
    toSquare.setPiece(fromSquare.getPiece());
    fromSquare.clear();
  }

  // assumes coords are valid
  public Square getSquareByCoords(String coords) {
    // convert coords to position
    return squares.get(convertCoordsToPosition(coords));
  }

  private int convertCoordsToPosition(String coords) {
    int col = (int) coords.charAt(0) - ASCII_OFFSET;
    int row = NUM_ROWS - Character.getNumericValue(coords.charAt(1));

    return row * NUM_COLS + col;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int row = 0; row < NUM_ROWS; row++) {
      // add row number
      sb.append(String.format("%d  ", NUM_ROWS - row));
      // add string representations of squares
      for (int col = 0; col < NUM_COLS; col++) {
        sb.append(squares.get(row * NUM_COLS + col).toString());
        sb.append(" ");
      }
      sb.append("\n");
    }

    // add column letters
    sb.append("\n   ");
    for (int col = 0; col < NUM_COLS; col++) {
      sb.append(String.format("%c  ", (char) col + ASCII_OFFSET));
    }

    sb.append("\n\n");
    return sb.toString();
  }

}
