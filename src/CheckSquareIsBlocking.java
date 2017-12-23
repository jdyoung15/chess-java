public class CheckSquareIsBlocking implements CheckSquare {

  public boolean test(Square square, Color currentPlayer) {
    return square.isOccupied();
  }

}
