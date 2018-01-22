public class CheckSquareIsAttackable implements CheckSquare {

  public boolean test(Square square, Color currentPlayer) {
    return square.containsOpponent(currentPlayer);
  }

}
