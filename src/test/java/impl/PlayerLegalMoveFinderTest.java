package test.java.impl;

import main.java.containers.Board;
import main.java.containers.Color;
import main.java.containers.Move;
import main.java.containers.Piece;
import main.java.core.PlayerLegalMoveFinder;
import main.java.helpers.CheckLogic;
import main.java.impl.PlayerLegalMoveFinderImpl;
import main.java.util.Positioning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerLegalMoveFinderTest {

  private final PlayerLegalMoveFinder playerMoveFinder = new PlayerLegalMoveFinderImpl();
  private final CheckLogic checkLogic = new CheckLogic();

  private Board board;
  private Color currentPlayer;
  private List<Move> previousMoves;

  @BeforeEach
  public void beforeEach() {
    board = Board.empty();
    currentPlayer = Color.WHITE;
    previousMoves = new ArrayList<>();
  }

  @Test
  public void testStalemate() {
    board.setPiece(Positioning.toPosition("A1"), new Piece(Color.WHITE, Piece.Type.KING));
    board.setPiece(Positioning.toPosition("B3"), new Piece(Color.BLACK, Piece.Type.QUEEN));

    List<Move> moves = playerMoveFinder.findLegalMoves(board, currentPlayer, previousMoves);

    assertTrue(moves.isEmpty());
  }

  @Test
  public void testCheckmateStraightforward() {
    board.setPiece(Positioning.toPosition("A1"), new Piece(Color.WHITE, Piece.Type.KING));
    board.setPiece(Positioning.toPosition("H1"), new Piece(Color.BLACK, Piece.Type.QUEEN));
    board.setPiece(Positioning.toPosition("H2"), new Piece(Color.BLACK, Piece.Type.ROOK));

    List<Move> moves = playerMoveFinder.findLegalMoves(board, currentPlayer, previousMoves);

    assertTrue(moves.isEmpty());
  }

  @Test
  public void testCheckmateAttackingPieceGuarded() {
    board.setPiece(Positioning.toPosition("A1"), new Piece(Color.WHITE, Piece.Type.KING));
    board.setPiece(Positioning.toPosition("B2"), new Piece(Color.BLACK, Piece.Type.QUEEN));
    board.setPiece(Positioning.toPosition("C3"), new Piece(Color.BLACK, Piece.Type.KING));

    List<Move> moves = playerMoveFinder.findLegalMoves(board, currentPlayer, previousMoves);

    assertTrue(moves.isEmpty());
  }

  @Test
  public void testCheckOnlyOnePossibleMove() {
    board.setPiece(Positioning.toPosition("A1"), new Piece(Color.WHITE, Piece.Type.KING));
    board.setPiece(Positioning.toPosition("A3"), new Piece(Color.BLACK, Piece.Type.QUEEN));

    List<Move> moves = playerMoveFinder.findLegalMoves(board, currentPlayer, previousMoves);

    assertEquals(1, moves.size());
    assertEquals("B1", Positioning.toDisplayString(moves.get(0).getToPosition()));
  }

  @Test
  public void testCheckMustCaptureAttackingPiece() {
    board.setPiece(Positioning.toPosition("A1"), new Piece(Color.WHITE, Piece.Type.KING));
    board.setPiece(Positioning.toPosition("A2"), new Piece(Color.BLACK, Piece.Type.QUEEN));

    List<Move> moves = playerMoveFinder.findLegalMoves(board, currentPlayer, previousMoves);

    assertEquals(1, moves.size());
    assertEquals("A2", Positioning.toDisplayString(moves.get(0).getToPosition()));
  }

  @Test
  public void testCheckMustBlockAttackingPiece() {
    board.setPiece(Positioning.toPosition("A1"), new Piece(Color.WHITE, Piece.Type.KING));
    board.setPiece(Positioning.toPosition("A2"), new Piece(Color.WHITE, Piece.Type.BISHOP));
    board.setPiece(Positioning.toPosition("B1"), new Piece(Color.WHITE, Piece.Type.QUEEN));
    board.setPiece(Positioning.toPosition("C3"), new Piece(Color.BLACK, Piece.Type.QUEEN));

    List<Move> moves = playerMoveFinder.findLegalMoves(board, currentPlayer, previousMoves);

    assertEquals(1, moves.size());
    assertEquals("B1", Positioning.toDisplayString(moves.get(0).getFromPosition()));
    assertEquals("B2", Positioning.toDisplayString(moves.get(0).getToPosition()));
  }
}
