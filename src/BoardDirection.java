public enum BoardDirection {

  UP(-1),
  RIGHT(1),
  DOWN(1),
  LEFT(-1),
  NONE(0);

  private int value;

  private BoardDirection(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

}
