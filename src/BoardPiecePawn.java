import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardPiecePawn extends BoardPiece {

  public BoardPiecePawn(Color color, int position) {
    super(new Piece(color, PieceType.PAWN), position);
  }

  public Moves findMoves(Board board, Moves previousMoves) {
    Moves moves = new Moves();
    moves.addAll(findPossibleMoves(board));
    moves.addAll(findEnPassantMoves(board, previousMoves));
    return moves.filterLegalMoves(board, piece.getColor());
  }

  public Moves findPossibleMoves(Board board) {
    Moves possibleMoves = new Moves();
    possibleMoves.addAll(findCoordinateMoves(board));
    possibleMoves.addAll(findDirectionMoves(board));
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

  private Moves findCoordinateMoves(Board board) {
    Iterable<MoveCoordinates> coordinates = findAttackCoordinates(piece.getColor());
    BoardPiece boardPiece = 
      new BoardPieceCoordinatesBased(piece, position, coordinates, new CheckSquareIsAttackable());

    return boardPiece.findPossibleMoves(board);
  }

  private Iterable<MoveCoordinates> findAttackCoordinates(Color attackerColor) {
    BoardDirection direction = BoardPositioning.findPawnDirection(piece.getColor());

    return Arrays.asList(
      new MoveCoordinates(BoardDirection.LEFT, 1, direction, 1),
      new MoveCoordinates(BoardDirection.RIGHT, 1, direction, 1));
  }

  private Moves findDirectionMoves(Board board) {
    Iterable<MoveDirection> directions = findAttackMoveDirections();
    int numAdvance = findNumSquaresCanAdvance();

    BoardPiece boardPiece = 
      new BoardPieceDirectionBased(piece, position, directions, numAdvance, new CheckSquareIsOccupiable());

    return boardPiece.findPossibleMoves(board);
  }

  private Iterable<MoveDirection> findAttackMoveDirections() {
    BoardDirection direction = BoardPositioning.findPawnDirection(piece.getColor());
    MoveDirection moveDirection = new MoveDirection(direction, BoardDirection.NONE);
    return Arrays.asList(moveDirection);
  }

  private int findNumSquaresCanAdvance() {
    int pawnStartRow = 
      piece.getColor() == Color.WHITE ? BoardPositioning.PAWN_WHITE_START_ROW : BoardPositioning.PAWN_BLACK_START_ROW;
    return BoardPositioning.findRow(position) == pawnStartRow ? 2 : 1;
  }

}
