package containers;

import util.Positioning;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a piece's move from one position on the board to another.
 */
public class Move {

  public static int OFF_POSITION = -1;

  private int fromPosition;
  private int toPosition;

  // For "special" moves like castling, en passant, etc which involve moving multiple pieces.
  private List<Move> associatedMoves;
  // Any move involving the king should include any intermediate squares traveled (only seen
  // in special cases like castling) since the king cannot be exposed to check at any point.
  private List<Integer> kingPositionsTraveled;

  public Move(int fromPosition, int toPosition) {
    this(fromPosition, toPosition, new ArrayList<>(), new ArrayList<>());
  }

  public Move(int fromPosition, int toPosition, List<Move> associatedMoves) {
    this(fromPosition, toPosition, associatedMoves, new ArrayList<>());
  }

  public Move(
    int fromPosition,
    int toPosition,
    List<Move> associatedMoves,
    List<Integer> kingPositionsTraveled)
  {
    this.fromPosition = fromPosition;
    this.toPosition = toPosition;
    this.associatedMoves = associatedMoves;
    this.kingPositionsTraveled = kingPositionsTraveled;
  }

  public int getFromPosition() {
    return fromPosition;
  }

  public int getToPosition() {
    return toPosition;
  }

  public List<Move> getAssociatedMoves() {
    return associatedMoves;
  }

  public List<Integer> getKingPositionsTraveled() {
    return kingPositionsTraveled;
  }

  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
 
    if (!(o instanceof Move)) {
      return false;
    }
     
    Move m = (Move) o;
     
    return fromPosition == m.getFromPosition() && toPosition == m.getToPosition();
  }

  public String toString() {
    return Positioning.toDisplayString(fromPosition) + "->" + Positioning.toDisplayString(toPosition);
  }

}
