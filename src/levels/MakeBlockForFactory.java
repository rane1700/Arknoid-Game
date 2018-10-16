package levels;

import graphics.Block;
import graphics.Point;
import graphics.Rectangle;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class to generate proper block from given details.
 */
public class MakeBlockForFactory implements BlockCreator {
    private int blockWidth;
    private int hitPoints;
    private int blockHeight;
    private String symbol;
    private List<String> blocksListString;
    private TreeMap<String, String> blocksMap;
    private TreeMap<Integer, String> blockFill;
    //map images fills for block hit points
    private TreeMap<Integer, Image> imageFill;
    //map colors fills for block hit points
    private TreeMap<Integer, Color> colorFill;

    /**
     * Constructor.
     * @param symbolOfBlock symbol of the block.
     * @param blocksList list of the block infrastructure.
     */
    public MakeBlockForFactory(String symbolOfBlock, List<String> blocksList) {
        symbol = symbolOfBlock;
        blocksListString = blocksList;
        blocksMap = new TreeMap<>();
        blockFill = new TreeMap<>();
        imageFill = new TreeMap<>();
        colorFill = new TreeMap<>();
        //make a map with specification details
        prepareSymbolDefinition();
        //make the fill for the block
        sortFillForBlockByNumOfHits();
    }

    /**
     * This method is making a map for the creation of block.
     * which is relevant to the symbol given as parameter.
     */
    public void prepareSymbolDefinition() {
        String str = null;
        String defStr = null;
        for (int i = 0; i < blocksListString.size(); i++) {
            //add default requests to block specification
            if (blocksListString.get(i).startsWith("default")) {
                defStr = blocksListString.get(i);
            }
            //collect only the line relevant to block symbol
            if (blocksListString.get(i).startsWith("bdef symbol:" + this.symbol)) {
                str = this.blocksListString.get(i);
                break;
            }
        }
        //Split the line collected previously and organize it in a map
        str = str.substring(14);
        String[] pairs = str.split(" ");
        for (String kvPair: pairs) {
            String[] kv = kvPair.split(":");
            String key = kv[0];
            String value = kv[1];
            this.blocksMap.put(key, value);
        }
        //add the default requests to block specification
        if (defStr != null) {
            defStr = defStr.substring(8);
            String[] defPairs = defStr.split("\\s+");
            String[]defPairsSeperated;
            for (int i = 0; i < defPairs.length; i++) {
                defPairsSeperated = defPairs[i].split(":");
                if (!this.blocksMap.containsKey(defPairsSeperated[0])) {
                    this.blocksMap.put(defPairsSeperated[0], defPairsSeperated[1]);
                }
            }
        }
        if (Integer.parseInt(this.blocksMap.get("width")) > 0) {
            this.blockWidth = Integer.parseInt(this.blocksMap.get("width"));
            //Wrong input for block width
        } else {
            System.out.println("block width must be a positive integer");
            System.exit(0);
        }
        if (Integer.parseInt(this.blocksMap.get("hit_points")) > 0) {
            this.hitPoints = Integer.parseInt(this.blocksMap.get("hit_points"));
            //Wrong input for block hit points
        } else {
            System.out.println("block hit points must be a positive integer");
            System.exit(0);
        }
        if (Integer.parseInt(this.blocksMap.get("height")) > 0) {
            this.blockHeight = Integer.parseInt(this.blocksMap.get("height"));
            //Wrong input for block height
        } else {
            System.out.println("block height must be a positive integer");
            System.exit(0);
        }
    }

    /**
     * Create the block patten due to the details collected.
     * @param xpos X coordinate position for the block
     * @param ypos Y coordinate position for the block
     * @return Block Object
     */
    public Block create(int xpos, int ypos) {
        ColorParser colorParser = new ColorParser();
        Color stroke = null;
        //Check for stroke color existence
        if (this.blocksMap.get("stroke") != null) {
            stroke = colorParser.matchFill(this.blocksMap.get("stroke"));
        }
        Rectangle rect = new Rectangle(new Point(xpos, ypos), this.blockWidth, this.blockHeight);
        //fill for block
        setImageOrColorFill();
        return new Block(rect, this.imageFill, this.colorFill, this.hitPoints, stroke);
    }

    /**
     * Check if im required to fill the block with image.
     * If so, add the images to a map.
     */
    public void setImageOrColorFill() {
        String s = null;
        Image image;
        ColorParser colorParser = new ColorParser();
        //Iterate through block fill map
        for (Map.Entry<Integer, String> entry : this.blockFill.entrySet()) {
            //add image to map
            if (entry.getValue().contains("image")) {
                image = null;
                s = entry.getValue();
                s = s.replaceAll("[()]", "");
                s = s.substring(5);
                try {
                    InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(s);
                    image = ImageIO.read(inputStream);
                    this.imageFill.put(entry.getKey(), image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                //this is a color and add it to a map of colors
                this.colorFill.put(entry.getKey(), colorParser.matchFill(entry.getValue()));
            }
        }
    }

    /**
     * Sort the fill for a block by hit points.
     * default fill for a block is '0' if num of hits fill is not listed.
     */
    public void sortFillForBlockByNumOfHits() {
        for (Map.Entry<String, String> entry : this.blocksMap.entrySet()) {
            if (entry.getKey().toString().startsWith("fill-")) {
                this.blockFill.put(Integer.parseInt(entry.getKey().toString().replaceAll("[A-Za-z-]", "")),
                        entry.getValue().toString());
                //default fill at position '0' in map.
            } else if (entry.getKey().toString().startsWith("fill")) {
                this.blockFill.put(0, entry.getValue().toString());
            }
        }
    }

}
