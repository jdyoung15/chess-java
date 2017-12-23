public class CheckSquareIsOccupiableOrAttackable implements CheckSquare {

  public boolean test(Square square, Color currentPlayer) {
    return new CheckSquareIsOccupiable().test(square, currentPlayer) 
      || new CheckSquareIsAttackable().test(square, currentPlayer);
  }

}
