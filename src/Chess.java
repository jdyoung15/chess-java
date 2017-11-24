public class Chess {

  public static void main(String[] args) {
    play();
  }

  private static void play() {
    Board board = new Board();
    System.out.println(board.toString());
  }

}
