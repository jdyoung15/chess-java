import java.util.List;
import java.util.ArrayList;

public class Board {

  public static final int NUM_ROWS = 8;
  public static final int NUM_COLS = 8;
  public static final int NUM_SQUARES = NUM_ROWS * NUM_COLS;
  public static final int ASCII_OFFSET = 65;
  public static final int RADIX = 10;

  private static final PieceType[] NON_PAWN_PIECE_ORDER = {
    PieceType.ROOK,
    PieceType.KNIGHT,
    PieceType.BISHOP,
    PieceType.QUEEN,
    PieceType.KING,
    PieceType.BISHOP,
    PieceType.KNIGHT,
    PieceType.ROOK
  };

  private List<Square> squares;

  public Board() {
    initializeSquares();
  }

  public Board(List<Square> squares) {
    this.squares = squares;
  }

  private void initializeSquares() {
    squares = new ArrayList<Square>();

    int row = 0;
    for (int col = 0; col < NUM_COLS; col++) {
      squares.add(findPosition(row, col), new Square(new Piece(Color.BLACK, NON_PAWN_PIECE_ORDER[col])));
    }

    row = 1;
    for (int col = 0; col < NUM_COLS; col++) {
      squares.add(findPosition(row, col), new Square(new Piece(Color.BLACK, PieceType.PAWN)));
    }

    for (row = 2; row < 6; row++) {
      for (int col = 0; col < NUM_COLS; col++) {
        squares.add(findPosition(row, col), new Square());
      }
    }

    row = 6;
    for (int col = 0; col < NUM_COLS; col++) {
      squares.add(findPosition(row, col), new Square(new Piece(Color.WHITE, PieceType.PAWN)));
    }

    row = 7;
    for (int col = 0; col < NUM_COLS; col++) {
      squares.add(findPosition(row, col), new Square(new Piece(Color.WHITE, NON_PAWN_PIECE_ORDER[col])));
    }

    //Piece kingWhite = new Piece(Color.WHITE, PieceType.KING);
    //Piece rookWhite = new Piece(Color.WHITE, PieceType.ROOK);
    //Piece bishopWhite = new Piece(Color.WHITE, PieceType.BISHOP);
    //Piece kingBlack = new Piece(Color.BLACK, PieceType.KING);
    //Piece rookBlack = new Piece(Color.BLACK, PieceType.ROOK);
    //squares.set(60, new Square(kingWhite));
    //squares.set(56, new Square(rookWhite));
    //squares.set(63, new Square(rookWhite));
    //squares.set(48, new Square(bishopWhite));
    //squares.set(4, new Square(kingBlack));
    //squares.set(0, new Square(rookBlack));
  }

  public List<Integer> findOpponentPositions(Color currentPlayer) {
    List<Integer> positions = new ArrayList<Integer>();
    Color opponent = Color.findOpponent(currentPlayer);
    int position = 0;
    for (Square square : squares) {
      if (square.isOccupied() && square.getPiece().getColor() == opponent) {
        positions.add(position); 
      }
      position++;
    }
    return positions;
  }

  public int findKingPosition(Color currentPlayer) {
    int position = 0;
    for (Square square : squares) {
      if (square.isOccupied()) {
        Piece piece = square.getPiece();
        if (piece.getPieceType() == PieceType.KING && piece.getColor() == currentPlayer) {
          return position;
        }
      }
      position++;
    }
    return -1;
  }

  public void move(int fromPosition, int toPosition) {
    Square from = findSquare(fromPosition);
    Square to = findSquare(toPosition);

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
    int row = findRow(position);
    int col = findCol(position);

    int newRow = row + -1 * vertical;
    int newCol = col + horizontal;

    return newRow >= 0 && newRow < NUM_ROWS && newCol >= 0 && newCol < NUM_COLS;
  }

  public static int findPosition(int row, int col) {
    return row * NUM_COLS + col;
  }

  public static int calculateNewPosition(int position, int horizontal, int vertical) {
    int row = findRow(position);
    int col = findCol(position);

    int newRow = row + -1 * vertical;
    int newCol = col + horizontal;

    return newRow * NUM_COLS + newCol;
  }

  public static int findPosition(String coords) {
    int col = (int) coords.charAt(0) - ASCII_OFFSET;
    int row = NUM_ROWS - Character.getNumericValue(coords.charAt(1));

    return row * NUM_COLS + col;
  }

  public static int findRow(int position) {
    return position / 8;  
  }

  public static int findCol(int position) {
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

  public static boolean isAdjacent(int position, int otherPosition) {
    return findRow(position) == findRow(otherPosition)
      && Math.abs(findCol(position) - findCol(otherPosition)) == 1;
  }

  public static Direction findDirection(Color color) {
    return color == Color.WHITE ? Direction.UP : Direction.DOWN;
  }

  public Board copy() {
    List<Square> squaresCopy = new ArrayList<Square>();
    for (Square square : squares) {
      squaresCopy.add(square.copy());
    }
    Board boardCopy = new Board(squaresCopy);
    return boardCopy;
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
