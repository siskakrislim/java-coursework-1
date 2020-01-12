//Name : Siska Kristanti Lim
//Student number : 170281939
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
//Comments for improvements:
//variable name "badGuesses" is better named as "wrongLetterGuessed"
//variable name "guesses" is better named as "lettersGuessed"
//variable name "goodGuess" is better named as "correctGuess"
//method name checkForEndState() is better named as checkCurrentGameState()
//method name isGoodGuess(char guess) is better named as isCorrectGuess(char guess)
//Comments for enhancements:
//When user enters a letter to guess for more than once, display a message telling that such letter has
//been guessed previously.
//"Enter next guess:" is better modified as "Enter a letter to guess:"
//The game should only allow user to enter a single alphabetical letter at once, instead of merely getting the first character
//entered from more than one character in the string. For example: 'a' is allowed, but 'ac' is not allowed.
public class Hangman extends AbstractRandomWordGame {
    private List<Character> lettersGuessed;
    private String wordToGuess;
    private int wrongLetterGuessed;
    private String wordProgress;
    private boolean gameRunning;

    private static final String[] hangman = {
            "",
            "_________%n",
            " |%n |%n |%n |%n_|_______%n",
            " ______%n |/%n |%n |%n |%n_|_______%n",
            " ______%n |/   |%n |%n |%n |%n_|_______%n",
            " ______%n |/   |%n |    O%n |%n |%n_|_______%n",
            " ______%n |/   |%n |    O%n |    |%n |%n_|_______%n",
            " ______%n |/   |%n |    O%n |   /|%n |%n_|_______%n",
            " ______%n |/   |%n |    O%n |   /|\\%n |%n_|_______%n",
            " ______%n |/   |%n |    O%n |   /|\\%n |   /%n_|_______%n",
            " ______%n |/   |%n |    O%n |   /|\\%n |   / \\%n_|_______%n"
    };

    public Hangman(InputStream input, PrintStream output) {
        this(input, output, null, null);
    }

    public Hangman(InputStream input, PrintStream output, Path wordsFilePath) {
        this(input, output, wordsFilePath, null);
    }

    public Hangman(InputStream input, PrintStream output, Path wordsFilePath, Charset wordsFileCharset) {
        super(input, output, wordsFilePath, wordsFileCharset);
    }

    public void play() {
        initialiseGameState();
        mainLoop();
    }

    private void initialiseGameState() {
    	lettersGuessed = new ArrayList<Character>();
        wordToGuess = getRandomWord();
        wrongLetterGuessed = 0;
        wordProgress = getCensoredAnswer(wordToGuess);
        gameRunning = true;
    }

    private void mainLoop() {
        while (gameRunning) {
            showGameBoard();
            askUserToGuess();
            char guess = getUserGuess();
            updateGameState(guess);
            checkForEndState();
        }
        showGameBoard();//once the loop has ended show the final state to the user
        showEndGameMessage();
    }

    private void updateGameState(char guess) {
        if (isDuplicateGuess(guess)) {
            return;
        }

        boolean goodGuess = isGoodGuess(guess);
        if (goodGuess) {
            wordProgress = updateWordProgress(guess);
        } else {
        	wrongLetterGuessed++;
        	lettersGuessed.add(guess);
        }
    }

    private boolean isDuplicateGuess(char guess) {
        return isDuplicateWrongGuess(guess) || isDuplicateCorrectGuess(guess);
    }

    private boolean isDuplicateWrongGuess(char guess) {
        char upper = Character.toUpperCase(guess);
        char lower = Character.toLowerCase(guess);
        return lettersGuessed.contains(upper) || lettersGuessed.contains(lower);
    }

    private boolean isDuplicateCorrectGuess(char guess) {
        char candidate = Character.toLowerCase(guess);

        //the letters in wordProgress are always lower case. This is enforced in the updateWordProgress() method
        return wordProgress.indexOf(candidate) != -1;
    }

    private void askUserToGuess() {
		output.println();
        output.print("Enter next guess: ");
    }

    private String getCensoredAnswer(String answer) {
        return answer.replaceAll(".", "-");
    }

    private void showGameBoard() {
        String string = "WORD:\t\t\tWRONG GUESSES:%n%s\t\t\t%s%n%n" + hangman[wrongLetterGuessed];
        output.format(string, wordProgress, lettersGuessed);
    }

    private char getUserGuess() {
        String s = input.nextLine();
        return s.charAt(0);
    }

    private boolean isGoodGuess(char guess) {
        String candidate = "" + guess;
        return wordToGuess.toLowerCase().contains(candidate.toLowerCase());
    }

    private String updateWordProgress(char guess) {
        String lowerCaseAnswer = wordToGuess.toLowerCase();
        char lowerCaseGuess = Character.toLowerCase(guess);
        StringBuilder builder = new StringBuilder(wordProgress);
        int index = lowerCaseAnswer.indexOf(lowerCaseGuess);
        while(index >= 0) {
            char answerCharacter = wordToGuess.charAt(index);
            builder.setCharAt(index, answerCharacter);
            index = lowerCaseAnswer.indexOf(lowerCaseGuess, index+1);
        }

        return builder.toString();
    }

    private void checkForEndState() {
        if (userHasGuessedTheWord() || userHasUsedAllTheirGuesses()) {
            gameRunning = false;
        }
    }

    private boolean userHasGuessedTheWord() {
        return wordToGuess.equalsIgnoreCase(wordProgress);
    }

    private boolean userHasUsedAllTheirGuesses() {
        return wrongLetterGuessed == hangman.length-1;
    }

    private void showEndGameMessage() {
        if (wordToGuess.equalsIgnoreCase(wordProgress)) {
            output.println("You won! The word was " + wordToGuess + ". Congratulations.");
        } else {
            output.println("You lose. Better luck next time!");
        }
    }

    public static void main(String[] args) {
        Hangman hangman = new Hangman(System.in, System.out);
        hangman.play();
    }
}

