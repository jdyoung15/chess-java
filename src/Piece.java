public class Piece {

  private Color color;
  private PieceType pieceType;

  public Piece(Color color, PieceType pieceType) {
    this.color = color;
    this.pieceType = pieceType;
  }

  public Color getColor() {
    return this.color;
  }

  public PieceType getPieceType() {
    return this.pieceType;
  }

  public String toString() {
    return String.format("%s%s", this.color.toString(), this.pieceType.toString());
  }

}
