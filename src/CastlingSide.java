public enum CastlingSide {

  KINGSIDE(new MoveDirection(BoardDirection.NONE, BoardDirection.RIGHT), 2, 2, 3, 1),
  QUEENSIDE(new MoveDirection(BoardDirection.NONE, BoardDirection.LEFT), 2, 3, 4, 1);

  private MoveDirection moveDirection;
  private int kingToPosition;
  private int numSquaresBetween;
  private int rookFromPosition;
  private int rookToPosition;

  private CastlingSide(MoveDirection moveDirection, int kingToPosition, int numSquaresBetween, int rookFromPosition, int rookToPosition) 
  {
    this.moveDirection = moveDirection;
    this.kingToPosition = kingToPosition;
    this.numSquaresBetween = numSquaresBetween;
    this.rookFromPosition = rookFromPosition;
    this.rookToPosition = rookToPosition;
  }

  public MoveDirection getMoveDirection() {
    return moveDirection;    
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
        BoardPositioning.findPosition(fromPosition, c.getMoveDirection(), c.getKingToPosition());
      if (toPosition == expectedPosition) {
        return c;
      }
    }
    return null;
  }

}
