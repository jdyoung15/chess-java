public class CheckSquareIsAttackable implements CheckSquare {

  public boolean test(Square square, Color currentPlayer) {
    return square.isOccupied() && square.getPiece().getColor() != currentPlayer;
  }

}
