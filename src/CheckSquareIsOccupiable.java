public class CheckSquareIsOccupiable implements CheckSquare {

  public boolean test(Square square, Color currentPlayer) {
    return !square.isOccupied();
  }

}
