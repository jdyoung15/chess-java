package impl;

import containers.Board;
import containers.Color;
import containers.Move;
import containers.Piece;
import core.PlayerLegalMoveFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Positioning;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerLegalMoveFinderTest {

  private final PlayerLegalMoveFinder moveFinder = new PlayerLegalMoveFinderImpl();

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
    board.setPiece("A1", new Piece(Color.WHITE, Piece.Type.KING));
    board.setPiece("B3", new Piece(Color.BLACK, Piece.Type.QUEEN));

    List<Move> moves = moveFinder.findLegalMoves(board, currentPlayer, previousMoves);
    assertEquals(Set.of(), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testCheckmateStandard() {
    board.setPiece("A1", new Piece(Color.WHITE, Piece.Type.KING));
    board.setPiece("H1", new Piece(Color.BLACK, Piece.Type.QUEEN));
    board.setPiece("H2", new Piece(Color.BLACK, Piece.Type.ROOK));

    List<Move> moves = moveFinder.findLegalMoves(board, currentPlayer, previousMoves);
    assertEquals(Set.of(), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testCheckmateAttackingPieceGuarded() {
    board.setPiece("A1", new Piece(Color.WHITE, Piece.Type.KING));
    board.setPiece("B2", new Piece(Color.BLACK, Piece.Type.QUEEN));
    board.setPiece("C3", new Piece(Color.BLACK, Piece.Type.KING));

    List<Move> moves = moveFinder.findLegalMoves(board, currentPlayer, previousMoves);
    assertEquals(Set.of(), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testCheckOnlyOnePossibleMove() {
    board.setPiece("A1", new Piece(Color.WHITE, Piece.Type.KING));
    board.setPiece("H1", new Piece(Color.WHITE, Piece.Type.KNIGHT));
    board.setPiece("A3", new Piece(Color.BLACK, Piece.Type.QUEEN));

    List<Move> moves = moveFinder.findLegalMoves(board, currentPlayer, previousMoves);
    assertEquals(Set.of("B1"), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testCheckKingMustCaptureAttackingPiece() {
    board.setPiece("A1", new Piece(Color.WHITE, Piece.Type.KING));
    board.setPiece("A2", new Piece(Color.BLACK, Piece.Type.QUEEN));

    List<Move> moves = moveFinder.findLegalMoves(board, currentPlayer, previousMoves);
    assertEquals(Set.of("A2"), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testCheckPieceMustCaptureCheckingPiece() {
    board.setPiece("A1", new Piece(Color.WHITE, Piece.Type.KING));
    board.setPiece("B2", new Piece(Color.BLACK, Piece.Type.QUEEN));
    board.setPiece("C3", new Piece(Color.BLACK, Piece.Type.KING));
    board.setPiece("H2", new Piece(Color.WHITE, Piece.Type.ROOK));

    List<Move> moves = moveFinder.findLegalMoves(board, currentPlayer, previousMoves);
    assertEquals(Set.of("B2"), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testCheckPieceMustBlockCheckingPiece() {
    board.setPiece("A1", new Piece(Color.WHITE, Piece.Type.KING));
    board.setPiece("A2", new Piece(Color.WHITE, Piece.Type.BISHOP));
    board.setPiece("B1", new Piece(Color.WHITE, Piece.Type.QUEEN));
    board.setPiece("C3", new Piece(Color.BLACK, Piece.Type.QUEEN));

    List<Move> moves = moveFinder.findLegalMoves(board, currentPlayer, previousMoves);
    assertEquals(Set.of("B2"), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testPieceCannotExposeKingToCheck() {
    board.setPiece("A1", new Piece(Color.WHITE, Piece.Type.KING));
    board.setPiece("B2", new Piece(Color.WHITE, Piece.Type.ROOK));
    board.setPiece("C3", new Piece(Color.BLACK, Piece.Type.QUEEN));

    List<Move> moves = moveFinder.findLegalMoves(board, currentPlayer, previousMoves);
    assertEquals(Set.of("A2", "B1"), Positioning.toDisplayStrings(moves));
  }

}
