import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class MoveDirectionFinder {

  public static List<MoveDirection> findMoveDirections(PieceType pieceType, Color color) {
    switch (pieceType) {
      case PAWN:
        // todo
        return new ArrayList<MoveDirection>();
      case ROOK:
       return new ArrayList<MoveDirection>(
        Arrays.asList(
          new MoveDirection(Direction.UP, Integer.MAX_VALUE, false),
          new MoveDirection(Direction.RIGHT, Integer.MAX_VALUE, false),
          new MoveDirection(Direction.DOWN, Integer.MAX_VALUE, false),
          new MoveDirection(Direction.LEFT, Integer.MAX_VALUE, false))); 
    }
    return new ArrayList<MoveDirection>();
  }


}
