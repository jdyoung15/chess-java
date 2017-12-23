public class PiecePossibleMovesFactory {

  public static PiecePossibleMoves getPiecePossibleMoves(PieceType pieceType, Board board, int fromPosition, Color color) {
    switch (pieceType) {
      case PAWN:
        return new PawnPossibleMoves(board, fromPosition, color);
      case ROOK:
        return new RookPossibleMoves(board, fromPosition, color);
      case KNIGHT:
        return new KnightPossibleMoves(board, fromPosition, color);
      case BISHOP:
        return new BishopPossibleMoves(board, fromPosition, color);
      case QUEEN:
        return new QueenPossibleMoves(board, fromPosition, color);
      case KING:
        return new KingPossibleMoves(board, fromPosition, color);
      default:
        return null;
    }
  }

}
