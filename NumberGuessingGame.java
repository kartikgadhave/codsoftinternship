import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int attempts = 10;
        int rounds = 0;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("You have " + attempts + " attempts to guess the number between " + minRange + " and " + maxRange);

        boolean playAgain = true;

        while (playAgain) {
            int numberToGuess = random.nextInt(maxRange - minRange + 1) + minRange;
            int currentAttempts = 0;
            boolean roundWon = false;

            while (currentAttempts < attempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                currentAttempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the correct number in " + currentAttempts + " attempts.");
                    score++;
                    roundWon = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Your guess is too low. Attempts left: " + (attempts - currentAttempts));
                } else {
                    System.out.println("Your guess is too high. Attempts left: " + (attempts - currentAttempts));
                }
            }

            if (!roundWon) {
                System.out.println("Sorry, you've used all your attempts. The correct number was " + numberToGuess);
            }

            rounds++;

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainResponse = scanner.next();
            playAgain = playAgainResponse.equalsIgnoreCase("yes");
        }

        System.out.println("Game Over! You played " + rounds + " round(s) and your score is " + score);
        scanner.close();
    }
}
