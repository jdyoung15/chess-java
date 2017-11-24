public class Square {
  
  private Piece piece;

  public Square() {
    this.piece = null;
  }

  public boolean isOccupied() {
    return this.piece != null;
  }

  public Piece getPiece() {
    return this.piece;
  }

  public String toString() {
    return piece != null ? piece.getDisplayName(): "_";
  }

}
