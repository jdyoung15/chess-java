public enum CastlingSide {

  KINGSIDE(1, 2, 2, 3, 1),
  QUEENSIDE(-1, 2, 3, 4, 1);

  private int directionValue;
  private int kingToPosition;
  private int numSquaresBetween;
  private int rookFromPosition;
  private int rookToPosition;

  private CastlingSide(int directionValue, int kingToPosition, int numSquaresBetween, int rookFromPosition, int rookToPosition) 
  {
    this.directionValue = directionValue;
    this.kingToPosition = kingToPosition;
    this.numSquaresBetween = numSquaresBetween;
    this.rookFromPosition = rookFromPosition;
    this.rookToPosition = rookToPosition;
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

  public int getRookFromPosition() {
    return rookFromPosition;    
  }

  public int getRookToPosition() {
    return rookToPosition;    
  }

  public static CastlingSide fromKingMove(int fromPosition, int toPosition) {
    for (CastlingSide c : CastlingSide.values()) {
      int expectedPosition = 
        Board.calculateNewPosition(fromPosition, c.getKingToPosition() * c.getDirectionValue(), 0);
      if (toPosition == expectedPosition) {
        return c;
      }
    }
    return null;
  }

}
