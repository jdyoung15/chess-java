/**
 * Represents a coordinate whose values are not from an objective, "bird's eye" view
 * of the board (as is the case with {@link Coordinate}, but rather from the perspective
 * of the piece oriented toward the opponent's side of the board.
 *
 * Examples:
 *   - OrientedCoordinate(1, 1) for {@code Color.WHITE} is equivalent to Coordinate(-1, -1)
 *   - OrientedCoordinate(1, 1) for {@code Color.BLACK} is equivalent to Coordinate(1, 1)
 */
public class OrientedCoordinate extends Coordinate {

  public OrientedCoordinate(int x, int y) {
    super(x, y);
  }

}
