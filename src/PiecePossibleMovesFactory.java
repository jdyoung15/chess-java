public class PiecePossibleMovesFactory {

  public static PiecePossibleMoves getPiecePossibleMoves(PieceType pieceType, Board board, int fromPosition, Color color) {
    switch (pieceType) {
      case ROOK:
        return new RookMoves(board, fromPosition, color);
      case KNIGHT:
        return new KnightPossibleMoves(board, fromPosition, color);
      default:
        return null;
    }
  }

}
