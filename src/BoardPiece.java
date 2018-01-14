import java.util.List;
import java.util.ArrayList;

public abstract class BoardPiece {

  protected Piece piece;
  protected int position;
  protected CheckSquare canMoveHere;

  public BoardPiece(Piece piece, int position) {
    this(piece, position, new CheckSquareIsOccupiableOrAttackable());
  }

  public BoardPiece(Piece piece, int position, CheckSquare canMoveHere) {
    this.piece = piece;
    this.position = position;
    this.canMoveHere = canMoveHere;
  }

  public abstract List<Integer> findMoves(Board board);

  protected List<Integer> findLegalMoves(int fromPosition, List<Integer> possibleMoves, Board board) {
    List<Integer> validPositions = new ArrayList<Integer>();
    for (int toPosition : possibleMoves) {
      if (isMoveLegal(fromPosition, toPosition, board)) {
        validPositions.add(toPosition);
      }
    }
    return validPositions;
  }

  private boolean isMoveLegal(int fromPosition, int toPosition, Board board) {
    Board copy = board.copy();  
    copy.move(fromPosition, toPosition);
    Check check = new Check(piece.getColor(), copy);
    return !check.isCheck();
  }

}
