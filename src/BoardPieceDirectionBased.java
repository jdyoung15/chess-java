import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A board piece that moves in directions, eg up or left or diagonally down-right.
 * Directions represented by MoveDirection.
 */
public class BoardPieceDirectionBased extends BoardPiece {

  private Iterable<MoveDirection> moveDirections;
  // by default, a piece can move an unlimited number of squares in a given direction
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

  public Moves findPossibleMoves(Board board) {
    Moves possibleMoves = new Moves();
    for (MoveDirection moveDirection : moveDirections) {
      possibleMoves.addAll(
        moveDirection.findUnblockedMoves(position, board, piece.getColor(), canMoveHere, limitPerDirection));
    }
    return possibleMoves;
  }

}
