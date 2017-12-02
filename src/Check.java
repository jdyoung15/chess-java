public class Check {

  private Color currentPlayer;
  private int kingPosition;
  private Board board;

  public Check(Color currentPlayer, int kingPosition, Board board) {
    this.currentPlayer = currentPlayer;
    this.kingPosition = kingPosition;
    this.board = board;
  }
  
  public boolean isCheck() {
    // for each oppoenent square
    //   find possible moves for that square/piece
    //   if moves include king square, return true 
    // return false
    return false;
  }

}
