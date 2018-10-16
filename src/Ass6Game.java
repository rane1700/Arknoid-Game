import game.PlayGame;

/**
 * *This class starts a new game and makes him work by calls to another function.
 *
 * @author Ran
 */
public class Ass6Game {
    /**
     * Main function.
     *
     * @param args no arguments are taken
     */
    public static void main(String[] args) {
        String pathToLevelSet;
        //List<LevelSet> listOfSets;
        if (args.length == 0) {
            pathToLevelSet = "level_sets.txt";
        } else {
            pathToLevelSet = args[0];
        }
        PlayGame play = new PlayGame(pathToLevelSet);
        play.startGame();
    }
}

