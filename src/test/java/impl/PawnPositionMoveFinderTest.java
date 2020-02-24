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

public class PawnPositionMoveFinderTest {

  private static final Piece.Type PIECE_TYPE = Piece.Type.PAWN;

  private final PositionMoveFinder moveFinder = PieceTypePositionMoveFinderFactory.getMoveFinder(PIECE_TYPE);

  private Game game;

  @BeforeEach
  public void beforeEach() {
    game = new Game();
  }

  @Test
  public void testStandardMoves() {
    int from = Positioning.toPosition("D2");
    List<Move> moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("D3", "D4"), Positioning.toDisplayStrings(moves));

    game.executeTurn("D2", "D4");
    game.executeTurn("D7", "D6");

    from = Positioning.toPosition("D4");
    moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("D5"), Positioning.toDisplayStrings(moves));

    game.executeTurn("D4", "D5");
    game.executeTurn("C7", "C6");

    from = Positioning.toPosition("D5");
    moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("C6"), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testEnPassant() {
    game.executeTurn("D2", "D4");
    game.executeTurn("C7", "C5");
    game.executeTurn("D4", "D5");
    game.executeTurn("E7", "E5");

    int from = Positioning.toPosition("D5");
    List<Move> moves = moveFinder.findMoves(game.getBoard(), from, game.getPreviousMoves());
    assertEquals(Set.of("D6", "E6"), Positioning.toDisplayStrings(moves));
  }

}
