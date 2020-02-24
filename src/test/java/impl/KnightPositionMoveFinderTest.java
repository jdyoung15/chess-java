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

public class KnightPositionMoveFinderTest {

  private static final Piece.Type PIECE_TYPE = Piece.Type.KNIGHT;

  private final PositionMoveFinder moveFinder = PieceTypePositionMoveFinderFactory.getMoveFinder(PIECE_TYPE);

  private Game game;

  @BeforeEach
  public void beforeEach() {
    game = new Game();
  }

  @Test
  public void testStandardMoves() {
    int from = Positioning.toPosition("G1");
    List<Move> moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("F3", "H3"), Positioning.toDisplayStrings(moves));

    game.executeTurn("G1", "F3");
    game.executeTurn("E7", "E5");

    from = Positioning.toPosition("F3");
    moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("D4", "E5", "G5", "H4", "G1"), Positioning.toDisplayStrings(moves));

    game.executeTurn("F3", "E5");
    game.executeTurn("A7", "A6");

    from = Positioning.toPosition("E5");
    moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("D3", "C4", "C6", "D7", "F7", "G6", "G4", "F3"), Positioning.toDisplayStrings(moves));
  }

}
