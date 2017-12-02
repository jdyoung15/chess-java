import java.util.List;
import java.util.Scanner;

public class Game {
  
  private Board board;

  public void play() {
    board = new Board();
    // while game not over:
    //   turn
    System.out.println(board.toString());
    executeTurn();
    System.out.println(board.toString());
    // if checkmate
    //   announce victor
    // else announce stalemate
  }

  private void executeTurn() {
    boolean validTurn = false;
    while (!validTurn) {
      System.out.println("Turn: White");

      Scanner scanner = new Scanner(System.in);     
      
      System.out.println("Select square to move from: ");
      String fromCoords = scanner.next();
      int fromPosition = 0;

      List<Integer> validMoves = new PossibleMoves(board, fromPosition, PieceType.ROOK, Color.WHITE).positions();
      System.out.println(String.format("Valid moves: %s", validMoves));

      System.out.println(String.format("Select square to move to (moving from square %s): ", fromCoords));
      String toCoords = scanner.next();
      int toPosition = 4;

       
      //if (validMoves.contains(toPosition)){
      //  board.move(board.findSquare(fromPosition), board.findSquare(toPosition));
      //  validTurn = true;
      //}
      //else {
      //  System.out.println("Invalid move, try again");
      //}
    }
  }

}
