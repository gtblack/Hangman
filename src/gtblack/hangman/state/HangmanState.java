package gtblack.hangman.state;

public class HangmanState {

    private static final int INITIAL_GUESSES = 7;

    private int lives;
    private WordState wordState;
    private GameState gameState;

    private HangmanState() {

    }

    public HangmanState init() {
        HangmanState hangmanState = new HangmanState();
        hangmanState.setLives(INITIAL_GUESSES);

    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
