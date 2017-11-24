import java.util.Scanner;

public class Chess {

  public static void main(String[] args) {
    play();
  }

  private static void play() {
    Board board = new Board();
    //   display board
    System.out.println(board.toString());
    executeTurn(board);
    System.out.println(board.toString());
    // while game not won:
    //   turn
    // announce victor
  }

  private static void executeTurn(Board board) {
    boolean validTurn = false;
    while (!validTurn) {
      System.out.println("Turn: White");

      Scanner scanner = new Scanner(System.in);     
      
      System.out.println("Select square to move from: ");
      String fromCoords = scanner.next();

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
