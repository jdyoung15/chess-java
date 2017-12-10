public class MoveCoordinates {
  
  private BoardDirection horizontal;
  private int horizontalAmount;
  private BoardDirection vertical;
  private int verticalAmount;

  public MoveCoordinates(BoardDirection horizontal, int horizontalAmount, BoardDirection vertical, int verticalAmount) {
    this.horizontal = horizontal;
    this.horizontalAmount = horizontalAmount;
    this.vertical = vertical;
    this.verticalAmount = verticalAmount;
  }

  public BoardDirection getVertical() {
    return vertical;
  }

  public int getVerticalAmount() {
    return verticalAmount;
  }

  public BoardDirection getHorizontal() {
    return horizontal;
  }

  public int getHorizontalAmount() {
    return horizontalAmount;
  }

}
