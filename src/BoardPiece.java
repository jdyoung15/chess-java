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

  public Moves findMoves(Board board) {
    Moves possibleMoves = findPossibleMoves(board);
    return possibleMoves.filterLegalMoves(board, piece.getColor());
  }

  public Moves findMoves(Board board, Moves previousMoves) {
    return findMoves(board);
  }

  public abstract Moves findPossibleMoves(Board board);

  public boolean isChecking(Board board) {
    Moves possibleMoves = findPossibleMoves(board);

    Color opponent = Color.findOpponent(piece.getColor());
    int opponentKingPosition = board.findKingPosition(opponent);

    return possibleMoves.containsMovesToPosition(opponentKingPosition);
  }

}
