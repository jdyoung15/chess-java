import java.util.List;

/**
 * Responsible for finding a bishop's possible moves.
 */
public class BishopPositionMoveFinderImpl extends DirectionPositionMoveFinder {

  private static final List<Direction> DIRECTIONS = List.of(
    Direction.DIAGONAL_UP_RIGHT,
    Direction.DIAGONAL_DOWN_RIGHT,
    Direction.DIAGONAL_DOWN_LEFT,
    Direction.DIAGONAL_UP_LEFT
  );

  public BishopPositionMoveFinderImpl() {
    super(DIRECTIONS);
  }

}
