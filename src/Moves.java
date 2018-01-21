import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Moves {

  private List<Move> moves;

  public Moves(List<Move> moves) {
    this.moves = moves;
  }

  public Moves() {
    this(new ArrayList<Move>());
  }

  public List<Move> getMoves() {
    return moves;
  }

  public boolean containsFromPosition(int fromPosition) {
    return !moves 
      .stream()
      .filter(m -> m.getFromPosition() == fromPosition)
      .collect(Collectors.toList())
      .isEmpty();
  }

  public boolean containsToPosition(int toPosition) {
    return !moves 
      .stream()
      .filter(m -> m.getToPosition() == toPosition)
      .collect(Collectors.toList())
      .isEmpty();
  }

  public Moves filterLegalMoves(Board board, Color currentPlayer) {
    return new Moves(
      moves 
        .stream()
        .filter(m -> m.isLegal(board, currentPlayer))
        .collect(Collectors.toList()));
  }

  public String toString() {
    Collections.sort(moves, new SortByCoords());
    return moves.toString();
  }

  public boolean isEmpty() {
    return moves.isEmpty();
  }

  public boolean contains(Move move) {
    return moves.contains(move);
  }

  public void add(Move move) {
    moves.add(move);
  }

  public void addAll(Moves newMoves) {
    moves.addAll(newMoves.getMoves());
  }

  public void clear() {
    moves.clear();
  }

  public int size() {
    return moves.size();
  }

  public Move get(int index) {
    return moves.get(index);
  }

}
