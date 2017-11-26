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
      SquarePosition from = new SquarePosition(fromCoords);

      List<SquarePosition> validMoves = MovesFinder.findMoves(board, from, PieceType.ROOK, Color.WHITE);
      System.out.println(String.format("Valid moves: %s", validMoves));

      System.out.println(String.format("Select square to move to (moving from square %s): ", fromCoords));
      String toCoords = scanner.next();
      SquarePosition to = new SquarePosition(toCoords);

       
      if (validMoves.contains(to)){
        board.move(board.findSquare(from), board.findSquare(to));
        validTurn = true;
      }
      else {
        System.out.println("Invalid move, try again");
      }
    }
  }

}
