import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardPiecePawn extends BoardPiece {

  public BoardPiecePawn(Color color, int position) {
    super(new Piece(color, PieceType.PAWN), position);
  }

  public List<Integer> findMoves(Board board) {
    // stub method -- use findMoves(Board board, List<Move> previousMoves)
    return null;
  }

  public List<Integer> findMoves(Board board, List<Move> previousMoves) {
    List<Integer> possibleMoves = findPossibleMoves(board);
    possibleMoves.addAll(findEnPassantMoves(board, previousMoves));
    return findLegalMoves(position, possibleMoves, board);
  }

  public List<Integer> findPossibleMoves(Board board) {
    List<Integer> positions = new ArrayList<Integer>();

    BoardDirection pawnDirection = BoardPositioning.findDirection(piece.getColor());
    Iterable<MoveCoordinates> attackCoordinates = Arrays.asList(
      new MoveCoordinates(BoardDirection.LEFT, 1, pawnDirection, 1),
      new MoveCoordinates(BoardDirection.RIGHT, 1, pawnDirection, 1));

    BoardPiece boardPieceCoordinatesBased = 
      new BoardPieceCoordinatesBased(piece, position, attackCoordinates, new CheckSquareIsAttackable());
    positions.addAll(boardPieceCoordinatesBased.findPossibleMoves(board));

    MoveDirection pawnMoveDirection = new MoveDirection(pawnDirection, BoardDirection.NONE);
    List<MoveDirection> moveDirections = Arrays.asList(pawnMoveDirection);
    int pawnStartRow = 
      piece.getColor() == Color.WHITE ? BoardPositioning.PAWN_WHITE_START_ROW : BoardPositioning.PAWN_BLACK_START_ROW;
    int numSquaresCanAdvance = 
      BoardPositioning.findRow(position) == pawnStartRow ? 2 : 1;

    BoardPiece boardPieceDirectionBased = 
      new BoardPieceDirectionBased(piece, position, moveDirections, numSquaresCanAdvance, new CheckSquareIsOccupiable());
    positions.addAll(boardPieceDirectionBased.findPossibleMoves(board));

    return positions;
  }

  public List<Integer> findEnPassantMoves(Board board, List<Move> previousMoves) {
    List<Integer> positions = new ArrayList<Integer>();
    for (EnPassantDirection epd : EnPassantDirection.values()) {
      if (epd.canEnPassant(position, piece.getColor(), board, previousMoves)) {
        positions.add(epd.findAttackingPawnToPosition(position, piece.getColor()));
      }
    }
    return positions;
  }

}
