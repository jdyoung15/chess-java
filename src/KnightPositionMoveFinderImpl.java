import java.util.List;

/**
 * Responsible for finding a knight's possible moves.
 */
public class KnightPositionMoveFinderImpl extends CoordinatePositionMoveFinder {

  private static final List<Coordinate> COORDINATES = List.of(
    new Coordinate(1, 2),
    new Coordinate(2, 1),
    new Coordinate(2, -1),
    new Coordinate(1, -2),
    new Coordinate(-1, -2),
    new Coordinate(-2, -1),
    new Coordinate(-2, 1),
    new Coordinate(-1, 2)
  );

  public KnightPositionMoveFinderImpl() {
    super(COORDINATES);
  }

}
