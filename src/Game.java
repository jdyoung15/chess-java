import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Game {
  
  private Board board;
  private Color currentPlayer;
  private Moves previousMoves;
  private Moves legalMovesForCurrentPlayer;

  private static final Pattern COORDS_PATTERN = Pattern.compile("[A-H][1-8]");
  private static final String MESSAGE_CHECKMATE = "Checkmate! %s wins.";
  private static final String MESSAGE_STALEMATE = "Stalemate.";
  private static final String MESSAGE_START_TURN = "Turn: %s";
  private static final String MESSAGE_SELECT_SQUARE_FROM = "Select square to move from:";
  private static final String MESSAGE_SELECT_SQUARE_TO = "Select square to move to (moving from square %s):";
  private static final String MESSAGE_ERROR_INVALID_ENTRY = "\nINVALID ENTRY, TRY AGAIN. FORMAT: [A-H][1-8] eg B5\n";
  private static final String MESSAGE_ERROR_NO_LEGAL_MOVES = "\nNO LEGAL MOVES FROM THIS POSITION, TRY AGAIN\n";
  private static final String MESSAGE_ERROR_ILLEGAL_MOVE = "\nILLEGAL MOVE, TRY AGAIN\n";

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
      System.out.println(String.format(MESSAGE_CHECKMATE, victor.name().toLowerCase()));
    }
    else {
      System.out.println(MESSAGE_STALEMATE);
    }
  }

  private void executeTurn() {
    System.out.println(board.toString());
    Scanner scanner = new Scanner(System.in);     

    while (true) {
      System.out.println(String.format(MESSAGE_START_TURN, currentPlayer.name().toLowerCase()));

      System.out.println(MESSAGE_SELECT_SQUARE_FROM);

      String fromCoords = scanner.next().toUpperCase();
      if (!isValidCoords(fromCoords)) {
        System.out.println(MESSAGE_ERROR_INVALID_ENTRY);
        continue;
      }

      int fromPosition = BoardPositioning.findPosition(fromCoords);

      if (!legalMovesForCurrentPlayer.containsMovesFromPosition(fromPosition)) {
        System.out.println(MESSAGE_ERROR_NO_LEGAL_MOVES);
        continue;
      }

      System.out.println(String.format(MESSAGE_SELECT_SQUARE_TO, fromCoords));

      String toCoords = scanner.next().toUpperCase();
      if (!isValidCoords(toCoords)) {
        System.out.println(MESSAGE_ERROR_INVALID_ENTRY);
        continue;
      }

      int toPosition = BoardPositioning.findPosition(toCoords);
       
      Move move = new Move(fromPosition, toPosition);

      if (!legalMovesForCurrentPlayer.contains(move)) {
        System.out.println(MESSAGE_ERROR_ILLEGAL_MOVE);
        continue;
      }

      handleIfEnPassantMove(move);
      handleIfCastlingMove(move);

      board.executeMove(move);
      previousMoves.add(move);

      break;
    }
  }

  private boolean isValidCoords(String coords) {
    Matcher m = COORDS_PATTERN.matcher(coords);
    return m.matches();
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
  }

}
