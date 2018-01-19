import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class BoardPieceDirectionBased extends BoardPiece {

  private Iterable<MoveDirection> moveDirections;
  private int limitPerDirection = Integer.MAX_VALUE;

  public BoardPieceDirectionBased(
    Piece piece,
    int position, 
    Iterable<MoveDirection> moveDirections)
  {
    super(piece, position);
    this.moveDirections = moveDirections;
  }

  public BoardPieceDirectionBased(
    Piece piece,
    int position, 
    Iterable<MoveDirection> moveDirections,
    int limitPerDirection,
    CheckSquare canMoveHere)
  {
    super(piece, position, canMoveHere);
    this.moveDirections = moveDirections;
    this.limitPerDirection = limitPerDirection;
  }

  public List<Integer> findPossibleMoves(Board board) {
    List<Integer> positions = new ArrayList<Integer>();
    for (MoveDirection moveDirection : moveDirections) {
      positions.addAll(
        moveDirection.findUnblockedSquares(position, board, piece.getColor(), canMoveHere, limitPerDirection));
    }
    return positions;
  }

}
