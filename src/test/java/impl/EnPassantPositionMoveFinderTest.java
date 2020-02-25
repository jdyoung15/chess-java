package impl;

import containers.Board;
import containers.Color;
import containers.Move;
import containers.Piece;
import core.PositionMoveFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Positioning;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnPassantPositionMoveFinderTest {

  private static final Piece.Type PIECE_TYPE = Piece.Type.PAWN;
  private static final int E7 = Positioning.toPosition("E7");
  private static final int E6 = Positioning.toPosition("E6");
  private static final int E5 = Positioning.toPosition("E5");
  private static final int E4 = Positioning.toPosition("E4");
  private static final int D5 = Positioning.toPosition("D5");
  private static final int D4 = Positioning.toPosition("D4");
  private static final int D2 = Positioning.toPosition("D2");
  private static final int F7 = Positioning.toPosition("F7");
  private static final int F6 = Positioning.toPosition("F6");

  private final PositionMoveFinder moveFinder = new EnPassantPositionMoveFinderImpl();

  private Board board;
  private int fromPosition;
  private List<Move> previousMoves;

  @BeforeEach
  public void beforeEach() {
    board = Board.empty();
    fromPosition = D5;
    board.setPiece(fromPosition, new Piece(Color.WHITE, PIECE_TYPE));
    previousMoves = new ArrayList<>();
  }

  @Test
  public void testEnPassantValid() {
    board.setPiece(E5, new Piece(Color.BLACK, Piece.Type.PAWN));
    previousMoves.add(new Move(E7, E5));
    List<Move> moves = moveFinder.findMoves(board, fromPosition, previousMoves);
    assertEquals(Set.of("E6"), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testEnPassantInvalidDueToPreviousMoveOnlyOneSquare() {
    board.setPiece(E5, new Piece(Color.BLACK, Piece.Type.PAWN));
    previousMoves.add(new Move(E6, E5));

    List<Move> moves = moveFinder.findMoves(board, fromPosition, previousMoves);
    assertEquals(Set.of(), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testEnPassantInvalidDueToTwoMovesAgo() {
    board.setPiece(E5, new Piece(Color.BLACK, Piece.Type.PAWN));
    previousMoves.add(new Move(E7, E5));
    board.setPiece(D5, new Piece(Color.WHITE, Piece.Type.PAWN));
    previousMoves.add(new Move(D4, D5));
    board.setPiece(F6, new Piece(Color.BLACK, Piece.Type.PAWN));
    previousMoves.add(new Move(F7, F6));

    List<Move> moves = moveFinder.findMoves(board, fromPosition, previousMoves);
    assertEquals(Set.of(), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testEnPassantInvalidDueToWrongRow() {
    board = Board.empty();
    board.setPiece(E5, new Piece(Color.BLACK, Piece.Type.PAWN));
    previousMoves.add(new Move(E7, E5));
    board.setPiece(D4, new Piece(Color.WHITE, Piece.Type.PAWN));
    previousMoves.add(new Move(D2, D4));
    board.setPiece(E4, new Piece(Color.BLACK, Piece.Type.PAWN));
    previousMoves.add(new Move(E5, E4));

    List<Move> moves = moveFinder.findMoves(board, E4, previousMoves);
    assertEquals(Set.of(), Positioning.toDisplayStrings(moves));
  }

}
