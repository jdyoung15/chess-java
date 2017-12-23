import java.util.List;
import java.util.ArrayList;

public class CoordinatesPiecePossibleMoves extends PiecePossibleMoves {

  private Iterable<MoveCoordinates> moveCoordinatesList;

  public CoordinatesPiecePossibleMoves(
    Iterable<MoveCoordinates> moveCoordinatesList, 
    Board board, 
    int fromPosition, 
    Color color)
  {
    super(board, fromPosition, color);
    this.moveCoordinatesList = moveCoordinatesList;
  }

  public CoordinatesPiecePossibleMoves(
    Iterable<MoveCoordinates> moveCoordinatesList, 
    Board board, 
    int fromPosition, 
    Color color,
    CheckSquare canMoveHere)
  {
    super(board, fromPosition, color, canMoveHere);
    this.moveCoordinatesList = moveCoordinatesList;
  }

  public List<Integer> positions() {
    // find square positions at coordinates
    // for each square position
    //   find square at position
    //   if can move to square
    //     add position to list
    //   if blocking condition satisfied
    //     break
    // return square positions

    List<Integer> positions = new ArrayList<Integer>();
    for (MoveCoordinates moveCoordinates : moveCoordinatesList) {
      if (!board.inBounds(fromPosition, moveCoordinates)) {
        continue;
      }
      int position = Board.calculateNewPosition(fromPosition, moveCoordinates);
      Square square = board.findSquare(position);
      if (canMoveHere.test(square, color)) {
        positions.add(position);
      }
    }
    return positions;
  }

}
