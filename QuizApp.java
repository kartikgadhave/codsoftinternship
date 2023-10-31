import java.util.Scanner;
// Question.java
class Question {
    private String questionText;
    private String[] options;
    private int correctOption;

    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

// QuizApp.java


public class QuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        Question[] questions = {
            new Question("What is the capital of France?",
                    new String[]{"A) London", "B) Berlin", "C) Paris", "D) Madrid"},
                    2), // Correct answer is C (Paris)

            new Question("Which planet is known as the Red Planet?",
                    new String[]{"A) Earth", "B) Mars", "C) Venus", "D) Jupiter"},
                    1) // Correct answer is B (Mars)
        };

        for (int i = 0; i < questions.length; i++) {
            Question question = questions[i];
            System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());

            String[] options = question.getOptions();
            for (String option : options) {
                System.out.println(option);
            }

            System.out.print("Enter the option (A, B, C, D): ");
            String userAnswer = scanner.next().toUpperCase();

            if (userAnswer.equals("A") || userAnswer.equals("B") || userAnswer.equals("C") || userAnswer.equals("D")) {
                int userOption = userAnswer.charAt(0) - 'A'; // Convert A, B, C, D to 0, 1, 2, 3
                if (userOption == question.getCorrectOption()) {
                    System.out.println("Correct!\n");
                    score++;
                } else {
                    System.out.println("Incorrect. The correct answer is " + options[question.getCorrectOption()] + "\n");
                }
            } else {
                System.out.println("Invalid option. Please select A, B, C, or D.\n");
            }
        }

        System.out.println("Quiz completed! Your score: " + score + " out of " + questions.length);
        scanner.close();
    }
}
