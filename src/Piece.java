public class Piece {

  private Color color;
  private PieceType pieceType;

  public Piece(Color color, PieceType pieceType) {
    this.color = color;
    this.pieceType = pieceType;
  }

  public Color getColor() {
    return color;
  }

  public PieceType getPieceType() {
    return pieceType;
  }

  public String toString() {
    return String.format("%s%s", color.toString(), pieceType.toString());
  }

}
