public enum Color {

  WHITE("W"), 
  BLACK("B");

  private String displayName;

  private Color(String displayName) {
    this.displayName = displayName;
  }

  public String toString() {
    return displayName;
  }

  public static Color findOpponent(Color color) {
    return color == WHITE ? BLACK : WHITE;
  }

}
