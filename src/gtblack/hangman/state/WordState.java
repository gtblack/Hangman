package gtblack.hangman.state;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordState {

    private String currentWord;
    private Set<Integer> openedPositions;

    public WordState() {
        init();
    }

    public void init() {
        openedPositions = new HashSet<>();
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public void addOpenedPositions(List<Integer> pos) {
        for (Integer i : pos) {
            openedPositions.add(i);
        }
    }

    public boolean allRevealed() {
        return currentWord.length() == openedPositions.size();
    }

    public Set<Integer> getOpenedPositions() {
        return openedPositions;
    }
}
