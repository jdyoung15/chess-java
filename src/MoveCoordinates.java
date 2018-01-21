public class MoveCoordinates {
  
  private BoardDirection horizontal;
  private int horizontalAmount;
  private BoardDirection vertical;
  private int verticalAmount;

  private static final BoardDirection[] HORIZONTAL_DIRECTIONS = {BoardDirection.LEFT, BoardDirection.RIGHT};
  private static final BoardDirection[] VERTICAL_DIRECTIONS = {BoardDirection.UP, BoardDirection.DOWN};

  public MoveCoordinates(BoardDirection horizontal, int horizontalAmount, BoardDirection vertical, int verticalAmount) {
    this.horizontal = horizontal;
    this.horizontalAmount = horizontalAmount;
    this.vertical = vertical;
    this.verticalAmount = verticalAmount;
  }

  public MoveCoordinates(BoardDirection direction, int amount) {
    switch (direction) {
      case LEFT:
      case RIGHT:
        this.horizontal = direction;
        this.horizontalAmount = amount;
        this.vertical = BoardDirection.NONE;
        this.verticalAmount = 0;
        break;
      case UP:
      case DOWN:
        this.horizontal = BoardDirection.NONE;
        this.horizontalAmount = 0;
        this.vertical = direction;
        this.verticalAmount = amount;
        break;
    }
  }

  public BoardDirection getHorizontal() {
    return horizontal;
  }

  public int getHorizontalAmount() {
    return horizontalAmount;
  }

  public BoardDirection getVertical() {
    return vertical;
  }

  public int getVerticalAmount() {
    return verticalAmount;
  }

  public int findPosition(int fromPosition) {
    return BoardPositioning.findPosition(fromPosition, horizontal, horizontalAmount, vertical, verticalAmount);
  }

}
