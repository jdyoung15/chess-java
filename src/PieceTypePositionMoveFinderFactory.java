/**
 * Factory class for finding the appropriate {@link PositionMoveFinder} implementation
 * for a given piece type.
 */
public class PieceTypePositionMoveFinderFactory {

  /**
   * Returns the appropriate {@link PositionMoveFinder} implementation for the given
   * piece type.
   */
  public static PositionMoveFinder getMoveFinder(Piece.Type pieceType) {
    switch (pieceType) {
      case PAWN:
        return new PawnPositionMoveFinderImpl();
      case ROOK:
        return new RookPositionMoveFinderImpl();
      case KNIGHT:
        return new KnightPositionMoveFinderImpl();
      case BISHOP:
        return new BishopPositionMoveFinderImpl();
      case QUEEN:
        return new QueenPositionMoveFinderImpl();
      case KING:
        return new KingPositionMoveFinderImpl();
      default:
        return null;
    }
  }

}
