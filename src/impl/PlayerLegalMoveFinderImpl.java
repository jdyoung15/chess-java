package impl;

import containers.Board;
import containers.Color;
import containers.Move;
import core.PlayerLegalMoveFinder;
import core.PlayerPossibleMoveFinder;
import helpers.CheckLogic;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link PlayerLegalMoveFinder}.
 */
public class PlayerLegalMoveFinderImpl implements PlayerLegalMoveFinder {

  private CheckLogic checkLogic;
  private PlayerPossibleMoveFinder playerPossibleMoveFinder;

  public PlayerLegalMoveFinderImpl() {
    checkLogic = new CheckLogic();
    playerPossibleMoveFinder = new PlayerPossibleMoveFinderImpl();
  }

  @Override
  public List<Move> findLegalMoves(Board board, Color currentPlayer, List<Move> previousMoves) {
    List<Move> possibleMoves =
      playerPossibleMoveFinder.findPossibleMoves(board, currentPlayer, previousMoves);
    List<Move> legalMoves = new ArrayList<>();

    for (Move move : possibleMoves) {
      if (isCheckedDuringKingMove(board, currentPlayer, previousMoves, move)) {
        continue;
      }

      if (isCheckedAfterMove(board, currentPlayer, previousMoves, move)) {
        continue;
      }

      legalMoves.add(move);
    }

    return legalMoves;
  }

/**
 * Returns whether the given player is checked at any intermediate position
 * during a king's move. Can only return true during castling, when a king
 * moves more than one position.
 */
  private boolean isCheckedDuringKingMove(
    Board board, Color player, List<Move> previousMoves, Move move)
  {
    System.out.println(move);
    int from = move.getFromPosition();
    if (move.getKingPositionsTraveled() == null) {
      System.out.println("null king positions traveled");
    }
    return move.getKingPositionsTraveled()
      .stream()
      .anyMatch(to -> isCheckedAfterMove(board, player, previousMoves, new Move(from, to)));
  }

  /**
   * Returns whether the given player is checked after the given move has been executed.
   */
  private boolean isCheckedAfterMove(
    Board board, Color player, List<Move> previousMoves, Move move)
  {
    Board boardCopy = board.copy();
    boardCopy.executeMove(move);
    List<Move> previousMovesCopy = new ArrayList<>(previousMoves);
    previousMovesCopy.add(move);
    return checkLogic.isChecked(boardCopy, player, previousMovesCopy);
  }

}
