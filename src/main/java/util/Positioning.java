package main.java.util;

import main.java.containers.Color;
import main.java.containers.Coordinate;
import main.java.containers.OrientedCoordinate;
import main.java.containers.Piece;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Utility class responsible for all logic concerning the board's positions
 * and how they are structured/ordered.
 */
public final class Positioning {

  private static final Pattern USER_COORDS_PATTERN = Pattern.compile("[A-H][1-8]");

  private static final int NUM_ROWS = 8;
  private static final int NUM_COLS = 8;
  private static final int OUT_OF_BOUNDS = -1;
  private static final int NUM_SQUARES = NUM_ROWS * NUM_COLS;
  private static final int ASCII_OFFSET_FOR_COL_SYMBOL = 65;
  private static final int RADIX_FOR_ROW_SYMBOL = 10;
  private static final int BLACK_HOME_ROW = 0;
  private static final int BLACK_PAWN_HOME_ROW =
    BLACK_HOME_ROW + convertOrientedOffset(Color.BLACK, 1);
  private static final int WHITE_HOME_ROW = NUM_ROWS - 1;
  private static final int WHITE_PAWN_HOME_ROW =
    WHITE_HOME_ROW + convertOrientedOffset(Color.WHITE, 1);
  private static final Piece.Type[] HOME_ROW_ORDER = {
    Piece.Type.ROOK,
    Piece.Type.KNIGHT,
    Piece.Type.BISHOP,
    Piece.Type.QUEEN,
    Piece.Type.KING,
    Piece.Type.BISHOP,
    Piece.Type.KNIGHT,
    Piece.Type.ROOK
  };
  private static final int KING_START_COL = Arrays.asList(HOME_ROW_ORDER).indexOf(Piece.Type.KING);

  private Positioning() {}

  /**
   * Returns all positions on the board.
   */
  public static List<Integer> getAllPositions() {
    return IntStream.range(0, NUM_SQUARES).boxed().collect(Collectors.toList());
  }

  /**
   * Returns a map of position to the piece that should occupy that position
   * at the start of the game.
   */
  public static Map<Integer, Piece> getPieceStartPositions() {
    Map<Integer, Piece> startPositions = new HashMap<>();

    Piece.Type[] pawns = new Piece.Type[NUM_COLS];
    Arrays.fill(pawns, Piece.Type.PAWN);

    populateStartPositionsForRow(BLACK_HOME_ROW, HOME_ROW_ORDER, Color.BLACK, startPositions);
    populateStartPositionsForRow(BLACK_PAWN_HOME_ROW, pawns, Color.BLACK, startPositions);
    populateStartPositionsForRow(WHITE_HOME_ROW, HOME_ROW_ORDER, Color.WHITE, startPositions);
    populateStartPositionsForRow(WHITE_PAWN_HOME_ROW, pawns, Color.WHITE, startPositions);

    return startPositions;
  }

  /**
   * Populates the given map of Start position -> containers.Piece using the provided information.
   */
  private static void populateStartPositionsForRow(
    int row, Piece.Type[] rowPieces, Color color, Map<Integer, Piece> startPositions)
  {
    for (int col = 0; col < NUM_COLS; col++) {
      int position = toPosition(row, col);
      Piece piece = new Piece(color, rowPieces[col]);
      startPositions.put(position, piece);
    }
  }

  /**
   * Returns whether the given position is valid.
   */
  public static boolean isValidPosition(int position) {
    return getAllPositions().contains(position);
  }

  /**
   * Returns a position calculated by applying the given {@link Coordinate} to the
   * given position.
   */
  public static int calculateNewPosition(int fromPosition, Coordinate coordinate) {
    return calculateNewPosition(fromPosition, coordinate.getY(), coordinate.getX());
  }

  /**
   * Returns a position calculated by applying the given {@link OrientedCoordinate}
   * to the given position.
   */
  public static int calculateNewPosition(
    int fromPosition, OrientedCoordinate coordinate, Color color)
  {
    int x = convertOrientedOffset(color, coordinate.getX());
    int y = convertOrientedOffset(color, coordinate.getY());
    return calculateNewPosition(fromPosition, y, x);
  }

  /**
   * Converts the given oriented row or col offset to a standard, "bird's eye" offset.
   */
  private static int convertOrientedOffset(Color color, int orientedOffset) {
    return color == Color.WHITE ? -orientedOffset : orientedOffset;
  }

  /**
   * Returns a position calculated by applying the given row and column offsets to the
   * given position.
   */
  private static int calculateNewPosition(int fromPosition, int rowOffset, int colOffset) {
    int row = rowOf(fromPosition) + rowOffset;
    int col = colOf(fromPosition) + colOffset;

    if ((row < 0 || NUM_ROWS <= row) || (col < 0 || NUM_COLS <= col)) {
      return OUT_OF_BOUNDS;

    }
    return toPosition(row, col);
  }

  private static int rowOf(int position) {
    return position / NUM_COLS;
  }

  private static int colOf(int position) {
    return position % NUM_COLS;
  }

  /**
   * Returns the position corresponding to the provided user input.
   */
  public static int toPosition(String input) {
    int row = toRow(getRowSymbol(input));
    int col = toCol(getColSymbol(input));
    return toPosition(row, col);
  }

  /**
   * Returns a char representing the row specified in the given user-provided string.
   */
  private static char getRowSymbol(String input) {
    return input.charAt(1);
  }

  /**
   * Returns a char representing the column specified in the given user-provided string.
   */
  private static char getColSymbol(String input) {
    return input.charAt(0);
  }

  private static int toRow(char rowSymbol) {
    return NUM_ROWS - Character.getNumericValue(rowSymbol);
  }

  private static int toCol(char colSymbol) {
    return (int) colSymbol - ASCII_OFFSET_FOR_COL_SYMBOL;
  }

  /**
   * Converts the given row and column values to a position.
   */
  private static int toPosition(int row, int col) {
    return row * NUM_COLS + col;
  }

  /**
   * Converts the given position to a user-friendly string.
   */
  public static String toDisplayString(int position) {
    char rowSymbol = toRowSymbol(rowOf(position));
    char colSymbol = toColSymbol(colOf(position));
    return String.format("%c%c", colSymbol, rowSymbol);
  }

  private static char toRowSymbol(int row) {
    return Character.forDigit(NUM_ROWS - row, RADIX_FOR_ROW_SYMBOL);
  }

  private static char toColSymbol(int col) {
    return (char) (col + ASCII_OFFSET_FOR_COL_SYMBOL);
  }

  /**
   * Returns whether the given string representing the user-provided position
   * is valid.
   */
  public static boolean isValidPositionInput(String coords) {
    Matcher m = USER_COORDS_PATTERN.matcher(coords);
    return m.matches();
  }

  /**
   * Returns whether the given piece in the given position
   * is currently in the start row for a piece of that color.
   */
  public static boolean inStartRow(int position, Piece piece) {
    int row = rowOf(position);
    Piece.Type type = piece.getPieceType();
    if (piece.getColor() == Color.WHITE) {
      return type == Piece.Type.PAWN ? row == WHITE_PAWN_HOME_ROW : row == WHITE_HOME_ROW;
    }
    else {
      return type == Piece.Type.PAWN ? row == BLACK_PAWN_HOME_ROW : row == BLACK_HOME_ROW;
    }
  }

  /**
   * Returns whether the given position is located in the non-pawn start row for
   * the given color.
   */
  public static boolean inHomeRow(int position, Color color) {
    int row = rowOf(position);
    return color == Color.WHITE ? row == BLACK_HOME_ROW : row == WHITE_HOME_ROW;
  }

  /**
   * Returns whether the two positions are in the same row and no positions exist between them.
   */
  public static boolean isAdjacent(int positionA, int positionB) {
    return rowOf(positionA) == rowOf(positionB)
      && Math.abs(colOf(positionA) - colOf(positionB)) == 1;
  }

  /**
   * Returns all positions between the two given positions, exclusive. Assumes
   * the two positions are not identical and that they are in the same row.
   */
  public static List<Integer> getRowPositionsBetween(int positionA, int positionB) {
    if (rowOf(positionA) != rowOf(positionB) || positionA == positionB) {
      throw new IllegalArgumentException(
        "Unequal rows for positions: " + positionA + " " + positionB);
    }

    List<Integer> positions = List.of(positionA, positionB);
    int lower = Collections.min(positions, Comparator.comparing(Positioning::colOf));
    int upper = Collections.max(positions, Comparator.comparing(Positioning::colOf));
    return getPositionsBetween(lower, upper, new Coordinate(1, 0));
  }

  /**
   * Returns all positions between the two given positions. Assumes the two positions
   * are not identical and that they are (in the same row) XOR (in the same column).
   */
  private static List<Integer> getPositionsBetween(int lower, int upper, Coordinate coordinate) {
    List<Integer> between = new ArrayList<>();
    int current = calculateNewPosition(lower, coordinate);
    while (current != upper) {
      between.add(current);
      current = calculateNewPosition(current, coordinate);
    }
    return between;
  }

  /**
   * Returns all positions between the two given positions, exclusive. Assumes
   * the two positions are not identical and that they are in the same column.
   */
  public static List<Integer> getColPositionsBetween(int positionA, int positionB) {
    if (colOf(positionA) != colOf(positionB) || positionA == positionB) {
      throw new IllegalArgumentException(
        "Unequal columns for positions: " + positionA + " " + positionB);
    }

    List<Integer> positions = List.of(positionA, positionB);
    int lower = Collections.min(positions, Comparator.comparing(Positioning::rowOf));
    int upper = Collections.max(positions, Comparator.comparing(Positioning::rowOf));
    return getPositionsBetween(lower, upper, new Coordinate(0, 1));
  }

  /**
   * Returns the start position of the king belonging to the given color.
   */
  public static int getKingStartPosition(Color color) {
    int row = color == Color.WHITE ? WHITE_HOME_ROW : BLACK_HOME_ROW;
    return toPosition(row, KING_START_COL);
  }

  /**
   * Returns a list of sublists, where each sublist represents a natural "grouping"
   * of positions. This can be used to structure the positions in a way that is easily
   * viewable by users.
   */
  public static List<List<Integer>> getPositionGroupings() {
    List<List<Integer>> groupings = new ArrayList<>();

    for (int row = 0; row < NUM_ROWS; row++) {
      List<Integer> grouping = new ArrayList<>();
      for (int col = 0; col < NUM_COLS; col++) {
        grouping.add(toPosition(row, col));
      }

      groupings.add(grouping);
    }

    return groupings;
  }

  /**
   * Returns a char representing a label for the grouping whose position in sequence is
   * denoted by the given number.
   */
  public static char getGroupingLabel(int groupingNumber) {
    return toRowSymbol(groupingNumber);
  }

  /**
   * Returns a char representing a label for the relative position, denoted by the given
   * number, of an item within a grouping.
   */
  public static char getGroupingItemLabel(int groupingItemNumber) {
    return toColSymbol(groupingItemNumber);
  }

}
