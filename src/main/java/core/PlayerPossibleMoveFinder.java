package core;

import containers.Board;
import containers.Color;
import containers.Move;

import java.util.List;

/**
 * Interface defining behavior for finding a given player's possible moves.
 */
public interface PlayerPossibleMoveFinder {

  /**
   * Returns all possible moves by the current player as of the current state of the given
   * board. Possible moves are defined as satisfying all rules *except* the rule that
   * prohibits the current player from exposing their king to check. A player cannot
   * necessarily employ any given possible move in actual gameplay.
   */
  List<Move> findPossibleMoves(Board board, Color currentPlayer, List<Move> previousMoves);

}
