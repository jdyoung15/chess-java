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
  private List<Move> previousMoves;
  private Moves validMovesForCurrentPlayer;

  public Game() {
    board = new Board();
    currentPlayer = Color.WHITE;
    previousMoves = new ArrayList<Move>();
    validMovesForCurrentPlayer = new Moves();
  }

  public void play() {
    populateValidMovesByPosition();
    while (!validMovesForCurrentPlayer.isEmpty()) {
      executeTurn();
      currentPlayer = Color.findOpponent(currentPlayer);
      populateValidMovesByPosition();
    }

    // reached here because no valid moves for current player
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

      if (!validMovesForCurrentPlayer.containsFromPosition(fromPosition)) {
        System.out.println("\nNO VALID MOVES FROM THIS POSITION, TRY AGAIN\n");
        continue;
      }

      System.out.println(String.format("Select square to move to (moving from square %s): ", fromCoords));
      String toCoords = scanner.next();
      int toPosition = BoardPositioning.findPosition(toCoords);
       
      Move move = new Move(fromPosition, toPosition);
      if (!validMovesForCurrentPlayer.contains(move)) {
        System.out.println("\nINVALID MOVE, TRY AGAIN\n");
        continue;
      }

      handleIfEnPassantMove(move);
      handleIfCastlingMove(move);

      board.move(move);
      previousMoves.add(move);

      break;
    }
  }

  private void handleIfEnPassantMove(Move move) {
    int fromPosition = move.getFromPosition();
    for (EnPassantDirection epd : EnPassantDirection.values()) {
      if (epd.canEnPassant(fromPosition, currentPlayer, board, previousMoves)
        && epd.findAttackingPawnMove(fromPosition, currentPlayer) == move) 
      {
        board.findSquare(epd.findVictimPawnToPosition(fromPosition, currentPlayer)).clear();
      }
    }
  }

  private void handleIfCastlingMove(Move move) {
    int fromPosition = move.getFromPosition();
    for (CastlingSide cs : CastlingSide.values()) {
      if (cs.canCastle(fromPosition, currentPlayer, board, previousMoves)
        && cs.findKingMove(fromPosition) == move) 
      {
        board.move(move);
      }
    }
  }

  private void populateValidMovesByPosition() {
    validMovesForCurrentPlayer.clear();
    Iterator<Integer> positionsIterator = BoardPositioning.positionsIterator();
    while (positionsIterator.hasNext()) {
      int fromPosition = positionsIterator.next();
      Square fromSquare = board.findSquare(fromPosition);
      if (!fromSquare.isOccupied() || fromSquare.getPiece().getColor() != currentPlayer) {
        continue;
      }

      Piece piece = fromSquare.getPiece();
      BoardPiece boardPiece = BoardPieceFactory.getBoardPiece(piece, fromPosition);
      List<Move> validMoves = boardPiece.findMoves(board, previousMoves);
      validMovesForCurrentPlayer.addAll(validMoves);
    }

    System.out.println(String.format("valid moves: %s", validMovesForCurrentPlayer));
  }

}
