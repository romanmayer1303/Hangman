import java.util.Scanner;

/**
 * Created by romanmayer on 26/12/16.
 */
public class Prompter {

    private Game game;

    public Prompter (Game game) {
        this.game = game;
    }

    public boolean promptForGuess() {
        Scanner scanner = new Scanner(System.in);
        boolean isHit = false;
        boolean isAcceptable = false;

        do {
            System.out.print("Enter a letter: ");
            String guessInput = scanner.nextLine();

            try {
                isHit = game.applyGuess(guessInput);
                isAcceptable = true;
            } catch (IllegalArgumentException iae) {
                System.out.printf("%s Please try again. %n",
                        iae.getMessage());
            }
        } while (!isAcceptable);
        return isHit;
    }

    public void displayProgress() {
        System.out.printf("You have %d tries left. Try to solve: %s%n",
                game.getRemainingTries(),
                game.getCurrentProgress());
    }

    public void displayOutcome() {
        if (game.isWon()) {
            System.out.println("Congratulations! You won.");
        } else {
            System.out.printf("Too bad, you lost. The answer was: %s%n", game.getAnswer());
        }

    }

}
