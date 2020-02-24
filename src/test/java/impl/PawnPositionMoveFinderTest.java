package test.java.impl;

import main.java.containers.Board;
import main.java.containers.Color;
import main.java.containers.Move;
import main.java.containers.Piece;
import main.java.core.PositionMoveFinder;
import main.java.impl.PieceTypePositionMoveFinderFactory;
import main.java.util.Positioning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnPositionMoveFinderTest {

  private static final Piece.Type PIECE_TYPE = Piece.Type.PAWN;
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
  public void testStartRow() {
    int fromPosition = Positioning.toPosition("E2");
    board.setPiece(fromPosition, new Piece(Color.WHITE, Piece.Type.PAWN));
    List<Move> moves = moveFinder.findMoves(board, fromPosition, previousMoves);
    assertEquals(Set.of("E3", "E4"), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testNonStartRow() {
    List<Move> moves = moveFinder.findMoves(board, fromPosition, previousMoves);
    assertEquals(Set.of("D5"), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testAttack() {
    board.setPiece(SURROUNDING_POSITIONS, new Piece(Color.BLACK, Piece.Type.PAWN));
    List<Move> moves = moveFinder.findMoves(board, fromPosition, previousMoves);
    assertEquals(Set.of("C5", "E5"), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testBlocked() {
    board.setPiece("D5", new Piece(Color.BLACK, Piece.Type.PAWN));
    List<Move> moves = moveFinder.findMoves(board, fromPosition, previousMoves);
    assertEquals(Set.of(), Positioning.toDisplayStrings(moves));
  }

}
