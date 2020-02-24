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

public class RookPositionMoveFinderTest {

  private static final Piece.Type PIECE_TYPE = Piece.Type.ROOK;

  private final PositionMoveFinder moveFinder = PieceTypePositionMoveFinderFactory.getMoveFinder(PIECE_TYPE);

  private Game game;

  @BeforeEach
  public void beforeEach() {
    game = new Game();
  }

  @Test
  public void testStandardMoves() {
    int from = Positioning.toPosition("H1");
    List<Move> moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of(), Positioning.toDisplayStrings(moves));

    game.executeTurn("H2", "H4");
    game.executeTurn("A7", "A6");

    from = Positioning.toPosition("H1");
    moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("H2", "H3"), Positioning.toDisplayStrings(moves));

    game.executeTurn("H1", "H3");
    game.executeTurn("A6", "A5");

    from = Positioning.toPosition("H3");
    moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("H1", "H2", "A3", "B3", "C3", "D3", "E3", "F3", "G3"), Positioning.toDisplayStrings(moves));
  }

}
