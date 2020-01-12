//Name : Siska Kristanti Lim
//Student number : 170281939
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class WordSleuth extends AbstractRandomWordGame {

	private List<Integer> letterIndex;
	private String wordToGuess;
	private int possibleTopScore;
	private String wordProgress;
	private boolean gameRunning;
	private int totalNoOfGuesses;
	private char firstLetterOfWord;
	private int wordLength;
	private int letterCounter;
	private List<Integer> randomMemory;

	public WordSleuth(InputStream input, PrintStream output) {
		this(input, output, null, null);
	}

	public WordSleuth(InputStream input, PrintStream output, Path wordsFilePath) {
		this(input, output, wordsFilePath, null);
	}

	public WordSleuth(InputStream input, PrintStream output, Path wordsFilePath, Charset wordsFileCharset) {
		super(input, output, wordsFilePath, wordsFileCharset);
	}

	public void play() {
		initialiseGameState();
		mainLoop();
	}

	private void initialiseGameState() {
		letterIndex = new ArrayList<Integer>();
		wordToGuess = getRandomWord();
		possibleTopScore = wordToGuess.length() * 2;
		totalNoOfGuesses = getNoOfGuesses();
		firstLetterOfWord = wordToGuess.charAt(0);
		wordLength = wordToGuess.length();
		letterCounter = 0;
		wordProgress = getCensoredAnswer(wordToGuess);
		gameRunning = true;
		randomMemory = new ArrayList<Integer>();
	}

	private void mainLoop() {
		while (gameRunning) {
			if (letterCounter == 0) {
				showGameBoard();
			}
			askUserToGuess();
			String guess = getUserGuess();
			if (!(wordToGuess.equalsIgnoreCase(guess))) {
				output.println("Not the right answer");
				output.println();
			}
			updateGameState(guess);
			checkCurrentGameState();
		}
	}

	private void showGameBoard() {
		output.println("Word starts with \'" + firstLetterOfWord + "\' and is " + wordLength
				+ " letters long. You have " + totalNoOfGuesses + " guesses.");
	}

	private void askUserToGuess() {
		output.print("Enter your guess: ");
		letterCounter++;
	}

	private String getCensoredAnswer(String answer) {
		return answer.replaceAll(".", "-");
	}

	private int getNoOfGuesses() {
		if (wordToGuess.length() % 2 != 0) {
			return Math.round(wordToGuess.length() / 2 + 1) + 1;
		}
		return wordToGuess.length() / 2 + 1;
	}

	private void updateGameState(String guess) {
		if (guess.equalsIgnoreCase(wordToGuess)) {
			output.println("Congratulations! You got it right.");
			output.println("you scored " + possibleTopScore);
			output.println("Not bad.");
			gameRunning = false;
		} else {
			if (letterCounter == totalNoOfGuesses - 1) {
				output.println("FINAL CHANCE: the letters appear in the word in this order: " + getWordOrder());
				possibleTopScore -= 2;
			} else if (letterCounter == totalNoOfGuesses) {
				possibleTopScore = 0;
				gameRunning = false;
				showEndGameMessage();
			} else if (letterCounter < totalNoOfGuesses) {
				possibleTopScore--;
				output.println("Word contains the letter \'" + getRandomLetter() + "\'");
			}
		}
	}

	private String getUserGuess() {
		String s = input.nextLine();
		return s;
	}

	private void checkCurrentGameState() {
		if (userHasGuessedTheWord() || userGivenHalfLettersInWord()) {
			gameRunning = false;
		}
	}

	private boolean userHasGuessedTheWord() {
		return wordToGuess.equalsIgnoreCase(wordProgress);
	}

	private boolean userGivenHalfLettersInWord() {
		return letterCounter == totalNoOfGuesses;
	}

	private void showEndGameMessage() {
		if (wordToGuess.equalsIgnoreCase(wordProgress)) {
			output.println("Congratulations! You got it right.");
			output.println("you scored " + possibleTopScore);
			output.println("Not bad.");
		} else {
			output.println("You lose. The word was " + wordToGuess);
			output.println("you scored " + possibleTopScore);
			output.println("I'm sure you tried.");
		}
	}

	private char getRandomLetter() {
		int r = random.nextInt(wordToGuess.length()-1)+1;
		randomMemory.add(r);
		letterIndex.add(0);
		if (letterCounter == 0)
			letterIndex.add(r);
		for (int i = 0; i < letterIndex.size(); i++) {
			if (!(letterIndex.contains(r))) {
				letterIndex.add(r);
				break;
			} else {
				while(randomMemory.contains(r)) {
					r = random.nextInt(wordToGuess.length()-1)+1;
				}
			}
		}
		System.out.println();
		return wordToGuess.charAt(r);
	}

	private String getWordOrder() {
		String buffer = "";
		for (int i = 0; i < letterIndex.size() - 1; i++) {
			if (letterIndex.get(i) > letterIndex.get(i + 1)) {
				int firstIndex = letterIndex.get(i);
				int secondIndex = letterIndex.get(i + 1);
				int temp = firstIndex;
				firstIndex = secondIndex;
				secondIndex = temp;
			}
		}
		System.out.println();
		for (int i = 0; i < wordToGuess.length(); i++) {
			if (letterIndex.contains(i))
				buffer = buffer + wordToGuess.charAt(i);
		}
		return buffer;
	}

	public static void main(String[] args) {
		WordSleuth wordSleuth = new WordSleuth(System.in, System.out);
		wordSleuth.play();
	}
}
