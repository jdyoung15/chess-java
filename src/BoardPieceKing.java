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

  public List<Integer> findMoves(Board board, List<Move> previousMoves) {
    List<Integer> positions = new ArrayList<Integer>();
    positions.addAll(super.findMoves(board));
    positions.addAll(findCastlingMoves(board, previousMoves));
    return positions;
  }

  private List<Integer> findCastlingMoves(Board board, List<Move> previousMoves) {
    List<Integer> positions = new ArrayList<Integer>();

    for (CastlingSide cs : CastlingSide.values()) {
      if (cs.canCastle(position, piece.getColor(), board, previousMoves)) {
        positions.add(cs.findKingToPosition(position));
      }
    }

    return positions;
  }

}
