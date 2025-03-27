import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * A Password Strength Checker that evaluates password complexity based on multiple factors.
 * It provides feedback and suggestions for improvement.
 */
public class PasswordStrengthChecker {
    private static final int MIN_LENGTH = 8;
    private static final int MAX_SCORE = 100;
    private static final String[] COMMON_WORDS = { "password", "123456", "qwerty", "admin", "welcome", "abc123" };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        int score = calculateStrength(password);
        String category = determineStrengthCategory(score);

        System.out.printf("\nPassword Strength: %d%% (%s)%n", score, category);
        scanner.close();
    }

    /**
     * Calculates the strength of a given password and provides reasons for deductions.
     * @param password The password to evaluate.
     * @return The strength score (0-100).
     */
    private static int calculateStrength(String password) {
        int score = 0;
        boolean hasUpper = Pattern.compile("[A-Z]").matcher(password).find();
        boolean hasLower = Pattern.compile("[a-z]").matcher(password).find();
        boolean hasNumber = Pattern.compile("[0-9]").matcher(password).find();
        boolean hasSpecial = Pattern.compile("[!@#$%^&*()\\-_=+{};:,<.>?]").matcher(password).find();

        System.out.println("\nPassword Analysis:");

        // Length check
        if (password.length() < MIN_LENGTH) {
            System.out.println("[WARNING] Too short: Use at least " + MIN_LENGTH + " characters.");
            return 0; // If too short, auto fail
        } else if (password.length() >= 12) {
            score += 10;
        } 
        if (password.length() >= 16) {
            score += 10;
        }

        // Character variety checks
        if (hasUpper) {
            score += 20;
        } else {
            System.out.println("[WARNING] No uppercase letters: Try adding some uppercase letters (A-Z).");
        }

        if (hasLower) {
            score += 20;
        } else {
            System.out.println("[WARNING] No lowercase letters: Include lowercase letters (a-z) for better security.");
        }

        if (hasNumber) {
            score += 20;
        } else {
            System.out.println("[WARNING] No numbers: Add digits (0-9) to increase complexity.");
        }

        if (hasSpecial) {
            score += 20;
        } else {
            System.out.println("[WARNING] No special characters: Try using symbols like @, #, $, %, etc.");
        }

        // Repeated characters penalty (New logic: penalizes excessive repeats, allows minor ones)
        int repetitionPenalty = repetitionPenalty(password);
        if (repetitionPenalty > 0) {
            System.out.println("[WARNING] Repeated characters: Avoid repeating characters multiple times.");
            score -= repetitionPenalty;
        }

        // Common dictionary word penalty
        if (containsCommonWord(password)) {
            System.out.println("[WARNING] Common password: Your password contains a frequently used pattern (e.g., 'password', '123456').");
            score -= 30;
        }

        // Ensure score stays in valid range
        return Math.max(0, Math.min(score, MAX_SCORE));
    }

    /**
     * Penalizes passwords that contain excessively repeated characters.
     * @param password The password to analyze.
     * @return Deduction score based on character frequency.
     */
    private static int repetitionPenalty(String password) {
        Map<Character, Integer> charFrequency = new HashMap<>();
        for (char c : password.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        int penalty = 0;
        for (int count : charFrequency.values()) {
            if (count > 2) { // Allow small repetitions, but penalize excessive ones
                penalty += (count - 2) * 2; // Heavier penalty for more repetitions
            }
        }
        return Math.min(penalty, 20);
    }

    /**
     * Checks if the password contains common dictionary words.
     * @param password The password to check.
     * @return true if a common word is found, otherwise false.
     */
    private static boolean containsCommonWord(String password) {
        String lowerPassword = password.toLowerCase();
        for (String word : COMMON_WORDS) {
            if (lowerPassword.contains(word)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines the strength category based on the score.
     * @param score The strength score.
     * @return A string representing the password strength category.
     */
    private static String determineStrengthCategory(int score) {
        if (score >= 80) return "Strong";
        if (score >= 50) return "Medium";
        return "Weak";
    }
}
