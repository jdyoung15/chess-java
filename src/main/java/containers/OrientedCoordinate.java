package containers;

/**
 * Represents a coordinate whose values are not from an objective, "bird's eye" view
 * of the board (as is the case with {@link Coordinate}, but rather from the perspective
 * of the piece oriented toward the opponent's side of the board.
 *
 * Examples:
 *   - containers.OrientedCoordinate(1, 1) for {@code containers.Color.WHITE} is equivalent to containers.Coordinate(-1, -1)
 *   - containers.OrientedCoordinate(1, 1) for {@code containers.Color.BLACK} is equivalent to containers.Coordinate(1, 1)
 */
public class OrientedCoordinate extends Coordinate {

  public OrientedCoordinate(int x, int y) {
    super(x, y);
  }

}
