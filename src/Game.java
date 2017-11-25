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
      SquarePosition from = new SquarePosition(fromCoords);

      System.out.println(String.format("Valid moves: %s", MovesFinder.findMoves(board, from, PieceType.ROOK, Color.WHITE)));

      System.out.println(String.format("Select square to move to (moving from square %s): ", fromCoords));
      String toCoords = scanner.next();
       
      if (board.isValidMove(fromCoords, toCoords)){
        board.move(fromCoords, toCoords);
        validTurn = true;
      }
      else {
        System.out.println("Invalid move, try again");
      }
    }
  }

}
