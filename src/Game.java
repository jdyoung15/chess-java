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
      int fromPosition = Board.findPosition(fromCoords);
      Square fromSquare = board.findSquare(fromPosition);
      Piece piece = fromSquare.getPiece();

      List<Integer> possibleMoves = 
        new PossibleMoves(board, fromPosition, piece.getPieceType(), piece.getColor()).positions();
      System.out.println(String.format("Possible moves: %s", Board.findCoords(possibleMoves)));

      List<Integer> validMoves = 
        new ValidMoves(fromPosition, possibleMoves, board, piece.getColor()).positions();
      System.out.println(String.format("valid moves: %s", Board.findCoords(validMoves)));

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
