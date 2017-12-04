public enum CastlingSide {

  KINGSIDE(1, 2, 2, 3),
  QUEENSIDE(-1, 2, 3, 4);

  private int directionValue;
  private int kingToPosition;
  private int numSquaresBetween;
  private int rookPosition;

  private CastlingSide(int directionValue, int kingToPosition, int numSquaresBetween, int rookPosition) {
    this.directionValue = directionValue;
    this.kingToPosition = kingToPosition;
    this.numSquaresBetween = numSquaresBetween;
    this.rookPosition = rookPosition;
  }

  public int getDirectionValue() {
    return directionValue;    
  }

  public int getKingToPosition() {
    return kingToPosition;    
  }

  public int getNumSquaresBetween() {
    return numSquaresBetween;    
  }

  public int getRookPosition() {
    return rookPosition;    
  }

}
