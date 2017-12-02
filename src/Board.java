import java.util.List;
import java.util.ArrayList;

public class Board {

  public static final int NUM_ROWS = 8;
  public static final int NUM_COLS = 8;
  public static final int NUM_SQUARES = NUM_ROWS * NUM_COLS;
  public static final int ASCII_OFFSET = 65;
  public static final int RADIX = 10;

  private List<Square> squares;

  public Board() {
    initializeSquares();
  }

  private void initializeSquares() {
    squares = new ArrayList<Square>();
    for (int i = 0; i < NUM_SQUARES; i++) {
      squares.add(new Square());
    }
    Piece piece = new Piece(Color.WHITE, PieceType.KING);
    Piece pieceWhite = new Piece(Color.WHITE, PieceType.ROOK);
    Piece pieceBlack = new Piece(Color.BLACK, PieceType.ROOK);
    squares.set(35, new Square(piece));
    squares.set(18, new Square(pieceWhite));
    squares.set(8, new Square(pieceBlack));
  }

  public void move(Square from, Square to) {
    to.setPiece(from.getPiece());
    from.clear();
  }

  public Square findSquare(int position) {
    return squares.get(position);
  }

  public List<Square> findSquares(List<Integer> positions) {
    List<Square> squares = new ArrayList<Square>();
    for (int position : positions) {
      squares.add(findSquare(position));
    }
    return squares;
  }

  public static boolean inBounds(int position, int horizontal, int vertical) {
    // find row, col
    int currentRow = findRow(position);
    int currentCol = findCol(position);
    if (currentRow + vertical < 0 || currentRow + vertical >= 8) {
      return false;
    }
    if (currentCol + horizontal < 0 || currentCol + horizontal >= 8) {
      return false;
    }

    return true;
  }

  public static int calculateNewPosition(int position, int horizontal, int vertical) {
    return position + vertical * NUM_COLS + horizontal;
  }

  public static int findPosition(String coords) {
    int col = (int) coords.charAt(0) - ASCII_OFFSET;
    int row = NUM_ROWS - Character.getNumericValue(coords.charAt(1));

    return row * NUM_COLS + col;
  }

  private static int findRow(int position) {
    return position / 8;  
  }

  private static int findCol(int position) {
    return position % 8;  
  }

  private static char findRowCoord(int row) {
    return Character.forDigit(NUM_ROWS - row, RADIX);
  }

  private static char findColCoord(int col) {
    return (char) (col + ASCII_OFFSET);
  }

  public static List<String> findCoords(List<Integer> positions) {
    List<String> coords = new ArrayList<String>();
    for (int position : positions) {
      coords.add(findCoords(position));
    }
    return coords;
  }

  public static String findCoords(int position) {
    return String.format("%c%c", findColCoord(findCol(position)), findRowCoord(findRow(position)));
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
