public class Square {
  
  private Piece piece;

  public Square() {
    this.piece = null;
  }

  public Square(Piece piece) {
    this.piece = piece;
  }

  public boolean isOccupied() {
    return piece != null;
  }

  public Piece getPiece() {
    return piece;
  }

  public String toString() {
    return piece != null ? piece.toString(): "__";
  }

}
