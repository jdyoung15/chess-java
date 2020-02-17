/**
 * Represents a single piece in the game.
 */
public class Piece {

  private Color color;
  private Type pieceType;

  public Piece(Color color, Type pieceType) {
    this.color = color;
    this.pieceType = pieceType;
  }

  public Color getColor() {
    return color;
  }

  public Type getPieceType() {
    return pieceType;
  }

  public String toString() {
    return String.format("%s%s", color.toString(), pieceType.toString());
  }

  public enum Type {

    PAWN("P"),
    ROOK("R"),
    KNIGHT("N"),
    BISHOP("B"),
    QUEEN("Q"),
    KING("K");

    private String displayName;

    Type(String displayName) {
      this.displayName = displayName;
    }

    public static Type findPieceType(String displayName) {
      for (Type pieceType : Type.values()) {
        if (pieceType.toString().equals(displayName)) {
          return pieceType;
        }
      }
      return Type.QUEEN;
    }

    @Override
    public String toString() {
      return displayName;
    }

    /** Returns the name formatted as a choice for the user. */
    public String toChoiceString() {
      String name = this.name();
      String first = name.substring(0, 1).replace(this.displayName, bracketedDisplayName());
      String remaining = name.substring(1).replace(this.displayName, bracketedDisplayName());
      return first.toUpperCase() + remaining.toLowerCase();
    }

    /** Returns the display name of this enum value, surrounded by brackets. */
    private String bracketedDisplayName() {
      return "[" + this.displayName + "]";
    }
  }
}
