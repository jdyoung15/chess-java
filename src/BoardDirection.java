public enum BoardDirection {

  UP(-1),
  RIGHT(1),
  DOWN(1),
  LEFT(-1),
  NONE(0);

  int multiplier;

  private BoardDirection(int multiplier) {
    this.multiplier = multiplier;
  }

  /** 
   * @amount the number of squares to move in this direction
   * @returns a positive or negative offset used in calculating new positions
   */
  public int findOffset(int amount) {
    return multiplier * amount;
  }

}
