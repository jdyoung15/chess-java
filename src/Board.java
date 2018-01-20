import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Board {


  private List<Square> squares;

  public Board() {
    initializeSquares();
    BoardPositioning.populateSquares(squares);
    //squares.get(61).clear();
    //squares.get(62).clear();
    //squares.get(57).clear();
    //squares.get(58).clear();
    //squares.get(59).clear();
    //squares.get(1).clear();
    //squares.get(2).clear();
    //squares.get(3).clear();
    //squares.get(5).clear();
    //squares.get(6).clear();
  }

  public Board(List<Square> squares) {
    this.squares = squares;
  }

  private void initializeSquares() {
    squares = new ArrayList<Square>();

    for (int i = 0; i < BoardPositioning.NUM_SQUARES; i++) {
      squares.add(new Square());
    }

    //int row = 0;
    //for (int col = 0; col < NUM_COLS; col++) {
    //  squares.add(findPosition(row, col), new Square(new Piece(Color.BLACK, NON_PAWN_PIECE_ORDER[col])));
    //}

    //row = 1;
    //for (int col = 0; col < NUM_COLS; col++) {
    //  squares.add(findPosition(row, col), new Square(new Piece(Color.BLACK, PieceType.PAWN)));
    //}

    //for (row = 2; row < 6; row++) {
    //  for (int col = 0; col < NUM_COLS; col++) {
    //    squares.add(findPosition(row, col), new Square());
    //  }
    //}

    //row = 6;
    //for (int col = 0; col < NUM_COLS; col++) {
    //  squares.add(findPosition(row, col), new Square(new Piece(Color.WHITE, PieceType.PAWN)));
    //}

    //row = 7;
    //for (int col = 0; col < NUM_COLS; col++) {
    //  squares.add(findPosition(row, col), new Square(new Piece(Color.WHITE, NON_PAWN_PIECE_ORDER[col])));
    //}


    // get iterator for white pawns
    // get iterator for white non pawns
    // get iterator for black pawns
    // get iterator for black non pawns


    //Piece kingWhite = new Piece(Color.WHITE, PieceType.KING);
    //Piece rookWhite = new Piece(Color.WHITE, PieceType.ROOK);
    //Piece bishopWhite = new Piece(Color.WHITE, PieceType.QUEEN);
    //Piece pawnWhite = new Piece(Color.WHITE, PieceType.PAWN);
    ////Piece kingBlack = new Piece(Color.BLACK, PieceType.KING);
    //Piece rookBlack = new Piece(Color.BLACK, PieceType.ROOK);
    //squares.set(45, new Square(kingWhite));
    //squares.set(56, new Square(rookWhite));
    ////squares.set(63, new Square(rookWhite));
    //squares.set(39, new Square(bishopWhite));
    //squares.set(55, new Square(pawnWhite));
    ////squares.set(4, new Square(kingBlack));
    //squares.set(16, new Square(rookBlack));
  }

  public boolean isChecked(Color currentPlayer) {
    List<Integer> opponentPositions = findOpponentPositions(currentPlayer);
    for (int opponentPosition : opponentPositions) {
      Square square = findSquare(opponentPosition);
      Piece opponentPiece = square.getPiece();
      BoardPiece opponentBoardPiece = BoardPieceFactory.getBoardPiece(opponentPiece, opponentPosition);
      if (opponentBoardPiece.isChecking(this)) {
        return true;
      }
    }
    return false;
  }

  public List<Integer> findOpponentPositions(Color currentPlayer) {
    List<Integer> positions = new ArrayList<Integer>();
    Color opponent = Color.findOpponent(currentPlayer);

    Iterator<Integer> positionsIterator = BoardPositioning.positionsIterator();
    while (positionsIterator.hasNext()) {
      int position = positionsIterator.next();
      Square square = findSquare(position);
      if (square.containsOpponent(currentPlayer)) {
        positions.add(position); 
      }
    }

    return positions;
  }

  public int findKingPosition(Color currentPlayer) {
    Iterator<Integer> positionsIterator = BoardPositioning.positionsIterator();
    while (positionsIterator.hasNext()) {
      int position = positionsIterator.next();
      Square square = findSquare(position);
      if (square.isOccupied()) {
        Piece piece = square.getPiece();
        if (piece.getPieceType() == PieceType.KING && piece.getColor() == currentPlayer) {
          return position;
        }
      }
    }
    return -1;
  }

  public void move(int fromPosition, int toPosition) {
    Square from = findSquare(fromPosition);
    Square to = findSquare(toPosition);

    to.setPiece(from.getPiece());
    from.clear();
  }

  public Square findSquare(int position) {
    return squares.get(position);
  }

  public List<Square> findSquares(List<Integer> positions) {
    List<Square> squares = new ArrayList<Square>();
    for (int position : positions) {
      squares.add(findSquare(position));
    }
    return squares;
  }

  public Board copy() {
    List<Square> squaresCopy = new ArrayList<Square>();
    for (Square square : squares) {
      squaresCopy.add(square.copy());
    }
    Board boardCopy = new Board(squaresCopy);
    return boardCopy;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int row = 0; row < BoardPositioning.NUM_ROWS; row++) {
      // add row number
      sb.append(String.format("%d  ", BoardPositioning.NUM_ROWS - row));
      // add string representations of squares
      for (int col = 0; col < BoardPositioning.NUM_COLS; col++) {
        int position = BoardPositioning.findPosition(row, col);
        sb.append(squares.get(position).toString());
        sb.append(" ");
      }
      sb.append("\n");
    }

    // add column letters
    sb.append("\n   ");
    for (int col = 0; col < BoardPositioning.NUM_COLS; col++) {
      sb.append(String.format("%c  ", (char) col + BoardPositioning.ASCII_OFFSET));
    }

    sb.append("\n\n");
    return sb.toString();
  }

}
