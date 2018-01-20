public class Move {

  private int fromPosition;
  private int toPosition;

  public Move(int fromPosition, int toPosition) {
    this.fromPosition = fromPosition;
    this.toPosition = toPosition;
  }

  public int getFromPosition() {
    return fromPosition;
  }

  public int getToPosition() {
    return toPosition;
  }

  public boolean isLegal(Board board, Color currentPlayer) {
    Board copy = board.copy();  
    copy.move(fromPosition, toPosition);
    return !copy.isChecked(currentPlayer);
  }

}
