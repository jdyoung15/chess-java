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

  public String getDisplayName() {
    // todo
    return "P";
  }

}
