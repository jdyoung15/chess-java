import java.util.List;
import java.util.ArrayList;

public class MovesFinder {
  
  //private Board board;
  //private int position;
  //private PieceType pieceType;

  public static List<SquarePosition> findMoves(Board board, SquarePosition fromPosition, PieceType pieceType, Color color) {
    // get directions for piece type
    List<MoveDirection> moveDirections = MoveDirectionFinder.findMoveDirections(pieceType, color);
    List<SquarePosition> squarePositions = new ArrayList<SquarePosition>();
    // for each direction
    for (MoveDirection moveDirection : moveDirections) {
      //   find squares in each direction, from start position
      List<SquarePosition> squarePositionsDirection = 
        findSquarePositionsInDirection(fromPosition, moveDirection.getDirection(), board);
      //   filter unblocked squares
      // TODO
      squarePositions.addAll(squarePositionsDirection);
    }
    // return squares
    return squarePositions;
  }

  private static List<SquarePosition> findSquarePositionsInDirection(SquarePosition fromPosition, Direction direction, Board board) {
    System.out.println(String.format("direction: %s, fromPosition: %s", direction.name(), fromPosition.toString()));
    System.out.println(String.format("position: %d", fromPosition.getPosition()));
    // init list of square positions
    List<SquarePosition> squarePositions = new ArrayList<SquarePosition>();

    int vertical = direction.getVertical();
    int horizontal = direction.getHorizontal();
    SquarePosition currentPosition = 
        new SquarePosition(fromPosition.getRow() + vertical, fromPosition.getCol() + horizontal);
    while (currentPosition.inBounds()) {
      squarePositions.add(currentPosition);
      currentPosition = 
        new SquarePosition(currentPosition.getRow() + vertical, currentPosition.getCol() + horizontal);
      System.out.println(String.format("position: %d", currentPosition.getPosition()));
    }

    return squarePositions;
  }

}
