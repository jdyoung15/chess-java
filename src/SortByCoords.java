import java.util.Comparator;

public class SortByCoords implements Comparator<Move> {

  public int compare(Move a, Move b) {
    int aFrom = a.getFromPosition();
    int bFrom = b.getFromPosition();

    int aFromRow = BoardPositioning.findRow(aFrom);
    int bFromRow = BoardPositioning.findRow(bFrom);

    if (aFromRow != bFromRow) {
      return aFromRow - bFromRow;
    }

    int aFromCol = BoardPositioning.findCol(aFrom);
    int bFromCol = BoardPositioning.findCol(bFrom);

    if (aFromCol != bFromCol) {
      return aFromCol - bFromCol;
    }

    int aTo = a.getToPosition();
    int bTo = b.getToPosition();

    int aToRow = BoardPositioning.findRow(aTo);
    int bToRow = BoardPositioning.findRow(bTo);

    if (aToRow != bToRow) {
      return aToRow - bToRow;
    }

    int aToCol = BoardPositioning.findCol(aTo);
    int bToCol = BoardPositioning.findCol(bTo);

    return aToCol - bToCol;
  }

}
