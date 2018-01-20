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

  public void addAll(List<Move> newMoves) {
    moves.addAll(newMoves);
  }

  public void clear() {
    moves.clear();
  }

}
