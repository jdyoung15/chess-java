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
    copy.move(this);
    return !copy.isChecked(currentPlayer);
  }

  public String toString() {
    return String.format(
      "%s -> %s", 
      BoardPositioning.findCoords(fromPosition), 
      BoardPositioning.findCoords(toPosition));
  }

  // Inspiration: https://www.geeksforgeeks.org/overriding-equals-method-in-java/
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
 
    if (!(o instanceof Move)) {
      return false;
    }
     
    Move m = (Move) o;
     
    return fromPosition == m.getFromPosition() && toPosition == m.getToPosition();
  }

}
