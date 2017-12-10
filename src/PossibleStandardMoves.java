import java.util.List;
import java.util.ArrayList;

public class PossibleStandardMoves {
  
  private Board board;
  private int fromPosition;
  private PieceType pieceType;
  private Color color;

  public PossibleStandardMoves(Board board, int fromPosition, PieceType pieceType, Color color) {
    this.board = board;
    this.fromPosition = fromPosition;
    this.pieceType = pieceType;
    this.color = color;
  }

  public List<Integer> positions() {
    List<Integer> positions = new ArrayList<Integer>();

    switch (pieceType) {
      case ROOK:
        positions.addAll(new RookMoves(board, fromPosition, color).positions());
        break;
    }

    // AttackOnly, AttackOrMove, MoveOnly, UnblockedAttackOrMoveSquares
    // findSquaresAtCoordinates, findSquaresInDirection
    // if pawn
    //  get square up and left
    //  if square is occupied by opponent
    //    add position of square to list
    //  get square up and right 
    //  if square is occupied by opponent
    //    add position of square to list
    //  get square up one
    //  if square is unoccupied
    //    add position to list
    //  if pawn in start row
    //    get square up two
    //    if square is unoccupied
    //    add position to list
    // if rook
    //  up direction, filtered
    //  down direction, filtered
    //  left direction, filtered
    //  right direction, filtered
    // if knight
    //  coordinates

    //boolean inStartRow = true;
    //List<MoveDirection> moveDirections = 
    //  new MoveDirections(pieceType, Board.findDirection(color), inStartRow).moveDirections();
    //List<Integer> positions = new ArrayList<Integer>();

    //for (MoveDirection moveDirection : moveDirections) {
    //  List<Integer> positionsDirection = 
    //    new DirectionSquares(fromPosition, moveDirection.getDirection()).positions();

    //  List<Square> squares = board.findSquares(positionsDirection);
    //  positionsDirection = 
    //    new FilteredDirectionSquares(
    //      positionsDirection, 
    //      squares, 
    //      moveDirection.getLimit(), 
    //      color,
    //      moveDirection.getAttackOnly()).positions();

    //  positions.addAll(positionsDirection);
    //}

    return positions;
  }

}
