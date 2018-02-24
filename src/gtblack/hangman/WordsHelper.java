package gtblack.hangman;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordsHelper {

    private static final String WORDS_FILE_LOCATION = "data/words.txt";

    private List<String> dictionary;

    public WordsHelper() throws IOException {
        dictionary = readWordsFromFile(WORDS_FILE_LOCATION);
    }

    public String randomWord() {
        int idx = (int) (Math.random() * dictionary.size());
        return dictionary.get(idx);
    }

    public List<Integer> checkCharacterAgainstWord(String word, char c) {
        List<Integer> pos = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (c == word.charAt(i)) {
                pos.add(i);
            }
        }

        return pos;
    }

    private List<String> readWordsFromFile(String fileLoc) throws IOException {
        List<String> words = new ArrayList<>();
        FileReader fr = new FileReader(getClass().getResource(fileLoc).getFile());
        Scanner sc = new Scanner(fr);
        while (sc.hasNext()) {
            words.add(sc.nextLine());
        }
        sc.close();
        fr.close();
        return words;
    }
}
