import java.util.List;
import java.util.ArrayList;

/**
 * A board piece that moves to specific squares 
 * (whose relative positions are represented by MoveCoordinates).
 */
public class BoardPieceCoordinatesBased extends BoardPiece {

  private Iterable<MoveCoordinates> moveCoordinatesList;

  public BoardPieceCoordinatesBased(
    Piece piece,
    int position,
    Iterable<MoveCoordinates> moveCoordinatesList)
  {
    super(piece, position);
    this.moveCoordinatesList = moveCoordinatesList;
  }

  public BoardPieceCoordinatesBased(
    Piece piece,
    int position,
    Iterable<MoveCoordinates> moveCoordinatesList,
    CheckSquare canMoveHere)
  {
    super(piece, position, canMoveHere);
    this.moveCoordinatesList = moveCoordinatesList;
  }

  public Moves findPossibleMoves(Board board) {
    int fromPosition = position;

    Moves possibleMoves = new Moves();
    for (MoveCoordinates moveCoordinates : moveCoordinatesList) {
      int toPosition = moveCoordinates.findPosition(fromPosition);
      if (toPosition == BoardPositioning.INVALID_POSITION) {
        continue;
      }

      Square square = board.findSquare(toPosition);
      if (canMoveHere.test(square, piece.getColor())) {
        possibleMoves.add(new Move(fromPosition, toPosition));
      }
    }
    return possibleMoves;
  }

}
