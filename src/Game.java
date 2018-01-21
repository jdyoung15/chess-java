import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Game {
  
  private Board board;
  private Color currentPlayer;
  private Moves previousMoves;
  private Moves legalMovesForCurrentPlayer;

  public Game() {
    board = new Board();
    currentPlayer = Color.WHITE;
    previousMoves = new Moves();
    legalMovesForCurrentPlayer = new Moves();
  }

  public void play() {
    populateLegalMovesForCurrentPlayer();
    while (!legalMovesForCurrentPlayer.isEmpty()) {
      executeTurn();
      currentPlayer = Color.findOpponent(currentPlayer);
      populateLegalMovesForCurrentPlayer();
    }

    // reached here because no legal moves for current player
    if (board.isChecked(currentPlayer)) {
      Color victor = Color.findOpponent(currentPlayer);
      System.out.println(String.format("Checkmate! %s wins.", victor.name().toLowerCase()));
    }
    else {
      System.out.println("Stalemate.");
    }
  }

  private void executeTurn() {
    System.out.println(board.toString());
    Scanner scanner = new Scanner(System.in);     

    while (true) {
      System.out.println(String.format("Turn: %s", currentPlayer.name().toLowerCase()));

      System.out.println("Select square to move from: ");
      String fromCoords = scanner.next();
      int fromPosition = BoardPositioning.findPosition(fromCoords);

      if (!legalMovesForCurrentPlayer.containsMovesFromPosition(fromPosition)) {
        System.out.println("\nNO LEGAL MOVES FROM THIS POSITION, TRY AGAIN\n");
        continue;
      }

      System.out.println(String.format("Select square to move to (moving from square %s): ", fromCoords));

      String toCoords = scanner.next();
      int toPosition = BoardPositioning.findPosition(toCoords);
       
      Move move = new Move(fromPosition, toPosition);

      if (!legalMovesForCurrentPlayer.contains(move)) {
        System.out.println("\nILLEGAL MOVE, TRY AGAIN\n");
        continue;
      }

      handleIfEnPassantMove(move);
      handleIfCastlingMove(move);

      board.executeMove(move);
      previousMoves.add(move);

      break;
    }
  }

  private void handleIfEnPassantMove(Move move) {
    int attackPawnFromPosition = move.getFromPosition();
    for (EnPassantDirection epd : EnPassantDirection.values()) {
      if (epd.canEnPassant(attackPawnFromPosition, currentPlayer, board, previousMoves)
        && epd.findAttackingPawnMove(attackPawnFromPosition, currentPlayer).equals(move))
      {
        int victimPawnPosition = epd.findVictimPawnToPosition(attackPawnFromPosition, currentPlayer);
        board.findSquare(victimPawnPosition).clear();
      }
    }
  }

  private void handleIfCastlingMove(Move move) {
    int kingFromPosition = move.getFromPosition();
    for (CastlingSide cs : CastlingSide.values()) {
      if (cs.canCastle(kingFromPosition, currentPlayer, board, previousMoves)
        && cs.findKingMove(kingFromPosition).equals(move))
      {
        board.executeMove(cs.findRookMove(kingFromPosition));
      }
    }
  }

  private void populateLegalMovesForCurrentPlayer() {
    legalMovesForCurrentPlayer.clear();

    Iterator<Integer> positionsIterator = BoardPositioning.positionsIterator();
    while (positionsIterator.hasNext()) {
      int fromPosition = positionsIterator.next();
      Square fromSquare = board.findSquare(fromPosition);
      if (!fromSquare.containsCurrentPlayer(currentPlayer)) {
        continue;
      }

      Piece piece = fromSquare.getPiece();
      BoardPiece boardPiece = BoardPieceFactory.getBoardPiece(piece, fromPosition);

      Moves legalMoves = boardPiece.findMoves(board, previousMoves);
      legalMovesForCurrentPlayer.addAll(legalMoves);
    }

    System.out.println(String.format("legal moves: %s", legalMovesForCurrentPlayer));
  }

}
