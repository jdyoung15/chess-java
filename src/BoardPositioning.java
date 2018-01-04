import java.util.List;
import java.util.ArrayList;

public class BoardPositioning {

  public static final int NUM_ROWS = 8;
  public static final int NUM_COLS = 8;
  public static final int NUM_SQUARES = NUM_ROWS * NUM_COLS;
  public static final int ASCII_OFFSET = 65;
  public static final int RADIX = 10;
  public static final int INVALID_POSITION = -1;

  public static int findPosition(int fromPosition, MoveDirection moveDirection, int amount) {
    if (fromPosition == 47 && moveDirection.getVertical() == BoardDirection.UP && moveDirection.getHorizontal() == BoardDirection.NONE) {
      System.out.println(String.format("vertical offset %d horizontal offset %d amount %d", 
        findPositionalOffset(moveDirection.getVertical(), amount),
        findPositionalOffset(moveDirection.getHorizontal(), amount),
        amount));
    }
    return findPosition(
      fromPosition, 
      findPositionalOffset(moveDirection.getVertical(), amount),
      findPositionalOffset(moveDirection.getHorizontal(), amount));
  }

  public static int findPosition(int fromPosition, MoveCoordinates moveCoordinates) {
    return findPosition(
      fromPosition, 
      findPositionalOffset(moveCoordinates.getVertical(), moveCoordinates.getVerticalAmount()),
      findPositionalOffset(moveCoordinates.getHorizontal(), moveCoordinates.getHorizontalAmount()));
  }

  public static int findPosition(int fromPosition, int up, int right, int down, int left) {
    return findPosition(
      fromPosition,
      findPositionalOffset(BoardDirection.UP, up) + findPositionalOffset(BoardDirection.DOWN, down),
      findPositionalOffset(BoardDirection.LEFT, left) + findPositionalOffset(BoardDirection.RIGHT, right));
  }

  public static int findPosition(int fromPosition, int verticalOffset, int horizontalOffset) {
    int fromRow = findRow(fromPosition);
    int fromCol = findCol(fromPosition);

    int toRow = fromRow + verticalOffset;
    int toCol = fromCol + horizontalOffset;

    return findPosition(toRow, toCol);
  }
  
  public static int findPosition(String coords) {
    int col = findCol(findColCoord(coords));
    int row = findRow(findRowCoord(coords));
    return findPosition(row, col);
  }

  public static int findPosition(int row, int col) {
    if (row < 0 || row >= NUM_ROWS || col < 0 || col >= NUM_COLS) {
      return INVALID_POSITION;
    }
    return row * NUM_COLS + col;
  }

  public static String findCoords(int position) {
    char colCoord = findColCoord(findCol(position));
    char rowCoord = findRowCoord(findRow(position));
    return String.format("%c%c", colCoord, rowCoord);
  }

  public static List<String> findCoords(List<Integer> positions) {
    List<String> coords = new ArrayList<String>();
    for (int position : positions) {
      coords.add(findCoords(position));
    }
    return coords;
  }

  public static boolean isAdjacent(int position, int otherPosition) {
    return findRow(position) == findRow(otherPosition)
      && Math.abs(findCol(position) - findCol(otherPosition)) == 1;
  }

  public static BoardDirection findDirection(Color color) {
    return color == Color.WHITE ? BoardDirection.UP : BoardDirection.DOWN;
  }

  private static int findPositionalOffset(BoardDirection boardDirection, int amount) {
    return boardDirection.getValue() * amount; 
  }

  public static int findCol(int position) {
    return position % 8;  
  }

  public static int findRow(int position) {
    return position / 8;  
  }

  private static int findCol(char colCoord) {
    return (int) colCoord - ASCII_OFFSET;
  }

  private static int findRow(char rowCoord) {
    return NUM_ROWS - Character.getNumericValue(rowCoord);
  }

  private static char findColCoord(String coords) {
    return coords.charAt(0);
  }

  private static char findRowCoord(String coords) {
    return coords.charAt(1);
  }

  private static char findColCoord(int col) {
    return (char) (col + ASCII_OFFSET);
  }

  private static char findRowCoord(int row) {
    return Character.forDigit(NUM_ROWS - row, RADIX);
  }


}
