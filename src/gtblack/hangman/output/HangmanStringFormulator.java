package gtblack.hangman.output;

import java.util.List;
import java.util.Set;

public class HangmanStringFormulator {

    private static final String WELCOME_MESSAGE = "You wanna play some Hangman?";
    private static final String LIVES_LEFT_MESSAGE = "You have %f lives left.";
    private static final String GAME_OVER_MESSAGE = "You have no lives left. Game over.";
    private static final String CONTINUE_MESSAGE = "Want some more Hangman?";

    public String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    public String getLivesLeftMessage(int lives) {
        return String.format(LIVES_LEFT_MESSAGE, lives);
    }

    public String getGameOverMessage() {
        return GAME_OVER_MESSAGE;
    }

    public String getContinueMessage() {
        return CONTINUE_MESSAGE;
    }

    public String getWordStatusMessage(String currentWord, Set<Integer> positions) {
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
}
