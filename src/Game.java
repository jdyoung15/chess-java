import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// TODO:
// - package
// - 2d array for squares

/**
 * Responsible for the user-facing interactions for a single game of Chess.
 */
public class Game {

  private static final String MESSAGE_CHECKMATE = "Checkmate! %s wins.";
  private static final String MESSAGE_STALEMATE = "Stalemate.";
  private static final String MESSAGE_START_TURN = "Turn: %s";
  private static final String MESSAGE_SELECT_SQUARE_FROM = "Select square to move from:";
  private static final String MESSAGE_SELECT_SQUARE_TO = "Select square to move to (moving from square %s):";
  private static final String MESSAGE_ERROR_INVALID_ENTRY = "\nINVALID ENTRY, TRY AGAIN. FORMAT: [A-H][1-8] eg B5\n";
  private static final String MESSAGE_ERROR_NO_LEGAL_MOVES = "\nNO LEGAL MOVES FROM THIS POSITION, TRY AGAIN\n";
  private static final String MESSAGE_ERROR_ILLEGAL_MOVE = "\nILLEGAL MOVE, TRY AGAIN\n";
  private static final String MESSAGE_PAWN_PROMOTION_CHOICE =
    "\nPawn promotion, choose new piece: " + PawnPromotion.CHOICES
      .stream()
      .map(Piece.Type::toChoiceString)
      .collect(Collectors.joining(" ")) + "\n";

  private static final String PAWN_PROMOTION_CHOICE_REGEX = PawnPromotion.CHOICES
    .stream()
    .map(Piece.Type::toString)
    .collect(Collectors.joining(".*|"));

  private static final Pattern PAWN_PROMOTION_CHOICE_PATTERN =
    Pattern.compile(PAWN_PROMOTION_CHOICE_REGEX);

  private final PlayerLegalMoveFinder playerMoveFinder;
  private final CheckLogic checkLogic;
  private final PawnPromotion pawnPromotion;

  private Board board;
  private Color currentPlayer;
  private List<Move> previousMoves;
  private List<Move> legalMoves;

  public Game() {
    board = new Board();
    currentPlayer = Color.WHITE;
    previousMoves = new ArrayList<>();
    legalMoves = new ArrayList<>();
    playerMoveFinder = new PlayerLegalMoveFinderImpl();
    checkLogic = new CheckLogic();
    pawnPromotion = new PawnPromotion();
  }

  /**
   * Responsible for executing a complete game from start to finish.
   */
  public void play() {
    legalMoves = playerMoveFinder.findLegalMoves(board, currentPlayer, previousMoves);
    while (!legalMoves.isEmpty()) {
      executeTurn();
      currentPlayer = Color.complementOf(currentPlayer);
      legalMoves.clear();
      legalMoves = playerMoveFinder.findLegalMoves(board, currentPlayer, previousMoves);
    }

    System.out.println(board.toString());

    if (checkLogic.isChecked(board, currentPlayer, previousMoves)) {
      Color victor = Color.complementOf(currentPlayer);
      System.out.println(String.format(MESSAGE_CHECKMATE, victor.name().toLowerCase()));
    }
    else {
      System.out.println(MESSAGE_STALEMATE);
    }
  }

  /**
   * Responsible for executing a single turn by the current player.
   */
  private void executeTurn() {
    System.out.println(board.toString());
    Scanner scanner = new Scanner(System.in);     

    while (true) {
      System.out.println(String.format(MESSAGE_START_TURN, currentPlayer.name().toLowerCase()));

      System.out.println(MESSAGE_SELECT_SQUARE_FROM);

      String fromInput = scanner.next().toUpperCase();
      if (!Positioning.isValidPositionInput(fromInput)) {
        System.out.println(MESSAGE_ERROR_INVALID_ENTRY);
        continue;
      }

      int fromPosition = Positioning.toPosition(fromInput);

      if (legalMoves.stream().noneMatch(m -> m.getFromPosition() == fromPosition)) {
        System.out.println(MESSAGE_ERROR_NO_LEGAL_MOVES);
        continue;
      }

      System.out.println(String.format(MESSAGE_SELECT_SQUARE_TO, fromInput));

      String toInput = scanner.next().toUpperCase();
      if (!Positioning.isValidPositionInput(toInput)) {
        System.out.println(MESSAGE_ERROR_INVALID_ENTRY);
        continue;
      }

      int toPosition = Positioning.toPosition(toInput);
       
      Move move = new Move(fromPosition, toPosition);

      if (!legalMoves.contains(move)) {
        System.out.println(MESSAGE_ERROR_ILLEGAL_MOVE);
        continue;
      }

      board.executeMove(move);
      previousMoves.add(move);

      if (pawnPromotion.isEligible(board, currentPlayer, move)) {
        String choice = requestPawnPromotionChoice();
        pawnPromotion.updateBoard(board, currentPlayer, move, choice);
      }

      break;
    }
  }

  /**
   * Asks the user to select a new piece for pawn promotion and returns the user's valid input.
   */
  private String requestPawnPromotionChoice() {
    Scanner scanner = new Scanner(System.in);
    String choice;
    do {
      System.out.println(MESSAGE_PAWN_PROMOTION_CHOICE);
      choice = scanner.next().toUpperCase();
    } while (!isValidChoice(choice));

    return choice.substring(0, 1);
  }

  /**
   * Returns whether the given user-provided choice for pawn promotion is valid.
   */
  public boolean isValidChoice(String choice) {
    Matcher m = PAWN_PROMOTION_CHOICE_PATTERN.matcher(choice);
    return m.matches();
  }

}
