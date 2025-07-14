import java.util.Random;
import java.util.Scanner;

public class NumberGuessGame {

    private static final int MIN = 1;          
    private static final int MAX = 100;        
    private static final int MAX_ATTEMPTS = 10; 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random  rng = new Random();

        int roundsPlayed = 0;
        int roundsWon    = 0;

        System.out.println("=== Welcome to the Number‑Guessing Game ===");

        boolean playAgain;
        do {
            roundsPlayed++;
            int secret = rng.nextInt(MAX - MIN + 1) + MIN;
            int attemptsLeft = MAX_ATTEMPTS;
            boolean guessedCorrectly = false;

            System.out.printf("%nRound %d begins! I'm thinking of a number between %d and %d.%n",
                              roundsPlayed, MIN, MAX);

            while (attemptsLeft > 0 && !guessedCorrectly) {
                System.out.printf("You have %d guess%s left. Enter your guess: ",
                                  attemptsLeft, attemptsLeft == 1 ? "" : "es");

                int guess;
               
                while (!sc.hasNextInt()) {
                    System.out.print("Please enter a valid integer: ");
                    sc.next(); 
                }
                guess = sc.nextInt();

                attemptsLeft--;

                if (guess == secret) {
                    guessedCorrectly = true;
                    roundsWon++;
                    System.out.printf("🎉 Correct! You got it in %d attempt%s.%n",
                                      MAX_ATTEMPTS - attemptsLeft,
                                      (MAX_ATTEMPTS - attemptsLeft) == 1 ? "" : "s");
                } else if (guess < secret) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }
            }

            if (!guessedCorrectly) {
                System.out.printf("☹️  Out of attempts. The number was %d.%n", secret);
            }

            // Show running score
            System.out.printf("Score: %d/%d rounds won.%n", roundsWon, roundsPlayed);

            // Ask if the player wants another round
            System.out.print("Play again? (y/N): ");
            String ans = sc.next().trim().toLowerCase();
            playAgain = ans.equals("y") || ans.equals("yes");

        } while (playAgain);

        System.out.printf("%nThanks for playing! Final score: %d/%d rounds won.%n",
                          roundsWon, roundsPlayed);
        sc.close();
    }
}
