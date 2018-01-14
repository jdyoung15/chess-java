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

  public List<Integer> findMoves(Board board) {
    List<Integer> possibleMoves = findPossibleMoves(board);
    return findLegalMoves(position, possibleMoves, board);
  }

  public List<Integer> findPossibleMoves(Board board) {
    int fromPosition = position;
    List<Integer> positions = new ArrayList<Integer>();
    for (MoveDirection moveDirection : moveDirections) {
      List<Integer> directionPositions = new MoveDirectionSquares(moveDirection, fromPosition).positions();
      directionPositions = directionPositions
        .stream()
        .limit(limitPerDirection)
        .collect(Collectors.toList());

      positions.addAll(
        new UnblockedSquares(directionPositions, board, piece.getColor(), canMoveHere).positions());
    }
    return positions;
  }

}
