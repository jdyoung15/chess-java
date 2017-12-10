import java.util.List;
import java.util.ArrayList;

public class CoordinatesPiecePossibleMoves implements PiecePossibleMoves {

  private Iterable<MoveCoordinates> moveCoordinatesList;
  private Board board;
  private int fromPosition;
  private Color color;

  public CoordinatesPiecePossibleMoves(
    Iterable<MoveCoordinates> moveCoordinatesList, 
    Board board, 
    int fromPosition, 
    Color color) 
  {
    this.moveCoordinatesList = moveCoordinatesList;
    this.board = board;
    this.fromPosition = fromPosition;
    this.color = color;
  }

  public List<Integer> positions() {
    List<Integer> positions = new ArrayList<Integer>();
    for (MoveCoordinates moveCoordinates : moveCoordinatesList) {
      if (!board.inBounds(fromPosition, moveCoordinates)) {
        continue;
      }
      int position = Board.calculateNewPosition(fromPosition, moveCoordinates);
      Square square = board.findSquare(position);
      if (!square.isOccupied() || square.getPiece().getColor() != color) {
        positions.add(position);
      }
    }
    return positions;
  }

}
