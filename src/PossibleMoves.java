import java.util.List;
import java.util.ArrayList;

public class PossibleMoves {
  
  private Board board;
  private int fromPosition;
  private PieceType pieceType;
  private Color color;
  private List<Move> previousMoves;

  public PossibleMoves(Board board, int fromPosition, PieceType pieceType, Color color, List<Move> previousMoves) {
    this.board = board;
    this.fromPosition = fromPosition;
    this.pieceType = pieceType;
    this.color = color;
    this.previousMoves = previousMoves;
  }

  public List<Integer> positions() {
    List<Integer> positions = new ArrayList<Integer>();

    positions.addAll(new PossibleStandardMoves(board, fromPosition, pieceType, color).positions());

    if (pieceType == PieceType.PAWN) {
      positions.addAll(new EnPassant(fromPosition, board, color, previousMoves).positions());
    }

    // create PossibleStandardMoves and add positions
    // if pawn
    //   create EnPassant and add positions
    // else if king
    //   create Castling and add positions
    // return positions

    //List<MoveDirection> moveDirections = 
    //  new MoveDirections(pieceType, Board.findDirection(color), hasMoved).moveDirections();
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
