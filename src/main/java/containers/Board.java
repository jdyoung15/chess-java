package containers;

import util.Positioning;

import java.util.*;

/**
 * Represents an ordered structuring of positions among which pieces are moved.
 */
public class Board {

  private List<Optional<Piece>> squares;

  public Board() {
    initialize();
  }

  public Board(List<Optional<Piece>> squares) {
    this.squares = squares;
  }

  /**
   * Creates the initial state for this board.
   */
  private void initialize() {
    squares = new ArrayList<>();

    List<Integer> allPositions = Positioning.getAllPositions();
    allPositions.forEach(position -> squares.add(Optional.empty()));

    Map<Integer, Piece> start = Positioning.getPieceStartPositions();
    for (int position : allPositions) {
      squares.set(position, Optional.ofNullable(start.get(position)));
    }
  }

  /**
   * Updates the board's state with the given move.
   */
  public void executeMove(Move move) {
    int from = move.getFromPosition();
    int to = move.getToPosition();

    Optional<Piece> fromPiece = squares.get(from);
    squares.set(from, Optional.empty());

    if (Positioning.isValidPosition(to)) {
      squares.set(to, fromPiece);
    }

    for (Move associatedMove : move.getAssociatedMoves()) {
      executeMove(associatedMove);
    }
  }

  /**
   * Returns a board that is a copy of the current board. Any changes to the copy
   * will not affect the current board.
   */
  public Board copy() {
    return new Board(new ArrayList<>(squares));
  }

  /**
   * Returns a board with all positions empty. Useful for testing.
   */
  public static Board empty() {
    Board board = new Board();
    board.clear();
    return board;
  }

  /**
   * Clear pieces from all postions.
   */
  private void clear() {
    for (int position : Positioning.getAllPositions()) {
      setEmpty(position);
    }
  }

  /**
   * Returns the current position of the king belonging to the given color.
   */
  public int findKingCurrentPosition(Color color) {
    return Positioning.getAllPositions()
      .stream()
      .filter(position -> squares.get(position).isPresent())
      .filter(position -> {
        Piece piece = squares.get(position).orElseThrow();
        return piece.getPieceType() == Piece.Type.KING && piece.getColor() == color;
      })
      .findFirst()
      .orElseThrow();
  }

  /**
   * Sets the given piece at the given position.
   */
  public void setPiece(int position, Piece piece) {
    squares.set(position, Optional.ofNullable(piece));
  }

  /**
   * Sets the given piece at the given position, represented as a user-friendly string input.
   */
  public void setPiece(String position, Piece piece) {
    setPiece(Positioning.toPosition(position), piece);
  }

  /**
   * Sets a piece with the given color and type at each of the given positions,
   * represented as user-friendly string inputs.
   */
  public void setPiece(Set<String> positions, Piece piece) {
    positions.forEach(s -> setPiece(s, piece));
  }

  /**
   * Sets the given position to empty.
   */
  private void setEmpty(int position) {
    squares.set(position, Optional.empty());
  }

  /**
   * Returns whether the given position is occupied by a piece, regardless of color.
   */
  public boolean occupiedAt(int position) {
    return squares.get(position).isPresent();
  }

  /**
   * Returns whether the given player has a piece at the given position.
   */
  public boolean playerAt(int position, Color player) {
    return occupiedAt(position) && getPieceAt(position).getColor() == player;
  }

  /**
   * Returns whether the given player's opponent has a piece at the given position.
   */
  public boolean opponentAt(int position, Color player) {
    return playerAt(position, Color.complementOf(player));
  }

  /**
   * Returns the piece at the given position. If no piece exists,
   * throws {@code NoSuchElementException}.
   */
  public Piece getPieceAt(int position) {
    return squares.get(position).orElseThrow(() ->
      new IllegalArgumentException("No piece at: " + position));
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    List<List<Integer>> groupings = Positioning.getPositionGroupings();
    for (int i = 0; i < groupings.size(); i++) {
      sb.append(String.format("%c  ", Positioning.getGroupingLabel(i)));
      for (int position : groupings.get(i)) {
        sb.append(squares.get(position).map(Piece::getDisplaySymbol).orElse("__"));
        sb.append(" ");
      }
      sb.append("\n");

    }

    sb.append("\n   ");
    List<Integer> representativeGrouping = groupings.get(0);
    for (int i = 0; i < representativeGrouping.size(); i++) {
      sb.append(String.format("%c  ", Positioning.getGroupingItemLabel(i)));
    }

    sb.append("\n\n");
    return sb.toString();
  }

}
