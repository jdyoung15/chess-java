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

  public void setPiece(Piece piece) {
    this.piece = piece;
  }

  public void clear() {
    this.piece = null;
  }

  public String toString() {
    return piece != null ? piece.toString(): "__";
  }

  public Square copy() {
    if (isOccupied()) {
      return new Square(piece);
    }
    return new Square();
  }

}
