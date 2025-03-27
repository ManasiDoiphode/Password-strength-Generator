# Password Strength Checker

A Java-based Password Strength Checker that evaluates password complexity and provides feedback to enhance security.

## Features
- **Strength Evaluation**: Scores passwords based on length, character variety, and uniqueness.
- **Detailed Feedback**: Warns about missing character types, common words, and excessive repetition.
- **Custom Scoring System**: Assigns a strength percentage (0-100) and categorizes passwords as Weak, Medium, or Strong.

## How It Works
1. The user enters a password.
2. The program evaluates the password based on:
   - Length
   - Presence of uppercase and lowercase letters
   - Inclusion of numbers and special characters
   - Repetitive characters
   - Common dictionary words
3. A strength score is calculated, and feedback is provided.

## Installation & Execution
1. Ensure Java (JDK 8 or later) is installed.
2. Compile the program:
   ```sh
   javac PasswordStrengthChecker.java
   ```
3. Run the program:
   ```sh
   java PasswordStrengthChecker
   ```

## Example Usage
```
Enter a password: Pa$$w0rd123

Password Analysis:
[WARNING] Common password: Your password contains a frequently used pattern (e.g., 'password', '123456').
Password Strength: 70% (Medium)
```

## Future Enhancements
- Implement real-time password suggestions.
- Enhance common word detection with a larger dictionary.
- Add a GUI for better usability.

## License
This project is open-source and available under the MIT License.

