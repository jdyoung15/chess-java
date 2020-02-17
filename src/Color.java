/**
 * Represents a player and is used to denote the player's pieces.
 */
public enum Color {

  WHITE("W"), 
  BLACK("B");

  private String displayName;

  Color(String displayName) {
    this.displayName = displayName;
  }

  public String toString() {
    return displayName;
  }

  public static Color complementOf(Color color) {
    return color == WHITE ? BLACK : WHITE;
  }

}
