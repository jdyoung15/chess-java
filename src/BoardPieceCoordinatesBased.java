import java.util.List;
import java.util.ArrayList;

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

  public List<Move> findPossibleMoves(Board board) {
    // find square positions at coordinates
    // for each square position
    //   find square at position
    //   if can move to square
    //     add position to list
    //   if blocking condition satisfied
    //     break
    // return square positions

    int fromPosition = position;

    List<Move> possibleMoves = new ArrayList<Move>();
    for (MoveCoordinates moveCoordinates : moveCoordinatesList) {
      int toPosition = BoardPositioning.findPosition(fromPosition, moveCoordinates);
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
