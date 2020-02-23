package main.java.core;

import main.java.containers.Board;
import main.java.containers.Color;
import main.java.containers.Move;

import java.util.List;

/**
 * Interface defining behavior for finding a given player's legal moves.
 */
public interface PlayerLegalMoveFinder {

  /**
   * Returns all legal moves by the current player as of the current state of the given
   * board. Legal moves are defined as satisfying all rules. That is, a player can
   * employ any given legal move in actual gameplay.
   */
  List<Move> findLegalMoves(Board board, Color currentPlayer, List<Move> previousMoves);

}
