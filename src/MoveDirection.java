public class MoveDirection {
  
  private BoardDirection vertical;
  private BoardDirection horizontal;

  public MoveDirection(BoardDirection vertical, BoardDirection horizontal) {
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
