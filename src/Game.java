import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
  
  private Board board;
  private Color currentPlayer;
  private List<Move> previousMoves;

  public void play() {
    board = new Board();
    currentPlayer = Color.WHITE;
    previousMoves = new ArrayList<Move>();

    // while current player has valid move:
    //   turn
    while (true) {
      executeTurn();
      currentPlayer = Color.findOpponent(currentPlayer);
    }
    // if king of current player is checked (checkmate) 
    //   announce victor (opponent of current player)
    // else announce stalemate
  }

  private void executeTurn() {
    Scanner scanner = new Scanner(System.in);     
    boolean validTurn = false;
    while (!validTurn) {
      System.out.println(board.toString());
      System.out.println(String.format("Turn: %s", currentPlayer.name().toLowerCase()));

      System.out.println("Select square to move from: ");
      String fromCoords = scanner.next();
      int fromPosition = Board.findPosition(fromCoords);
      Square fromSquare = board.findSquare(fromPosition);

      if (!fromSquare.isOccupied() || fromSquare.getPiece().getColor() != currentPlayer) {
        System.out.println("Invalid move, try again");
        continue;
      }

      Piece piece = fromSquare.getPiece();

      List<Integer> possibleMoves = 
        new PossibleMoves(board, fromPosition, piece.getPieceType(), piece.getColor(), previousMoves).positions();
      System.out.println(String.format("Possible moves: %s", Board.findCoords(possibleMoves)));

      List<Integer> validMoves = 
        new ValidMoves(fromPosition, possibleMoves, board, piece.getColor()).positions();
      System.out.println(String.format("valid moves: %s", Board.findCoords(validMoves)));

      System.out.println(String.format("Select square to move to (moving from square %s): ", fromCoords));
      String toCoords = scanner.next();
      int toPosition = Board.findPosition(toCoords);
       
      if (validMoves.contains(toPosition)){
        if (piece.getPieceType() == PieceType.PAWN
          && new EnPassant(fromPosition, board, currentPlayer, previousMoves).positions().contains(toPosition)) 
        {
          int victimPawnPosition = Board.findPosition(Board.findRow(fromPosition), Board.findCol(toPosition));
          board.findSquare(victimPawnPosition).clear();
        }

        if (piece.getPieceType() == PieceType.KING
          && new Castling(fromPosition, board, currentPlayer, previousMoves).positions().contains(toPosition)) 
        {
          CastlingSide castlingSide = CastlingSide.fromKingMove(fromPosition, toPosition);

          int rookFromPosition = 
            Board.calculateNewPosition(fromPosition, castlingSide.getRookFromPosition() * castlingSide.getDirectionValue(), 0);
          int rookToPosition = 
            Board.calculateNewPosition(fromPosition, castlingSide.getRookToPosition() * castlingSide.getDirectionValue(), 0);

          board.move(rookFromPosition, rookToPosition);
        }
        board.move(fromPosition, toPosition);
        validTurn = true;
        previousMoves.add(new Move(fromPosition, toPosition));
      }
      else {
        System.out.println("Invalid move, try again");
      }
    }
  }

}
