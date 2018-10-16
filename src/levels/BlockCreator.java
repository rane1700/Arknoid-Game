package levels;

import graphics.Block;

/**
 * BlockCreator is an interface of a factory-object that is used for creating blocks.
 */
public interface BlockCreator {

    /**
     * Create a block at the specified location.
     * @param xpos X coordinate for block.
     * @param ypos Y coordinate for block.
     * @return Block object
     */
    Block create(int xpos, int ypos);
}
