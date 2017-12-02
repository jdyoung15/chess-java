import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class MoveDirections {

  private PieceType pieceType;
  private Direction playerDirection;
  private boolean hasMoved;

  public MoveDirections(PieceType pieceType, Direction playerDirection, boolean hasMoved) {
    this.pieceType = pieceType;
    this.playerDirection = playerDirection;
    this.hasMoved = hasMoved;
  }

  public List<MoveDirection> moveDirections() {
    switch (pieceType) {
      case PAWN:
        boolean canAdvanceTwoSquares = !hasMoved;
        int numAdvanceSquares = canAdvanceTwoSquares ? 2 : 1;

        switch (playerDirection) {
          case UP:
            return new ArrayList<MoveDirection>(
             Arrays.asList(
               new MoveDirection(Direction.UP, numAdvanceSquares, false),
               new MoveDirection(Direction.UP_RIGHT, 1, true),
               new MoveDirection(Direction.UP_LEFT, 1, true)));
          case DOWN:
            return new ArrayList<MoveDirection>(
             Arrays.asList(
               new MoveDirection(Direction.DOWN, numAdvanceSquares, false),
               new MoveDirection(Direction.DOWN_RIGHT, 1, true),
               new MoveDirection(Direction.DOWN_LEFT, 1, true)));
          default: 
            return new ArrayList<MoveDirection>();
        }

      case ROOK:
       return new ArrayList<MoveDirection>(
        Arrays.asList(
          new MoveDirection(Direction.UP, Integer.MAX_VALUE, false),
          new MoveDirection(Direction.RIGHT, Integer.MAX_VALUE, false),
          new MoveDirection(Direction.DOWN, Integer.MAX_VALUE, false),
          new MoveDirection(Direction.LEFT, Integer.MAX_VALUE, false))); 

      case KNIGHT:
       return new ArrayList<MoveDirection>(
        Arrays.asList(
          new MoveDirection(Direction.UP_TWO_RIGHT_ONE, 1, false),
          new MoveDirection(Direction.UP_ONE_RIGHT_TWO, 1, false),
          new MoveDirection(Direction.DOWN_ONE_RIGHT_TWO, 1, false),
          new MoveDirection(Direction.DOWN_TWO_RIGHT_ONE, 1, false),
          new MoveDirection(Direction.DOWN_TWO_LEFT_ONE, 1, false),
          new MoveDirection(Direction.DOWN_ONE_LEFT_TWO, 1, false),
          new MoveDirection(Direction.UP_ONE_LEFT_TWO, 1, false),
          new MoveDirection(Direction.UP_TWO_LEFT_ONE, 1, false)));

      case BISHOP:
       return new ArrayList<MoveDirection>(
        Arrays.asList(
          new MoveDirection(Direction.UP_RIGHT, Integer.MAX_VALUE, false),
          new MoveDirection(Direction.DOWN_RIGHT, Integer.MAX_VALUE, false),
          new MoveDirection(Direction.DOWN_LEFT, Integer.MAX_VALUE, false),
          new MoveDirection(Direction.UP_LEFT, Integer.MAX_VALUE, false))); 

      case QUEEN:
       return new ArrayList<MoveDirection>(
        Arrays.asList(
          new MoveDirection(Direction.UP_RIGHT, Integer.MAX_VALUE, false),
          new MoveDirection(Direction.DOWN_RIGHT, Integer.MAX_VALUE, false),
          new MoveDirection(Direction.DOWN_LEFT, Integer.MAX_VALUE, false),
          new MoveDirection(Direction.UP_LEFT, Integer.MAX_VALUE, false),
          new MoveDirection(Direction.UP, Integer.MAX_VALUE, false),
          new MoveDirection(Direction.RIGHT, Integer.MAX_VALUE, false),
          new MoveDirection(Direction.DOWN, Integer.MAX_VALUE, false),
          new MoveDirection(Direction.LEFT, Integer.MAX_VALUE, false))); 

      case KING:
       return new ArrayList<MoveDirection>(
        Arrays.asList(
          new MoveDirection(Direction.UP_RIGHT, 1, false),
          new MoveDirection(Direction.DOWN_RIGHT, 1, false),
          new MoveDirection(Direction.DOWN_LEFT, 1, false),
          new MoveDirection(Direction.UP_LEFT, 1, false),
          new MoveDirection(Direction.UP, 1, false),
          new MoveDirection(Direction.RIGHT, 1, false),
          new MoveDirection(Direction.DOWN, 1, false),
          new MoveDirection(Direction.LEFT, 1, false))); 

    }
    return new ArrayList<MoveDirection>();
  }


}
