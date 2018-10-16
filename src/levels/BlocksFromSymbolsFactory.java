package levels;

import graphics.Block;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Ran on 01/06/2016.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Constructor.
     */
    public BlocksFromSymbolsFactory() {
        this.blockCreators = new TreeMap<>();
        this.spacerWidths = new TreeMap<>();
    }

    /**
     * returns true if 's' is a valid space symbol.
     * @param s space symbol
     * @return returns true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
        //return !s.matches("[a-zA-z]+");
    }

    /**
     * returns true if 's' is a valid block symbol.
     * @param s block symbol.
     * @return returns true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
        //return s.matches("[a-zA-z]+");
    }

    /**
     * Return a block according to the definitions associated.
     // with symbol s. The block will be located at position (xpos, ypos).
     * @param s block symbol
     * @param xpos X coordinate
     * @param ypos Y coordinate
     * @return Block object
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return blockCreators.get(s).create(xpos, ypos);
    }

    /**
     *  Add block to blocks map.
     * @param symbol block symbol.
     * @param creator BlockCreator interfaced object
     */
    public void addToBlockMap(String symbol, BlockCreator creator) {
        this.blockCreators.put(symbol, creator);
    }

    /**
     * Add spacer to blocks map.
     * @param spacer spacer symbol.
     * @param width spacer width.
     */
    public void addSpacer(String spacer, int width) {
        this.spacerWidths.put(spacer, width);
    }

    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     * @param s spacer symbol
     * @return the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

}
