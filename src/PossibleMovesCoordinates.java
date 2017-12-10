import java.util.List;
import java.util.ArrayList;

public class PossibleMovesCoordinates {

  private MoveCoordinates moveCoordinates;
  private Board board;
  private int fromPosition;
  private Color color;

  public PossibleMovesCoordinates(MoveCoordinates moveCoordinates, Board board, int fromPosition, Color color) {
    this.moveCoordinates = moveCoordinates;
    this.board = board;
    this.fromPosition = fromPosition;
    this.color = color;
  }

  public List<Integer> positions() {
    List<Integer> positions = new MoveCoordinatesSquares(moveCoordinates, fromPosition).positions();

    List<Integer> unblockedPositions = new ArrayList<Integer>();
    for (int position : positions) {
      // find square at coordinates
      Square square = board.findSquare(position);
      // if square is unoccupied or occupied by opponent
      if (!square.isOccupied() || square.getPiece().getColor() != color) {
      //   add position to list
        unblockedPositions.add(position);
      }
    }
    return unblockedPositions;
  }

}
