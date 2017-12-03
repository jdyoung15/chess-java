import java.util.List;
import java.util.Scanner;

public class Game {
  
  private Board board;
  private Color currentPlayer;

  public void play() {
    board = new Board();
    currentPlayer = Color.WHITE;

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
        new PossibleMoves(board, fromPosition, piece.getPieceType(), piece.getColor()).positions();
      System.out.println(String.format("Possible moves: %s", Board.findCoords(possibleMoves)));

      List<Integer> validMoves = 
        new ValidMoves(fromPosition, possibleMoves, board, piece.getColor()).positions();
      System.out.println(String.format("valid moves: %s", Board.findCoords(validMoves)));

      System.out.println(String.format("Select square to move to (moving from square %s): ", fromCoords));
      String toCoords = scanner.next();
      int toPosition = Board.findPosition(toCoords);
      //Square toSquare = board.findSquare(fromPosition);
       
      if (validMoves.contains(toPosition)){
        board.move(fromPosition, toPosition);
        validTurn = true;
      }
      else {
        System.out.println("Invalid move, try again");
      }
    }
  }

}
