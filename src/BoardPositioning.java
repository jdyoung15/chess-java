import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class BoardPositioning {

  public static final int NUM_ROWS = 8;
  public static final int NUM_COLS = 8;
  public static final int NUM_SQUARES = NUM_ROWS * NUM_COLS;
  public static final int ASCII_OFFSET = 65;
  public static final int RADIX = 10;
  public static final int INVALID_POSITION = -1;

  private static final int NON_PAWN_BLACK_START_ROW = 0;
  public static final int PAWN_BLACK_START_ROW = 1;
  private static final int NON_PAWN_WHITE_START_ROW = 7;
  public static final int PAWN_WHITE_START_ROW = 6;
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

  private static final int KING_START_COL = 4;

  public static void populateSquares(List<Square> squares) {
    for (int col = 0; col < NUM_COLS; col++) {
      int position = findPosition(NON_PAWN_BLACK_START_ROW, col);
      Piece piece = new Piece(Color.BLACK, NON_PAWN_PIECE_ORDER[col]);
      squares.get(position).setPiece(piece);

      position = findPosition(PAWN_BLACK_START_ROW, col);
      piece = new Piece(Color.BLACK, PieceType.PAWN);
      squares.get(position).setPiece(piece);

      position = findPosition(NON_PAWN_WHITE_START_ROW, col);
      piece = new Piece(Color.WHITE, NON_PAWN_PIECE_ORDER[col]);
      squares.get(position).setPiece(piece);

      position = findPosition(PAWN_WHITE_START_ROW, col);
      piece = new Piece(Color.WHITE, PieceType.PAWN);
      squares.get(position).setPiece(piece);
    }
  }

  public static int findKingStartPosition(Color currentPlayer) {
    int row = currentPlayer == Color.WHITE ? NON_PAWN_WHITE_START_ROW : NON_PAWN_BLACK_START_ROW;
    return findPosition(row, KING_START_COL);
  }


  public static Iterator<Integer> positionsIterator() {
    List<Integer> positions = new ArrayList();
    for (int row = 0; row < NUM_ROWS; row++) {
      for (int col = 0; col < NUM_COLS; col++) {
        int position = findPosition(row, col);
        positions.add(position);
      }
    }
    return positions.iterator();
  }

  public static int findPosition(
    int fromPosition, 
    BoardDirection horizontal, 
    int horizontalAmount,
    BoardDirection vertical, 
    int verticalAmount)
  {
    int fromRow = findRow(fromPosition);
    int fromCol = findCol(fromPosition);

    int toRow = fromRow + vertical.findOffset(verticalAmount);
    int toCol = fromCol + horizontal.findOffset(horizontalAmount);

    return findPosition(toRow, toCol);
  }

  public static int findPosition(String coords) {
    int row = findRow(findRowCoord(coords));
    int col = findCol(findColCoord(coords));
    return findPosition(row, col);
  }

  public static int findPosition(int row, int col) {
    if (row < 0 || row >= NUM_ROWS || col < 0 || col >= NUM_COLS) {
      return INVALID_POSITION;
    }
    return row * NUM_COLS + col;
  }

  public static String findCoords(int position) {
    char rowCoord = findRowCoord(findRow(position));
    char colCoord = findColCoord(findCol(position));
    return String.format("%c%c", colCoord, rowCoord);
  }

  public static boolean isAdjacent(int position, int otherPosition) {
    return findRow(position) == findRow(otherPosition)
      && Math.abs(findCol(position) - findCol(otherPosition)) == 1;
  }

  public static BoardDirection findDirection(Color color) {
    return color == Color.WHITE ? BoardDirection.UP : BoardDirection.DOWN;
  }

  public static int findRow(int position) {
    return position / NUM_COLS;  
  }

  public static int findCol(int position) {
    return position % NUM_COLS;  
  }

  private static int findRow(char rowCoord) {
    return NUM_ROWS - Character.getNumericValue(rowCoord);
  }

  private static int findCol(char colCoord) {
    return (int) colCoord - ASCII_OFFSET;
  }

  private static char findRowCoord(String coords) {
    return coords.charAt(1);
  }

  private static char findColCoord(String coords) {
    return coords.charAt(0);
  }

  private static char findRowCoord(int row) {
    return Character.forDigit(NUM_ROWS - row, RADIX);
  }

  private static char findColCoord(int col) {
    return (char) (col + ASCII_OFFSET);
  }

}
