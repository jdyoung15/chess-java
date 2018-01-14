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

  public List<Integer> findMoves(Board board) {
    List<Integer> possibleMoves = findPossibleMoves(board);
    return findLegalMoves(position, possibleMoves, board);
  }

  public List<Integer> findPossibleMoves(Board board) {
    // find square positions at coordinates
    // for each square position
    //   find square at position
    //   if can move to square
    //     add position to list
    //   if blocking condition satisfied
    //     break
    // return square positions

    int fromPosition = position;

    List<Integer> positions = new ArrayList<Integer>();
    for (MoveCoordinates moveCoordinates : moveCoordinatesList) {
      int position = BoardPositioning.findPosition(fromPosition, moveCoordinates);
      if (position == BoardPositioning.INVALID_POSITION) {
        continue;
      }

      Square square = board.findSquare(position);
      if (canMoveHere.test(square, piece.getColor())) {
        positions.add(position);
      }
    }
    return positions;
  }

}
