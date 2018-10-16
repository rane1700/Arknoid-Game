package game;


import java.io.FileOutputStream;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is for creates an empty high-scores table with the specified size,
 * (the size means that the table holds up to size top scores) and for add details to
 * the table and load and save it.
 */
public class HighScoresTable {

    private int size;
    private List<ScoreInfo> highScore;

    /**
     * This method returns the size of the table.
     *
     * @param size the size of the table
     */
    public HighScoresTable(int size) {
        this.size = size;
        highScore = new ArrayList<ScoreInfo>();
    }

    /**
     * This method read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename the file that save the table
     * @return the current high scores table
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable highScoresTab = new HighScoresTable(5);
        try {
            highScoresTab.load(filename);
            return highScoresTab;
        } catch (Exception e) {
            return highScoresTab;
        }
    }

    /**
     * This method is for add a high-score to the table.
     *
     * @param score parameter from ScoreInfo type for add.
     */
    public void add(ScoreInfo score) {
        int indexPlace;
        if (getRank(score.getScore()) <= this.size) {
            indexPlace = (getRank(score.getScore()) - 1);
            highScore.add(indexPlace, score);
        }
        if (highScore.size() > this.size) {
            highScore.remove(highScore.size() - 1);
        }
    }

    /**
     * this method returns table size.
     *
     * @return the table size
     */
    public int size() {
        return this.size;
    }

    /**
     * This method returns the current high scores.
     * The list is sorted such that the highest
     * scores come first.
     *
     * @return sorted list of the current high scores
     */
    public List<ScoreInfo> getHighScores() {
        return highScore;
    }

    /**
     * This method returns the rank of the current score: where will it
     * be on the list if added?
     *
     * @param score the value of the score that the player achieve
     * @return the rank of the player's score
     */
    public int getRank(int score) {
        int rank = 1;
        for (int i = 0; i < this.highScore.size(); i++) {
            if (score <= this.highScore.get(i).getScore()) {
                rank++;
            } else {
                break;
            }
        }
        return rank;
    }

    /**
     * This method clears the table.
     */
    public void clear() {
        highScore.clear();
    }

    /**
     * This method load table data from file.
     * Current table data is cleared.
     *
     * @param filename the file that save the table.
     * @throws IOException notify the user that something went wrong
     */
    public void load(File filename) throws IOException {
        this.clear();
        BufferedReader is = null;
        try {
            is = new BufferedReader(// wrapper that reads ahead
                    new InputStreamReader(// wrapper that reads characters
                            new FileInputStream(filename)));
            String playerScore;
            // null - no more data in the stream
            while ((playerScore = is.readLine()) != null) {
                String[] scores = playerScore.split(":");
                highScore.add(new ScoreInfo(scores[0], Integer.parseInt(scores[1])));
            }
        } catch (IOException e) {
            System.out.println(" Something went wrong while reading !");
        } finally {
            if (is != null) { // Exception might have happened at constructor
                try {
                    is.close(); // closes FileInputStream too
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
        }

    }

    /**
     * This method saves table data to the specified file.
     *
     * @param filename the file that save the table
     * @throws IOException notify the user that something went wrong
     */
    public void save(File filename) throws IOException {
        PrintWriter os = null;
        try {
            os = new PrintWriter(// wrapper with many ways of writing strings
                    new OutputStreamWriter(// wrapper that can write strings
                            new FileOutputStream(filename)));
            for (ScoreInfo oneScore : highScore) {
                os.printf("%s:%d\n", oneScore.getName(), oneScore.getScore());
            }
        } catch (IOException e) {
            System.out.println(" Something went wrong while writing !");
        } finally {
            if (os != null) { // Exception might have happened at constructor
                os.close(); // closes fileOutputStream too
            }
        }
    }
}

