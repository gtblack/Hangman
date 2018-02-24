package gtblack.hangman.output;

import gtblack.hangman.state.HangmanState;

import java.util.Set;

public class HangmanStringFormulator {

    private static final String WELCOME_MESSAGE = "You wanna play some Hangman?";
    private static final String RIGHT_GUESS_MESSAGE = "Your guess was right!";
    private static final String WRONG_GUESS_MESSAGE = "Your guess was wrong!";
    private static final String LIVES_LEFT_MESSAGE = "You have %d lives left.";
    private static final String GAME_OVER_MESSAGE = "You have no lives left. Game over.";
    private static final String VICTORY_MESSAGE = "You won!";
    private static final String CONTINUE_MESSAGE = "Want some more Hangman? (y/n) ";
    private static final String NEXT_GUESS_MESSAGE = "Take a guess! Please enter a character and type enter: ";
    private static final String ERROR_NO_FILE_MESSAGE = "Error from reading the words file!";
    private static final String ERROR_WRONG_INPUT_MESSAGE =
            "That was unexpected! Please try to type in the correct input as requested.";

    private HangmanState hangmanState;

    public HangmanStringFormulator(HangmanState hangmanState) {
        this.hangmanState = hangmanState;
    }

    public String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    public String getRightGuessMessage() {
        return RIGHT_GUESS_MESSAGE;
    }

    public String getWrongGuessMessage() {
        return WRONG_GUESS_MESSAGE;
    }

    public String getLivesLeftMessage() {
        return String.format(LIVES_LEFT_MESSAGE, hangmanState.getLives());
    }

    public String getGameOverMessage() {
        return GAME_OVER_MESSAGE;
    }

    public String getVictoryMessage() {
        return VICTORY_MESSAGE;
    }

    public String getContinueMessage() {
        return CONTINUE_MESSAGE;
    }

    public String getWordStatusMessage() {
        String currentWord = hangmanState.getCurrentWord();
        Set<Integer> positions = hangmanState.getOpenedPositions();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < currentWord.length(); i++) {
            sb.append("_ ");
        }
        for (Integer i : positions) {
            int pos = i.intValue() * 2;
            sb.replace(pos, pos + 1, String.valueOf(currentWord.charAt(i.intValue())));
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public String getNextGuessMessage() {
        return NEXT_GUESS_MESSAGE;
    }

    public String getErrorNoFileMessage() {
        return ERROR_NO_FILE_MESSAGE;
    }

    public String getErrorWrongInputMessage() {
        return ERROR_WRONG_INPUT_MESSAGE;
    }
}
