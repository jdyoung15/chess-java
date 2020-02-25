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

public class CastlingPositionMoveFinderTest {

  private static final Piece.Type PIECE_TYPE = Piece.Type.KING;
  private static final int C1 = Positioning.toPosition("C1");
  private static final int D1 = Positioning.toPosition("D1");
  private static final int E1 = Positioning.toPosition("E1");
  private static final int F1 = Positioning.toPosition("F1");
  private static final int G1 = Positioning.toPosition("G1");

  private final PositionMoveFinder moveFinder = new CastlingPositionMoveFinderImpl();

  private Board board;
  private int fromPosition;
  private List<Move> previousMoves;

  @BeforeEach
  public void beforeEach() {
    board = Board.empty();
    fromPosition = Positioning.toPosition("E1");
    board.setPiece(fromPosition, new Piece(Color.WHITE, PIECE_TYPE));
    board.setPiece("H1", new Piece(Color.WHITE, Piece.Type.ROOK));
    board.setPiece("A1", new Piece(Color.WHITE, Piece.Type.ROOK));
    previousMoves = new ArrayList<>();
  }

  @Test
  public void testCastlingValid() {
    List<Move> moves = moveFinder.findMoves(board, fromPosition, previousMoves);
    assertEquals(Set.of("C1", "G1"), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testCastlingMoveRight() {
    List<Move> moves = moveFinder.findMoves(board, fromPosition, previousMoves);
    Move move = moves.stream().filter(m -> m.toString().equals("E1->G1")).findFirst().orElseThrow();
    board.executeMove(move);
    assertEquals(board.getPieceAt(G1).getPieceType(), Piece.Type.KING);
    assertEquals(board.getPieceAt(F1).getPieceType(), Piece.Type.ROOK);
  }

  @Test
  public void testCastlingMoveLeft() {
    List<Move> moves = moveFinder.findMoves(board, fromPosition, previousMoves);
    Move move = moves.stream().filter(m -> m.toString().equals("E1->C1")).findFirst().orElseThrow();
    board.executeMove(move);
    assertEquals(board.getPieceAt(C1).getPieceType(), Piece.Type.KING);
    assertEquals(board.getPieceAt(D1).getPieceType(), Piece.Type.ROOK);
  }

  @Test
  public void testCastlingInvalidDueToPieceBetween() {
    board.setPiece("B1", new Piece(Color.WHITE, Piece.Type.KNIGHT));
    board.setPiece("F1", new Piece(Color.WHITE, Piece.Type.BISHOP));

    List<Move> moves = moveFinder.findMoves(board, fromPosition, previousMoves);
    assertEquals(Set.of(), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testCastlingInvalidDueToPriorKingMove() {
    int toPosition = Positioning.toPosition("E2");
    previousMoves.add(new Move(fromPosition, toPosition));
    previousMoves.add(new Move(toPosition, fromPosition));

    List<Move> moves = moveFinder.findMoves(board, fromPosition, previousMoves);
    assertEquals(Set.of(), Positioning.toDisplayStrings(moves));
  }

  @Test
  public void testCastlingInvalidDueToPriorRookMove() {
    int rookFromPosition = Positioning.toPosition("H1");
    int rookToPosition = Positioning.toPosition("H8");
    previousMoves.add(new Move(rookFromPosition, rookToPosition));
    previousMoves.add(new Move(rookToPosition, rookFromPosition));

    List<Move> moves = moveFinder.findMoves(board, fromPosition, previousMoves);
    assertEquals(Set.of("C1"), Positioning.toDisplayStrings(moves));
  }

}
