import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class MoveDirections {

  private PieceType pieceType;
  private Color color;

  public MoveDirections(PieceType pieceType, Color color) {
    this.pieceType = pieceType;
    this.color = color;
  }

  public List<MoveDirection> moveDirections() {
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
