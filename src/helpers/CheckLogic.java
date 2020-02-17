package helpers;

import containers.Board;
import containers.Color;
import containers.Move;
import core.PlayerPossibleMoveFinder;
import impl.PlayerPossibleMoveFinderImpl;

import java.util.List;

/**
 * Responsible for logic determining when a player is "checked".
 */
public class CheckLogic {

  private PlayerPossibleMoveFinder playerPossibleMoveFinder;

  public CheckLogic() {
    this.playerPossibleMoveFinder = new PlayerPossibleMoveFinderImpl();
  }

  /**
   * Returns whether the player represented by the given color is currently checked.
   */
  public boolean isChecked(Board board, Color color, List<Move> previousMoves) {
    Color opponent = Color.complementOf(color);
    List<Move> opponentMoves = playerPossibleMoveFinder.findPossibleMoves(board, opponent, previousMoves);
    int kingPosition = board.findKingCurrentPosition(color);
    return opponentMoves.stream().anyMatch(m -> m.getToPosition() == kingPosition);
  }

}
