import java.util.List;

/**
 * Interface defining possible move finding behaviors from a certain position.
 */
public interface PositionMoveFinder {

  /**
   * Returns all possible moves from the given position.
   * May include illegal moves that expose the current player's king to checked.
   */
  List<Move> findMoves(Board board, int fromPosition, List<Move> previousMoves);

}
