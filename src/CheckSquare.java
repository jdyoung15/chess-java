/**
 * Represents a piece's ability to move to a square, 
 * ie which conditions must be fulfilled for a piece to move there.
 */
public interface CheckSquare {

  public boolean test(Square square, Color currentPlayer);

}
