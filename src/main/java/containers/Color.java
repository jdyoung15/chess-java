package main.java.containers;

/**
 * Represents a player and is used to denote the player's pieces.
 */
public enum Color {

  WHITE("W"), 
  BLACK("B");

  private String displaySymbol;

  Color(String displaySymbol) {
    this.displaySymbol = displaySymbol;
  }

  public String getDisplaySymbol() {
    return displaySymbol;
  }

  public static Color complementOf(Color color) {
    return color == WHITE ? BLACK : WHITE;
  }

}
