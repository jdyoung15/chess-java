import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardPieceKing extends BoardPieceCoordinatesBased {

  private static final Iterable<MoveCoordinates> moveCoordinatesList = Arrays.asList(
    new MoveCoordinates(BoardDirection.RIGHT, 1, BoardDirection.UP, 1),
    new MoveCoordinates(BoardDirection.RIGHT, 1, BoardDirection.UP, 0),
    new MoveCoordinates(BoardDirection.RIGHT, 1, BoardDirection.DOWN, 1),
    new MoveCoordinates(BoardDirection.RIGHT, 0, BoardDirection.DOWN, 1),
    new MoveCoordinates(BoardDirection.LEFT, 1, BoardDirection.DOWN, 1),
    new MoveCoordinates(BoardDirection.LEFT, 1, BoardDirection.DOWN, 0),
    new MoveCoordinates(BoardDirection.LEFT, 1, BoardDirection.UP, 1),
    new MoveCoordinates(BoardDirection.LEFT, 0, BoardDirection.UP, 1)
  );

  public BoardPieceKing(Color color, int position) {
    super(new Piece(color, PieceType.KING), position, moveCoordinatesList);  
  }

  public Moves findMoves(Board board, Moves previousMoves) {
    Moves possibleMoves = new Moves();
    possibleMoves.addAll(super.findPossibleMoves(board));
    possibleMoves.addAll(findCastlingMoves(board, previousMoves));
    return possibleMoves.filterLegalMoves(board, piece.getColor());
  }

  private Moves findCastlingMoves(Board board, Moves previousMoves) {
    Moves castlingMoves = new Moves();
    for (CastlingSide cs : CastlingSide.values()) {
      if (cs.canCastle(position, piece.getColor(), board, previousMoves)) {
        castlingMoves.add(cs.findKingMove(position));
      }
    }
    return castlingMoves;
  }

}
