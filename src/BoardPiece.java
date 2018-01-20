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

  public List<Integer> findMoves(Board board) {
    List<Integer> possibleMoves = findPossibleMoves(board);
    return filterLegalMoves(possibleMoves, board);
  }

  public List<Integer> findMoves(Board board, List<Move> previousMoves) {
    return findMoves(board);
  }

  public abstract List<Integer> findPossibleMoves(Board board);

  public boolean isChecking(Board board) {
    List<Integer> possibleMoves = findPossibleMoves(board);

    Color opponent = Color.findOpponent(piece.getColor());
    int opponentKingPosition = board.findKingPosition(opponent);

    return possibleMoves.contains(opponentKingPosition);
  }

  protected List<Integer> filterLegalMoves(List<Integer> possibleMoves, Board board) {
    List<Integer> validPositions = new ArrayList<Integer>();
    for (int toPosition : possibleMoves) {
      Move move = new Move(position, toPosition);
      if (move.isLegal(board, piece.getColor())) {
        validPositions.add(toPosition);
      }
    }
    return validPositions;
  }

}
