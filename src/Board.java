import java.util.List;
import java.util.ArrayList;

public class Board {


  private static final PieceType[] NON_PAWN_PIECE_ORDER = {
    PieceType.ROOK,
    PieceType.KNIGHT,
    PieceType.BISHOP,
    PieceType.QUEEN,
    PieceType.KING,
    PieceType.BISHOP,
    PieceType.KNIGHT,
    PieceType.ROOK
  };

  private List<Square> squares;

  public Board() {
    initializeSquares();
  }

  public Board(List<Square> squares) {
    this.squares = squares;
  }

  private void initializeSquares() {
    squares = new ArrayList<Square>();

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

    for (int i = 0; i < BoardPositioning.NUM_SQUARES; i++) {
      squares.add(new Square());
    }
    Piece kingWhite = new Piece(Color.WHITE, PieceType.KING);
    Piece rookWhite = new Piece(Color.WHITE, PieceType.ROOK);
    Piece bishopWhite = new Piece(Color.WHITE, PieceType.QUEEN);
    Piece pawnWhite = new Piece(Color.WHITE, PieceType.PAWN);
    //Piece kingBlack = new Piece(Color.BLACK, PieceType.KING);
    Piece rookBlack = new Piece(Color.BLACK, PieceType.ROOK);
    squares.set(45, new Square(kingWhite));
    squares.set(56, new Square(rookWhite));
    //squares.set(63, new Square(rookWhite));
    squares.set(39, new Square(bishopWhite));
    squares.set(55, new Square(pawnWhite));
    //squares.set(4, new Square(kingBlack));
    squares.set(16, new Square(rookBlack));
  }

  public List<Integer> findOpponentPositions(Color currentPlayer) {
    List<Integer> positions = new ArrayList<Integer>();
    Color opponent = Color.findOpponent(currentPlayer);
    int position = 0;
    for (Square square : squares) {
      if (square.isOccupied() && square.getPiece().getColor() == opponent) {
        positions.add(position); 
      }
      position++;
    }
    return positions;
  }

  public int findKingPosition(Color currentPlayer) {
    int position = 0;
    for (Square square : squares) {
      if (square.isOccupied()) {
        Piece piece = square.getPiece();
        if (piece.getPieceType() == PieceType.KING && piece.getColor() == currentPlayer) {
          return position;
        }
      }
      position++;
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
        sb.append(squares.get(row * BoardPositioning.NUM_COLS + col).toString());
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
