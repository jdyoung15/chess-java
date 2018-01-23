public enum PieceType {

  PAWN("P"), 
  ROOK("R"), 
  KNIGHT("N"), 
  BISHOP("B"), 
  QUEEN("Q"), 
  KING("K");

  private String displayName;

  private PieceType(String displayName) {
    this.displayName = displayName;
  }

  public String toString() {
    return displayName;
  }

  public static PieceType findPieceType(String displayName) {
    for (PieceType pieceType : PieceType.values()) {
      if (pieceType.toString().equals(displayName)) {
        return pieceType;
      }
    }
    return PieceType.QUEEN;
  }

}
