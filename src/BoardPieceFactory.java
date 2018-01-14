public class BoardPieceFactory {

  public static BoardPiece getBoardPiece(Piece piece, int position) {
    switch (piece.getPieceType()) {
      case PAWN:
        return new BoardPiecePawn(piece.getColor(), position);
      case ROOK:
        return new BoardPieceRook(piece.getColor(), position);
      case KNIGHT:
        return new BoardPieceKnight(piece.getColor(), position);
      case BISHOP:
        return new BoardPieceBishop(piece.getColor(), position);
      case QUEEN:
        return new BoardPieceQueen(piece.getColor(), position);
      case KING:
        return new BoardPieceKing(piece.getColor(), position);
      default:
        return null;
    }
  }

}
