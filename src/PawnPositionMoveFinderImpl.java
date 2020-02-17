import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Responsible for finding a pawn's possible moves.
 */
public class PawnPositionMoveFinderImpl implements PositionMoveFinder {

  private static final List<OrientedCoordinate> ATTACK_COORDINATES = List.of(
    new OrientedCoordinate(1, 1),
    new OrientedCoordinate(-1, 1)
  );

  private static final List<OrientedCoordinate> NON_START_UNOCCUPIED_COORDINATES = List.of(
    new OrientedCoordinate(0, 1)
  );

  private static final List<OrientedCoordinate> START_UNOCCUPIED_COORDINATES = List.of(
    new OrientedCoordinate(0, 1),
    new OrientedCoordinate(0, 2)
  );

  private PositionMoveFinder enPassantPositionMoveFinder;

  public PawnPositionMoveFinderImpl() {
    enPassantPositionMoveFinder = new EnPassantPositionMoveFinderImpl();
  }

  @Override
  public List<Move> findMoves(Board board, int fromPosition, List<Move> previousMoves) {
    List<Move> moves = new ArrayList<>();
    ATTACK_COORDINATES.forEach(coord -> moves.addAll(findRelativeCoordinateMoves(
      board, fromPosition, coord, board::opponentAt)));

    Piece piece = board.getPieceAt(fromPosition);

    List<OrientedCoordinate> unoccupied = Positioning.inStartRow(fromPosition, piece)
      ? START_UNOCCUPIED_COORDINATES
      : NON_START_UNOCCUPIED_COORDINATES;

    unoccupied.forEach(coord -> moves.addAll(findRelativeCoordinateMoves(
      board, fromPosition, coord, (p, color) -> !board.occupiedAt(p))));

    moves.addAll(enPassantPositionMoveFinder.findMoves(board, fromPosition, previousMoves));
    return moves;
  }

  /**
   * Returns the moves where the pawn can move, using the given function to determine
   * whether the pawn can occupy a square.
   */
  private List<Move> findRelativeCoordinateMoves(
    Board board,
    int fromPosition,
    OrientedCoordinate coordinate,
    BiFunction<Integer, Color, Boolean> canOccupySquare)
  {
    List<Move> moves = new ArrayList<>();
    Piece piece = board.getPieceAt(fromPosition);
    Color currentPlayer = piece.getColor();
    int toPosition = Positioning.calculateNewPosition(fromPosition, coordinate, currentPlayer);
    if (Positioning.isValidPosition(toPosition) && canOccupySquare.apply(toPosition, currentPlayer)) {
      moves.add(new Move(fromPosition, toPosition));
    }
    return moves;
  }

}
