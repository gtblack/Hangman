package gtblack.hangman.state;

import gtblack.hangman.WordsHelper;

import java.util.List;
import java.util.Set;

public class HangmanState {

    private static final int INITIAL_GUESSES = 7;

    private int lives;

    private WordState wordState;
    private WordsHelper wordsHelper;
    private GameState gameState;

    public HangmanState(WordsHelper wordsHelper) {
        wordState = new WordState();
        this.wordsHelper = wordsHelper;
        startNewGame();
    }

    public boolean playerWon() {
        return wordState.allRevealed();
    }

    public boolean playerDefeated() {
        return lives <= 0;
    }

    public void reduceLives() {
        lives--;
    }

    public void startNewGame() {
        lives = INITIAL_GUESSES;
        gameState = GameState.STARTED;
        wordState.init();
        wordState.setCurrentWord(wordsHelper.randomWord());
    }

    public void updateRightGuess(List<Integer> positions) {
        wordState.addOpenedPositions(positions);
        if (wordState.allRevealed()) {
            gameState = GameState.GAME_ENDED;
        } else {
            gameState = GameState.IN_PROGRESS_RIGHT_GUESS;
        }
    }

    public void updateWrongGuess() {
        reduceLives();
        if (playerDefeated()) {
            gameState = GameState.GAME_ENDED;
        } else {
            gameState = GameState.IN_PROGRESS_WRONG_GUESS;
        }
    }

    public String getCurrentWord() {
        return wordState.getCurrentWord();
    }

    public Set<Integer> getOpenedPositions() {
        return wordState.getOpenedPositions();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getLives() {
        return lives;
    }
}
