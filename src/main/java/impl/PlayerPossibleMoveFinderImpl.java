package main.java.impl;

import main.java.containers.Board;
import main.java.containers.Color;
import main.java.containers.Move;
import main.java.containers.Piece;
import main.java.core.PlayerPossibleMoveFinder;
import main.java.core.PositionMoveFinder;
import main.java.util.Positioning;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link PlayerPossibleMoveFinder}.
 */
public class PlayerPossibleMoveFinderImpl implements PlayerPossibleMoveFinder, PositionMoveFinder {

  @Override
  public List<Move> findPossibleMoves(Board board, Color currentPlayer, List<Move> previousMoves) {
    List<Move> moves = new ArrayList<>();
    for (int position : Positioning.getAllPositions()) {
      if (board.playerAt(position, currentPlayer)) {
        moves.addAll(findMoves(board, position, previousMoves));
      }
    }
    return moves;
  }

  @Override
  public List<Move> findMoves(Board board, int fromPosition, List<Move> previousMoves) {
    Piece piece = board.getPieceAt(fromPosition);
    PositionMoveFinder positionMoveFinder =
      PieceTypePositionMoveFinderFactory.getMoveFinder(piece.getPieceType());
    return positionMoveFinder.findMoves(board, fromPosition, previousMoves);
  }

}
