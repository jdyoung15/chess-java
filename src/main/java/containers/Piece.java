package main.java.containers;

import java.util.Arrays;
import java.util.Optional;

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

  public String getDisplaySymbol() {
    return String.format("%s%s", color.getDisplaySymbol(), pieceType.getDisplaySymbol());
  }

  public enum Type {

    PAWN("P"),
    ROOK("R"),
    KNIGHT("N"),
    BISHOP("B"),
    QUEEN("Q"),
    KING("K");

    private String displaySymbol;

    Type(String displaySymbol) {
      this.displaySymbol = displaySymbol;
    }

    /**
     * Returns an optional containing the {@link Piece.Type} corresponding to the given
     * string representing its display symbol. If no match is found, returns an empty Optional.
     */
    public static Optional<Type> findPieceType(String displaySymbol) {
      return Arrays.stream(Type.values())
        .filter(value -> value.toString().equals(displaySymbol))
        .findFirst();
    }

    public String getDisplaySymbol() {
      return displaySymbol;
    }

    /**
     * Returns the name of the current enum with the display symbol surrounded by brackets.
     */
    public String toBracketedName() {
      String name = this.name();
      String first = name.substring(0, 1).replace(this.displaySymbol, bracketedDisplayName());
      String remaining = name.substring(1).replace(this.displaySymbol, bracketedDisplayName());
      return first.toUpperCase() + remaining.toLowerCase();
    }

    /**
     * Returns the display name of this enum value, surrounded by brackets.
     */
    private String bracketedDisplayName() {
      return "[" + this.displaySymbol + "]";
    }
  }
}
