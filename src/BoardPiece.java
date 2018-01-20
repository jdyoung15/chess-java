import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

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

  public List<Move> findMoves(Board board) {
    List<Move> possibleMoves = findPossibleMoves(board);
    return filterLegalMoves(possibleMoves, board);
  }

  public List<Move> findMoves(Board board, List<Move> previousMoves) {
    return findMoves(board);
  }

  public abstract List<Move> findPossibleMoves(Board board);

  public boolean isChecking(Board board) {
    Moves possibleMoves = new Moves(findPossibleMoves(board));

    Color opponent = Color.findOpponent(piece.getColor());
    int opponentKingPosition = board.findKingPosition(opponent);

    return possibleMoves.containsToPosition(opponentKingPosition);
  }

  public List<Move> filterLegalMoves(List<Move> possibleMoves, Board board) {
    return possibleMoves
      .stream()
      .filter(m -> m.isLegal(board, piece.getColor()))
      .collect(Collectors.toList());
  }

}
