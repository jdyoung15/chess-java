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

public class BishopPositionMoveFinderTest {

  private static final Piece.Type PIECE_TYPE = Piece.Type.BISHOP;

  private final PositionMoveFinder moveFinder = PieceTypePositionMoveFinderFactory.getMoveFinder(PIECE_TYPE);

  private Game game;

  @BeforeEach
  public void beforeEach() {
    game = new Game();
  }

  @Test
  public void testStandardMoves() {
    int from = Positioning.toPosition("F1");
    List<Move> moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of(), Positioning.toDisplayStrings(moves));

    game.executeTurn("E2", "E4");
    game.executeTurn("A7", "A6");

    from = Positioning.toPosition("F1");
    moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("E2", "D3", "C4", "B5", "A6"), Positioning.toDisplayStrings(moves));

    game.executeTurn("F1", "C4");
    game.executeTurn("A6", "A5");

    from = Positioning.toPosition("C4");
    moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("B3", "A6", "B5", "D5", "E6", "F7", "D3", "E2", "F1"), Positioning.toDisplayStrings(moves));
  }

}
