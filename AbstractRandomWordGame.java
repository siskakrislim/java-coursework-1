//Name : Siska Kristanti Lim
//Student number : 170281939
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class AbstractRandomWordGame {
    protected List<String> words;
    protected Random random;
    protected Scanner input;
    protected PrintStream output;
    protected static final Path defaultWordsFilePath = Paths.get("gamedictionary.txt");
    protected static final Charset defaultWordsFileCharset = Charset.forName("UTF-8");
    protected Path wordsFilePath;
    protected Charset wordsFileCharset;

    public AbstractRandomWordGame(InputStream input, PrintStream output) {
        this(input, output, null, null);
    }

    public AbstractRandomWordGame(InputStream input, PrintStream output, Path wordsFilePath) {
        this(input, output, wordsFilePath, null);
    }

    public AbstractRandomWordGame(InputStream input, PrintStream output, Path wordsFilePath, Charset wordsFileCharset) {
        this.input = new Scanner(input);
        this.output = output;
        this.wordsFilePath = wordsFilePath;
        this.wordsFileCharset = wordsFileCharset;
        random = new Random();
        loadWords();
    }

    //TODO: It is better to make it stay as private, because loadWords() is accessed directly by other method
    //which is within the class itself, and it does not affect any of its subclass, Hangman or WordSleuth.
    //And in terms of game security, it is better to read text file from the superclass itself so that any
    //unauthorised user cannot simply see or modify the words inside the text file.
    //TODO: rename to tell of side effects
    private void loadWords() {
        if (wordsFilePath == null) wordsFilePath = defaultWordsFilePath;
        if (wordsFileCharset == null) wordsFileCharset = defaultWordsFileCharset;

        try {
            words = Files.readAllLines(wordsFilePath, wordsFileCharset);
        } catch (IOException e) {
            System.err.println("Error reading file '" + wordsFilePath + "'");
            //default word list
            words = Arrays.asList("compilation", "popstar", "symphony");
            //words = getHardcodedWordList();
            //Comments: when the system fails to read the file, it is supposed to just end the game, instead
            //of giving only few hard coded words, as these words are to be repeated in a high frequency when the
            //user plays many times already. It will degrade the true meaning of the game, its quality, and
            //user would feel the game is boring. It is for the purpose of maintaining the entertainment it has.
        }
    }

    protected String getRandomWord() {
        int r = random.nextInt(words.size());
        return words.get(r);
    }

    abstract void play();
}
