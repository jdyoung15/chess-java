public class SquarePosition {

  public static final int ASCII_OFFSET = 65;
  public static final int RADIX = 10;
  private int row;
  private int col;

  public SquarePosition(String coords) {
    // todo: validate
    this.col = coordToCol(coords.charAt(0));
    this.row = coordToRow(coords.charAt(1));
  }

  public SquarePosition(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public SquarePosition(int position) {
  }

  private static int coordToRow(char coord) {
    return Character.getNumericValue(coord) - 1;
  }

  private static int coordToCol(char coord) {
    return ((int) coord) - ASCII_OFFSET;
  }

  private static char rowToCoord(int row) {
    return Character.forDigit(row + 1, RADIX);
  }

  private static char colToCoord(int col) {
    return (char) (col + ASCII_OFFSET);
  }

  private static int rowColToPosition(int row, int col) {
    int actualRow = BoardPositioning.NUM_ROWS - row - 1;
    int actualCol = col;
    return actualRow * BoardPositioning.NUM_COLS + actualCol;
  }

  public String getCoords() {
    return String.format("%c%c", colToCoord(col), rowToCoord(row));
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public int getPosition() {
    return rowColToPosition(row, col);
  }

  public String toString() {
    return getCoords();  
  }

  public boolean inBounds() {
    return row >= 0 && row < BoardPositioning.NUM_ROWS && col >= 0 && col < BoardPositioning.NUM_COLS;
  }

  // Code inspired by: https://www.sitepoint.com/implement-javas-equals-method-correctly/
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SquarePosition other = (SquarePosition) o;
    return row == other.getRow() && col == other.getCol();
  }

}
