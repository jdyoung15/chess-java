public enum Direction {

  UP(1, 0),
  UP_RIGHT(1, 1),
  RIGHT(0, 1),
  DOWN_RIGHT(-1, 1),
  DOWN(-1, 0),
  DOWN_LEFT(-1, -1),
  LEFT(0, -1),
  UP_LEFT(1, -1);

  private int vertical;
  private int horizontal;

  private Direction(int vertical, int horizontal) {
    this.vertical = vertical;
    this.horizontal = horizontal;
  }

  public int getVertical() {
    return vertical;
  }

  public int getHorizontal() {
    return horizontal;
  }

}
