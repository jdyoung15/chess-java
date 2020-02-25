package containers;

/**
 * Represents a direction in the board. This direction is not relative to any specific
 * player, but rather an absolute direction from the vantage point of one looking down
 * on the board.
 */
public enum Direction {

  UP(new Coordinate(0, 1)),
  DIAGONAL_UP_RIGHT(new Coordinate(1, 1)),
  RIGHT(new Coordinate(1, 0)),
  DIAGONAL_DOWN_RIGHT(new Coordinate(1, -1)),
  DOWN(new Coordinate(0, -1)),
  DIAGONAL_DOWN_LEFT(new Coordinate(-1, -1)),
  LEFT(new Coordinate(-1, 0)),
  DIAGONAL_UP_LEFT(new Coordinate(-1, 1)),
  ;

  private Coordinate coordinate;

  Direction(Coordinate coordinate) {
    this.coordinate = coordinate;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

}
