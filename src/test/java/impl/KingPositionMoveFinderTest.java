package test.java.impl;

import main.java.containers.Move;
import main.java.containers.Piece;
import main.java.core.PositionMoveFinder;
import main.java.impl.PieceTypePositionMoveFinderFactory;
import main.java.ui.Game;
import main.java.util.Positioning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KingPositionMoveFinderTest {

  private static final Piece.Type PIECE_TYPE = Piece.Type.KING;

  private final PositionMoveFinder moveFinder = PieceTypePositionMoveFinderFactory.getMoveFinder(PIECE_TYPE);

  private Game game;

  @BeforeEach
  public void beforeEach() {
    game = new Game();
  }

  @Test
  public void testStandardMoves() {
    int from = Positioning.toPosition("E1");
    List<Move> moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of(), Positioning.toDisplayStrings(moves));

    game.executeTurn("E2", "E4");
    game.executeTurn("A7", "A6");

    from = Positioning.toPosition("E1");
    moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("E2"), Positioning.toDisplayStrings(moves));

    game.executeTurn("E1", "E2");
    game.executeTurn("A6", "A5");

    from = Positioning.toPosition("E2");
    moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("D3", "E3", "F3", "E1"), Positioning.toDisplayStrings(moves));

    game.executeTurn("E2", "D3");
    game.executeTurn("A5", "A4");

    from = Positioning.toPosition("D3");
    moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("C3", "C4", "D4", "E3", "E2"), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testCastling() {
    // Move intermediate pieces out of the way for castling
    game.executeTurn("G2", "G3");
    game.executeTurn("D7", "D5");
    game.executeTurn("F1", "H3");
    game.executeTurn("C8", "E6");
    game.executeTurn("G1", "F3");
    game.executeTurn("D8", "D7");

    // Test that White can castle to the right
    int from = Positioning.toPosition("E1");
    List<Move> moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("F1", "G1"), Positioning.toDisplayStrings(moves));

    // Move White's king one square to the right (did not castle)
    game.executeTurn("E1", "F1");
    game.executeTurn("B8", "A6");
    // Move White's king back to start position
    game.executeTurn("F1", "E1");

    // Test Black can castle to the right
    from = Positioning.toPosition("E8");
    moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("C8", "D8"), Positioning.toDisplayStrings(moves));

    // Move Black's rook
    game.executeTurn("A8", "B8");

    // Test White cannot castle to the right since it moved its king
    from = Positioning.toPosition("E1");
    moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("F1"), Positioning.toDisplayStrings(moves));

    game.executeTurn("A2", "A3");
    // Return Black's rook to its start position
    game.executeTurn("B8", "A8");

    // Test Black cannot castle to the left since it moved its rook
    from = Positioning.toPosition("E8");
    moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("D8"), Positioning.toDisplayStrings(moves));
  }

}
