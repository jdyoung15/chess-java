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
    int kingPosition = board.findKingPosition(currentPlayer);
    if (new Check(currentPlayer, kingPosition, board).isCheck()) {
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
    boolean validTurn = false;
    while (!validTurn) {
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
       
      if (validMovesByPosition.get(fromPosition).contains(toPosition)) {
        Piece piece = board.findSquare(fromPosition).getPiece();
        if (piece.getPieceType() == PieceType.PAWN
          && new EnPassant(fromPosition, board, currentPlayer, previousMoves).positions().contains(toPosition)) 
        {
          int victimPawnPosition = BoardPositioning.findPosition(BoardPositioning.findRow(fromPosition), BoardPositioning.findCol(toPosition));
          board.findSquare(victimPawnPosition).clear();
        }

        if (piece.getPieceType() == PieceType.KING
          && new Castling(fromPosition, board, currentPlayer, previousMoves).positions().contains(toPosition)) 
        {
          CastlingSide castlingSide = CastlingSide.fromKingMove(fromPosition, toPosition);
          System.out.println(castlingSide);

          int rookFromPosition = BoardPositioning.findPosition(
            fromPosition, 
            castlingSide.getMoveDirection(), 
            castlingSide.getRookFromPosition());
          int rookToPosition = BoardPositioning.findPosition(
            fromPosition, 
            castlingSide.getMoveDirection(), 
            castlingSide.getRookToPosition());

          board.move(rookFromPosition, rookToPosition);
        }
        board.move(fromPosition, toPosition);
        validTurn = true;
        previousMoves.add(new Move(fromPosition, toPosition));
      }
      else {
        System.out.println("\nINVALID MOVE, TRY AGAIN\n");
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

      List<Integer> possibleMoves = 
        new PossibleMoves(board, fromPosition, piece.getPieceType(), piece.getColor(), previousMoves).positions();
      //System.out.println(String.format("Possible moves: %s", BoardPositioning.findCoords(possibleMoves)));

      List<Integer> validMoves = new ValidMoves(fromPosition, possibleMoves, board, piece.getColor()).positions();
      //System.out.println(String.format("valid moves: %s", BoardPositioning.findCoords(validMoves)));

      if (validMoves.isEmpty()) {
        continue;
      }

      validMovesByPosition.put(fromPosition, validMoves);
    }

    System.out.println(validMovesByPosition);
  }

}
