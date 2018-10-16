package game;

/**
 * This class is represents the object that will
 * save in the high scores table- the name of the user and the
 * score that achieved.
 */
public class ScoreInfo {

    private String name;
    private int score;

    /**
     * This is the constructor of the ScoreInfo class.
     *
     * @param name  the name of the player
     * @param score the score of the player
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * This method return the player's name.
     *
     * @return the player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * This method return the player's score.
     *
     * @return the player's score.
     */
    public int getScore() {
        return score;
    }

}
