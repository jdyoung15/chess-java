import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardPiecePawn extends BoardPiece {

  public BoardPiecePawn(Color color, int position) {
    super(new Piece(color, PieceType.PAWN), position);
  }

  public Moves findMoves(Board board, Moves previousMoves) {
    Moves possibleMoves = new Moves();
    possibleMoves.addAll(findPossibleMoves(board));
    possibleMoves.addAll(findEnPassantMoves(board, previousMoves));
    return possibleMoves.filterLegalMoves(board, piece.getColor());
  }

  public Moves findPossibleMoves(Board board) {
    Moves possibleMoves = new Moves();

    BoardDirection pawnDirection = BoardPositioning.findDirection(piece.getColor());
    Iterable<MoveCoordinates> attackCoordinates = Arrays.asList(
      new MoveCoordinates(BoardDirection.LEFT, 1, pawnDirection, 1),
      new MoveCoordinates(BoardDirection.RIGHT, 1, pawnDirection, 1));

    BoardPiece boardPieceCoordinatesBased = 
      new BoardPieceCoordinatesBased(piece, position, attackCoordinates, new CheckSquareIsAttackable());
    possibleMoves.addAll(boardPieceCoordinatesBased.findPossibleMoves(board));

    MoveDirection pawnMoveDirection = new MoveDirection(pawnDirection, BoardDirection.NONE);
    List<MoveDirection> moveDirections = Arrays.asList(pawnMoveDirection);
    int pawnStartRow = 
      piece.getColor() == Color.WHITE ? BoardPositioning.PAWN_WHITE_START_ROW : BoardPositioning.PAWN_BLACK_START_ROW;
    int numSquaresCanAdvance = 
      BoardPositioning.findRow(position) == pawnStartRow ? 2 : 1;

    BoardPiece boardPieceDirectionBased = 
      new BoardPieceDirectionBased(piece, position, moveDirections, numSquaresCanAdvance, new CheckSquareIsOccupiable());
    possibleMoves.addAll(boardPieceDirectionBased.findPossibleMoves(board));

    return possibleMoves;
  }

  public Moves findEnPassantMoves(Board board, Moves previousMoves) {
    Moves enPassantMoves = new Moves();
    for (EnPassantDirection epd : EnPassantDirection.values()) {
      if (epd.canEnPassant(position, piece.getColor(), board, previousMoves)) {
        enPassantMoves.add(epd.findAttackingPawnMove(position, piece.getColor()));
      }
    }
    return enPassantMoves;
  }

}
