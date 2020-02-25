package test.java.impl;

import containers.Board;
import containers.Color;
import containers.Move;
import containers.Piece;
import core.PositionMoveFinder;
import impl.PieceTypePositionMoveFinderFactory;
import util.Positioning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RookPositionMoveFinderTest {

  private static final Piece.Type PIECE_TYPE = Piece.Type.ROOK;
  private static final Set<String> SURROUNDING_POSITIONS = Set.of(
    "C3", "C4", "C5", "D5", "E5", "E4", "E3", "D3"
  );

  private final PositionMoveFinder moveFinder = PieceTypePositionMoveFinderFactory.getMoveFinder(PIECE_TYPE);

  private Board board;
  private int fromPosition;
  private List<Move> previousMoves;

  @BeforeEach
  public void beforeEach() {
    board = Board.empty();
    fromPosition = Positioning.toPosition("D4");
    board.setPiece(fromPosition, new Piece(Color.WHITE, PIECE_TYPE));
    previousMoves = new ArrayList<>();
  }

  @Test
  public void testUnrestricted() {
    List<Move> moves = moveFinder.findMoves(board, fromPosition, previousMoves);
    assertEquals(
      Set.of("A4", "B4", "C4", "D8", "D7", "D6", "D5", "E4", "F4", "G4", "H4", "D3", "D2", "D1"),
      Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testAttack() {
    board.setPiece(SURROUNDING_POSITIONS, new Piece(Color.BLACK, Piece.Type.PAWN));
    List<Move> moves = moveFinder.findMoves(board, fromPosition, previousMoves);
    assertEquals(Set.of("C4", "D5", "E4", "D3"), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testBlocked() {
    board.setPiece(SURROUNDING_POSITIONS, new Piece(Color.WHITE, Piece.Type.PAWN));
    List<Move> moves = moveFinder.findMoves(board, fromPosition, previousMoves);
    assertEquals(Set.of(), Positioning.toDisplayStrings(moves));
  }

}
