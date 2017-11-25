import java.util.List;
import java.util.ArrayList;

public class MovesFinder {
  
  //private Board board;
  //private int position;
  //private PieceType pieceType;

  public static List<Integer> findMoves(Board board, int position, PieceType pieceType, Color color) {
    // get directions for piece type
    List<MoveDirection> moveDirections = MoveDirectionFinder.findMoveDirections(pieceType, color);
    List<Integer> squarePositions = new ArrayList<Integer>();
    // for each direction
    for (MoveDirection moveDirection : moveDirections) {
      //   find squares in each direction, from start position
      List<Integer> squarePositionsDirection = 
        findSquarePositionsInDirection(position, moveDirection.getDirection(), board);
      //   filter unblocked squares
      // TODO
      squarePositions.addAll(squarePositionsDirection);
    }
    // return squares
    return squarePositions;
  }

  private static List<Integer> findSquarePositionsInDirection(int position, Direction direction, Board board) {
    // init list of square positions
    List<Integer> squarePositions = new ArrayList<Integer>();

    int currentPosition = position;
    int horizontal = direction.getHorizontal();
    int vertical = direction.getVertical();
    while (board.isWithinBoundaries(currentPosition, horizontal, vertical)) {
      // find first square in move direction
      currentPosition = board.calculateNewPosition(currentPosition, horizontal, vertical);
      squarePositions.add(currentPosition);
    }

    return squarePositions;
  }

}
