package gtblack.hangman;

import gtblack.hangman.output.HangmanStringFormulator;
import gtblack.hangman.state.GameState;
import gtblack.hangman.state.HangmanState;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Hangman {

    private static WordsHelper wordsHelper;
    private static HangmanStringFormulator hangmanStringFormulator;
    private static HangmanState hangmanState;
    private static Scanner sc;

    public static void main(String[] args) {
        try {
            wordsHelper = new WordsHelper();
        } catch (IOException e) {
            System.out.println(hangmanStringFormulator.getErrorNoFileMessage());
            return;
        }
        hangmanState = new HangmanState(wordsHelper);
        hangmanStringFormulator = new HangmanStringFormulator(hangmanState);
        sc = new Scanner(System.in);
        handleInput();
    }

    private static void handleInput() {
        boolean endGame = false;
        GameState tempState = GameState.STARTED;
        String input;
        while (!endGame) {
            switch (hangmanState.getGameState()) {
                case STARTED:
                    tempState = GameState.STARTED;
                    System.out.println(hangmanStringFormulator.getWelcomeMessage());
                    processGuess(readNextGuess());
                    break;
                case IN_PROGRESS_RIGHT_GUESS:
                    tempState = GameState.IN_PROGRESS_RIGHT_GUESS;
                    System.out.println(hangmanStringFormulator.getRightGuessMessage());
                    processGuess(readNextGuess());
                    break;
                case IN_PROGRESS_WRONG_GUESS:
                    tempState = GameState.IN_PROGRESS_WRONG_GUESS;
                    System.out.println(hangmanStringFormulator.getWrongGuessMessage());
                    processGuess(readNextGuess());
                    break;
                case GAME_ENDED:
                    if (hangmanState.playerWon()) {
                        System.out.println(hangmanStringFormulator.getVictoryMessage());
                    } else {
                        System.out.println(hangmanStringFormulator.getGameOverMessage());
                    }
                    System.out.print(hangmanStringFormulator.getContinueMessage());

                    input = sc.nextLine();

                    endGame = input.equals("n");
                    if (input.equals("y")) {
                        hangmanState.startNewGame();
                    } else if (!input.equals("y") && !input.equals("n")) {
                        hangmanState.setGameState(GameState.INCORRECT_INPUT);
                    }
                    break;
                case INCORRECT_INPUT:
                    System.out.println(hangmanStringFormulator.getErrorWrongInputMessage());
                    hangmanState.setGameState(tempState);
                    break;
            }
        }

        sc.close();
    }

    private static String readNextGuess() {
        System.out.println(hangmanStringFormulator.getWordStatusMessage());
        System.out.println(hangmanStringFormulator.getLivesLeftMessage());
        System.out.print(hangmanStringFormulator.getNextGuessMessage());

        String input = sc.nextLine();

        return input;
    }

    private static void processGuess(String input) {
        if (input.length() > 2 || input.length() == 0) {
            hangmanState.setGameState(GameState.INCORRECT_INPUT);
            return;
        }

        List<Integer> rightlyGuessedPlaces =
                wordsHelper.checkCharacterAgainstWord(hangmanState.getCurrentWord(), input.charAt(0));
        if (rightlyGuessedPlaces.size() == 0) {
            hangmanState.updateWrongGuess();
        } else {
            hangmanState.updateRightGuess(rightlyGuessedPlaces);
        }
    }
}
