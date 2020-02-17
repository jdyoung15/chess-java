/**
 * Represents a way to compute a new board position relative to a source position.
 * The source position can be treated as the "origin" -- i.e. (0,0) -- of the
 * "coordinate plane" that is the board.
 */
public class Coordinate {

  // The left (-) or right (+) offset to apply to a given source position
  private int x;
  // The up (+) or down (-) offset to apply to a given source position
  private int y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

}