/**
 * Created by romanmayer on 26/12/16.
 */
public class Game {
    public static final int MAX_MISSES = 7;
    private String answer;
    private String hits;
    private String misses;

    public Game(String answer) {
        this.answer = answer.toLowerCase();
        hits = "";
        misses = "";
    }

    public String getAnswer() {
        return answer;
    }

    public String getHits() {
        return hits;
    }

    public String getMisses() {
        return misses;
    }

    private char normalizeGuess(char letter) {
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException("This is not a letter.");
        }
        letter = Character.toLowerCase(letter);
        if (misses.indexOf(letter) != -1 || hits.indexOf(letter) != -1) {
            throw new IllegalArgumentException("Letter has already been guessed.");
        }
        return letter;
    }

    public boolean applyGuess(String letters) {
        if (letters.length() == 0) {
            throw new IllegalArgumentException("No letter found.");
        }
        return applyGuess(letters.charAt(0));
    }

    public boolean applyGuess(char letter) {
        letter = normalizeGuess(letter);
        boolean isHit = answer.indexOf(letter) != -1;
        if (isHit) {
            hits += letter;
        }
        else {
            misses += letter;
        }
        return isHit;
    }

    public String getCurrentProgress() {
        String progress = "";
        for (char letter : answer.toCharArray()) {
            if (hits.indexOf(letter) != -1) {
                progress += letter;
            } else {
                progress += '-';
            }
        }
        return progress;
    }

    public boolean isWon() {
        return !getCurrentProgress().contains("-");
    }

    public int getRemainingTries() {
        return MAX_MISSES - misses.length();
    }
}
