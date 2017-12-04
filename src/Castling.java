import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Castling {

  private int fromPosition;
  private Board board;
  private Color currentPlayer;
  private List<Move> previousMoves;

  public Castling(int fromPosition, Board board, Color currentPlayer, List<Move> previousMoves) {
    this.fromPosition = fromPosition;
    this.board = board;
    this.currentPlayer = currentPlayer;
    this.previousMoves = previousMoves;
  }

  public List<Integer> positions() {
    List<Integer> positions = new ArrayList<Integer>();
    int kingStartPosition = 60; // TODO

    // check that the king has not moved (and by extension is currently at its start position)
    List<Move> kingMoves = 
      previousMoves
        .stream()
        .filter(m -> m.getFromPosition() == kingStartPosition)
        .collect(Collectors.toList());

    if (!kingMoves.isEmpty()) {
      return positions;
    }

    // chech that king is not currently in check
    if (new Check(currentPlayer, kingStartPosition, board).isCheck()) {
      return positions;
    }

    for (CastlingSide castlingSide : CastlingSide.values()) {

      // check that castling rook has not moved (and by extension is currently at start position)
      int rookStartPosition = Board.calculateNewPosition(kingStartPosition, castlingSide.getRookPosition(), 0);
      List<Move> rookMoves = 
        previousMoves
          .stream()
          .filter(m -> m.getFromPosition() == rookStartPosition)
          .collect(Collectors.toList());
      if (!rookMoves.isEmpty()) {
        continue;
      }

      int directionValue = castlingSide.getDirectionValue();

      boolean skip = false;

      // check that squares between king and rook are unoccupied
      for (int i = 1; i < castlingSide.getNumSquaresBetween() + 1; i++) {
        int currentPosition = Board.calculateNewPosition(kingStartPosition, directionValue * i, 0);
        Square currentSquare = board.findSquare(currentPosition);
        if (currentSquare.isOccupied()) {
          skip = true;
          break;
        }
      }

      if (skip) {
        continue;
      }

      // check that king will not be in check at any square it travels through (including end square)
      for (int i = 1; i < castlingSide.getKingToPosition() + 1; i++) {
        int currentPosition = Board.calculateNewPosition(kingStartPosition, directionValue * i, 0);
        Board copy = board.copy();
        copy.move(kingStartPosition, currentPosition);
        if (new Check(currentPlayer, currentPosition, copy).isCheck()) {
          skip = true;
          break;
        }
      }

      if (skip) {
        continue;
      }

      int kingEndPosition = 
        Board.calculateNewPosition(kingStartPosition, directionValue * castlingSide.getKingToPosition(), 0);
      positions.add(kingEndPosition);

    }

    return positions;

    // find king start position for current player
    // if king has moved or if king is in check
    //   return
    // for direction (left, right)
    //   find rook start position 
    //   if rook has moved
    //     continue
    //   
    //   find between squares
    //   if any are occupied
    //     continue
    // 
    //   find king move squares
    //   if king is in check for any square
    //     continue
    // 
    //   find king end position
    //   add to positions
  }

}
