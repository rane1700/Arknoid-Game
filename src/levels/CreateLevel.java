package levels;

import game.Velocity;
import graphics.Block;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class creates a level from given level details.
 */
public class CreateLevel  {
    private List<String> level;
    private TreeMap<String, String> map;
    private BlocksFromSymbolsFactory factory;

    /**
     * Constructor.
     * @param levelString List of strings containing level specification.
     */
    public CreateLevel(List<String> levelString) {
        this.level = new ArrayList<>();
        this.level = levelString;
        this.map = new TreeMap<String, String>();
        seperteLevelDetail();
        // getValuesFromMap();
        setBlocks();
    }
    /**
     * This method gathers the information I arranged.
     * Generates a level information object out of it.
     * @return LevelInformation object
     */
    public LevelInformation getLevelInformation() {
        String levelName = this.map.get("level_name");
        List<Velocity> intiallBallsVeloc = getBallsVelocities();
        int paddleSpeed = Integer.parseInt(this.map.get("paddle_speed"));
        int paddleWidth = Integer.parseInt(this.map.get("paddle_width"));
        int numOfBlocks = Integer.parseInt(this.map.get("num_blocks"));
        int numOfBalls = getNumOfBalls();
        List<Block> blockList = blocksForLevel();
        Image image = null;
        String backgroundString = this.map.get("background");
        if (isBackgroundApicture()) {
            try {
                backgroundString = backgroundString.replaceAll("[()]", "");
                backgroundString = backgroundString.substring(5);
                InputStream imageIS = ClassLoader.getSystemClassLoader().getResourceAsStream(backgroundString);
                image = ImageIO.read(imageIS);
                LevelInformationGenerator levelInformationGenerator = new LevelInformationGenerator(numOfBalls,
                        intiallBallsVeloc, paddleSpeed, paddleWidth,
                        levelName, blockList, numOfBlocks);
                 levelInformationGenerator.setLevelBackground(image);
                return levelInformationGenerator;
            } catch (IOException e) {
                System.err.println("Couldnt read background image");
                e.printStackTrace();
            }
        }
        LevelInformationGenerator levelInformationGenerator = new LevelInformationGenerator(numOfBalls,
                intiallBallsVeloc, paddleSpeed, paddleWidth,
                levelName, blockList, numOfBlocks);
        levelInformationGenerator.setLevelBackground(new ColorParser().matchFill(backgroundString));
        return levelInformationGenerator;
    }
    /**
     * Method to get the velocities provided and put them into velocites object list.
     * @return Velocity list
     */
    private List<Velocity> getBallsVelocities() {
        List<Velocity> velocList = new ArrayList<>();
        String velocitis = this.map.get("ball_velocities");
        String[] pairs = velocitis.split("\\s+");
        String[] velocPairs;
        for (int i = 0; i < pairs.length; i++) {
            velocPairs  = pairs[i].split(",");
            velocList.add(new Velocity(Integer.parseInt(velocPairs[0]), Integer.parseInt(velocPairs[1])));
        }
        return velocList;
    }

    /**
     * Method to return number of balls in level.
     * @return number of balls in level.
     */
    private int getNumOfBalls() {
        return getBallsVelocities().size();
    }
    /**
     * Method to check if i was asked to make an image background.
     * @return True if image  - false if not
     */
    private boolean isBackgroundApicture() {
        String imageString = this.map.get("background");
        return imageString.contains("image");
        /*if (!imageString.contains("image")) {
            return false;
        } else {
            return true;
        }*/
    }

    /**
     * Create the blocks in level.
     * @return List of blocks
     */
    private List<Block> blocksForLevel() {
        ArrayList<String> listOfBlocksAndSpacersSymbols = new ArrayList<String>();
        boolean flag = false;
        for (int i = 0; i < this.level.size(); i++) {
            if (this.level.get(i).startsWith("END_BLOCKS")) {
                flag = false;
            }
            if (flag) {
                listOfBlocksAndSpacersSymbols.add(this.level.get(i));
            }
            if (this.level.get(i).startsWith("START_BLOCKS")) {
                flag = true;
            } else if (this.level.get(i).startsWith("END_BLOCKS")) {
                flag = false;
            }
        }
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = this.factory;
        List<Block> blocksList = new ArrayList<>();
        int startPointX = Integer.parseInt(this.map.get("blocks_start_x"));
        int startPointY = Integer.parseInt(this.map.get("blocks_start_y"));
        String[] sArray;
        for (int i = 0; i < listOfBlocksAndSpacersSymbols.size(); i++) {
            sArray = listOfBlocksAndSpacersSymbols.get(i).split("");
            for (int j = 0; j < sArray.length; j++) {
                if (sArray[j].equals("")) {
                    continue;
                }
                if (blocksFromSymbolsFactory.isBlockSymbol(sArray[j])) {
                    blocksList.add(blocksFromSymbolsFactory.getBlock(sArray[j], startPointX, startPointY));
                    startPointX += blocksFromSymbolsFactory.getBlock(sArray[j], startPointX, startPointY)
                            .getCollisionRectangle().getWidth();
                } else if (blocksFromSymbolsFactory.isSpaceSymbol(sArray[j])) {
                    startPointX += blocksFromSymbolsFactory.getSpaceWidth(sArray[j]);
                }
            }
            startPointY += Integer.parseInt(this.map.get("row_height"));
            startPointX = Integer.parseInt(this.map.get("blocks_start_x"));
        }
        return blocksList;
    }

    /**
     *Method to make the string list specification for a level appear in a map form.
     */
    private void seperteLevelDetail() {
        String line;
        final int lhs = 0;
        final int rhs = 1;
        for (int i = 0; i < level.size(); i++) {
            if (!level.get(i).contains(":")) {
                break;
            }
            String[] pair = level.get(i).trim().split(":");
            this.map.put(pair[lhs].trim(), pair[rhs].trim());
        }
    }

    /**
     *Method to add block definitions to block factory.
     */
    private void setBlocks() {
        InputStream inputStream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(this.map.get("block_definitions"));
        BlocksDefinitionReader defReader = new BlocksDefinitionReader();
        this.factory = defReader.fromReader(new InputStreamReader(inputStream));
    }
}
