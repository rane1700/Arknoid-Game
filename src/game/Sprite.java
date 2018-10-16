package game;

import biuoop.DrawSurface;


/**
 * The interface of sprite (include Ball,Block,Paddle..).
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d DrawSurface on gui
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     *
     * @param dt the dt parameter of speed
     */
    void timePassed(double dt);


    /**
     * add the sprite to the game.
     *
     * @param g GameLevel parameter
     */
    void addToGame(GameLevel g);
}
