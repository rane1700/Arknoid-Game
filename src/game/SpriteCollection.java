package game;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * This class makes actions on the sprite objects list- add objects and on the
 * objects that find in the list- draw and notify that time passed.
 */
public class SpriteCollection {
    // list of sprite objects
    private List<Sprite> spriteObjects;

    /**
     * define the constructor of SpriteCollection.
     */
    public SpriteCollection() {
        this.spriteObjects = new ArrayList<>();
    }

    /**
     * This method receives a new sprites.
     * adds it to the sprite objects list
     *
     * @param s sprite object to add
     */
    public void addSprite(Sprite s) {
        this.spriteObjects.add(s);
    }

    /**
     * This method removes the sprite object from the game.
     *
     * @param s sprite object to remove
     */
    public void removeSprite(Sprite s) {
        this.spriteObjects.remove(s);
    }

    /**
     * This method call timePassed() on all sprites.
     * @param dt the dt parameter of speed
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < spriteObjects.size(); i++) {
            spriteObjects.get(i).timePassed(dt);
        }
    }

    /**
     * This method call drawOn on all sprites.
     *
     * @param d DrawSurface d
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spriteObjects.size(); i++) {
            spriteObjects.get(i).drawOn(d);
        }
    }
}
