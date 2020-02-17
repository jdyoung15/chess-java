public class Square {

  private Piece piece;

  public Square() {
    new Square(null);
  }

  public Square(Piece piece) {
    this.piece = piece;
  }

  public boolean isSquareOccupied() {
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

  public Square copy() {
    return new Square(piece);
  }

  public static Square empty() {
    return new Square();
  }

  @Override
  public String toString() {
    return piece != null ? piece.toString(): "__";
  }

}
