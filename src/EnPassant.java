import java.util.List;
import java.util.ArrayList;

public class EnPassant {

  private int fromPosition;
  private Board board;
  private Color currentPlayer;
  private List<Move> previousMoves;

  public EnPassant(int fromPosition, Board board, Color currentPlayer, List<Move> previousMoves) {
    this.fromPosition = fromPosition;
    this.board = board;
    this.currentPlayer = currentPlayer;
    this.previousMoves = previousMoves;
  }

  public List<Integer> positions() {
    List<Integer> positions = new ArrayList<Integer>();
    if (previousMoves.size() == 0) {
      return positions;
    }

    List<Integer> adjacentPositions = new ArrayList<Integer>();
    if (Board.inBounds(fromPosition, -1, 0)) {
      adjacentPositions.add(Board.calculateNewPosition(fromPosition, -1, 0));
    }
    if (Board.inBounds(fromPosition, 1, 0)) {
      adjacentPositions.add(Board.calculateNewPosition(fromPosition, 1, 0));
    }

    Direction attackPawnDirection = Board.findDirection(currentPlayer);
    int directionMultiplier = attackPawnDirection == Direction.UP ? 1 : -1;


    for (int adjacentPosition : adjacentPositions) {
      Square square = board.findSquare(adjacentPosition);
      if (!square.isOccupied() 
        || square.getPiece().getColor() == currentPlayer
        || square.getPiece().getPieceType() != PieceType.PAWN) 
      {
        continue;
      }

      Move previousMove = previousMoves.get(previousMoves.size() - 1);
      if (previousMove.getToPosition() != adjacentPosition) {
        continue;
      }

      if (!Board.inBounds(adjacentPosition, 0, directionMultiplier * 2)) {
        continue;
      }

      int victimPawnFromPosition = Board.calculateNewPosition(adjacentPosition, 0, directionMultiplier * 2);
      if (previousMove.getFromPosition() != victimPawnFromPosition) {
        continue;
      }
      
      int attackPawnToPosition = Board.calculateNewPosition(adjacentPosition, 0, directionMultiplier * 1);
      positions.add(attackPawnToPosition);
    }

    return positions;

    // find positions of adjacent squares 
    // for adjacent square
    //   if not occupied by opponent pawn
    //     continue
    //   find previous move
    //   if adjacent pawn moved from this position to current position
    //     find position that is one "before" 
    //     add this position
    //   break
    // 
    // return positions
  }

}
