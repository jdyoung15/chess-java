import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class Game {
  
  private Board board;
  private Color currentPlayer;
  private List<Move> previousMoves;
  private Map<Integer, List<Integer>> validMovesByPosition;

  public Game() {
    board = new Board();
    currentPlayer = Color.WHITE;
    previousMoves = new ArrayList<Move>();
    validMovesByPosition = new HashMap<Integer, List<Integer>>();
  }

  public void play() {
    populateValidMovesByPosition();
    while (!validMovesByPosition.isEmpty()) {
      executeTurn();
      currentPlayer = Color.findOpponent(currentPlayer);
      validMovesByPosition.clear();
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

      if (!validMovesByPosition.containsKey(fromPosition)) {
        System.out.println("\nNO VALID MOVES FROM THIS POSITION, TRY AGAIN\n");
        continue;
      }

      System.out.println(String.format("Select square to move to (moving from square %s): ", fromCoords));
      String toCoords = scanner.next();
      int toPosition = BoardPositioning.findPosition(toCoords);
       
      if (!validMovesByPosition.get(fromPosition).contains(toPosition)) {
        System.out.println("\nINVALID MOVE, TRY AGAIN\n");
        continue;
      }

      handleIfEnPassantMove(fromPosition, toPosition);
      handleIfCastlingMove(fromPosition, toPosition);

      board.move(fromPosition, toPosition);
      previousMoves.add(new Move(fromPosition, toPosition));

      break;
    }
  }

  private void handleIfEnPassantMove(int fromPosition, int toPosition) {
    for (EnPassantDirection epd : EnPassantDirection.values()) {
      if (epd.canEnPassant(fromPosition, currentPlayer, board, previousMoves)
        && epd.findAttackingPawnToPosition(fromPosition, currentPlayer) == toPosition) 
      {
        board.findSquare(epd.findVictimPawnToPosition(fromPosition, currentPlayer)).clear();
      }
    }
  }

  private void handleIfCastlingMove(int fromPosition, int toPosition) {
    for (CastlingSide cs : CastlingSide.values()) {
      if (cs.canCastle(fromPosition, currentPlayer, board, previousMoves)
        && cs.findKingToPosition(fromPosition) == toPosition) 
      {
        board.move(cs.findRookFromPosition(fromPosition), cs.findRookToPosition(fromPosition));
      }
    }
  }

  private void populateValidMovesByPosition() {
    Iterator<Integer> positionsIterator = BoardPositioning.positionsIterator();
    while (positionsIterator.hasNext()) {
      int fromPosition = positionsIterator.next();
      Square fromSquare = board.findSquare(fromPosition);
      if (!fromSquare.isOccupied() || fromSquare.getPiece().getColor() != currentPlayer) {
        continue;
      }

      Piece piece = fromSquare.getPiece();

      BoardPiece boardPiece = BoardPieceFactory.getBoardPiece(piece, fromPosition);

      List<Integer> validMoves = boardPiece.findMoves(board, previousMoves);
      System.out.println(String.format("valid moves: %s", BoardPositioning.findCoords(validMoves)));
      if (validMoves.isEmpty()) {
        continue;
      }

      validMovesByPosition.put(fromPosition, validMoves);
    }
  }

}
