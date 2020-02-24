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

public class QueenPositionMoveFinderTest {

  private static final Piece.Type PIECE_TYPE = Piece.Type.QUEEN;

  private final PositionMoveFinder moveFinder = PieceTypePositionMoveFinderFactory.getMoveFinder(PIECE_TYPE);

  private Game game;

  @BeforeEach
  public void beforeEach() {
    game = new Game();
  }

  @Test
  public void testStandardMoves() {
    int from = Positioning.toPosition("D1");
    List<Move> moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of(), Positioning.toDisplayStrings(moves));

    game.executeTurn("E2", "E3");
    game.executeTurn("A7", "A6");

    from = Positioning.toPosition("D1");
    moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("E2", "F3", "G4", "H5"), Positioning.toDisplayStrings(moves));

    game.executeTurn("D1", "G4");
    game.executeTurn("A6", "A5");

    from = Positioning.toPosition("G4");
    moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(
      Set.of("D1", "E2", "F3", "A4", "B4", "C4", "D4", "E4", "F4", "D7", "E6", "F5",
        "G7", "G6", "G5", "H5", "H4", "H3", "G3"),
      Positioning.toDisplayStrings(moves));
  }

}
