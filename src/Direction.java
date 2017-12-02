public enum Direction {

  UP(1, 0),
  UP_TWO_RIGHT_ONE(2, 1),
  UP_RIGHT(1, 1),
  UP_ONE_RIGHT_TWO(1, 2),
  RIGHT(0, 1),
  DOWN_ONE_RIGHT_TWO(-1, 2),
  DOWN_RIGHT(-1, 1),
  DOWN_TWO_RIGHT_ONE(-2, 1),
  DOWN(-1, 0),
  DOWN_TWO_LEFT_ONE(-2, -1),
  DOWN_LEFT(-1, -1),
  DOWN_ONE_LEFT_TWO(-1, -2),
  LEFT(0, -1),
  UP_ONE_LEFT_TWO(1, -2),
  UP_LEFT(1, -1),
  UP_TWO_LEFT_ONE(2, -1);

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
