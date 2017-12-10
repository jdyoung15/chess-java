public enum MoveDirection {
  
  UP(BoardDirection.UP, BoardDirection.NONE),
  UP_RIGHT(BoardDirection.UP, BoardDirection.RIGHT),
  RIGHT(BoardDirection.NONE, BoardDirection.RIGHT),
  DOWN_RIGHT(BoardDirection.DOWN, BoardDirection.RIGHT),
  DOWN(BoardDirection.DOWN, BoardDirection.NONE),
  DOWN_LEFT(BoardDirection.DOWN, BoardDirection.LEFT),
  LEFT(BoardDirection.NONE, BoardDirection.LEFT),
  UP_LEFT(BoardDirection.UP, BoardDirection.LEFT);

  private BoardDirection vertical;
  private BoardDirection horizontal;

  private MoveDirection(BoardDirection vertical, BoardDirection horizontal) {
    this.vertical = vertical;
    this.horizontal = horizontal;
  }

  public BoardDirection getVertical() {
    return vertical;
  }

  public BoardDirection getHorizontal() {
    return horizontal;
  }

}
