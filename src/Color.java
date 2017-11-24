public enum Color {

  WHITE("W"), 
  BLACK("B");

  private String displayName;

  private Color(String displayName) {
    this.displayName = displayName;
  }

  public String toString() {
    return this.displayName;
  }

}
