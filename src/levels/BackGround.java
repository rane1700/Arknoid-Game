package levels;

import biuoop.DrawSurface;
import game.GameConst;
import game.GameLevel;
import game.Sprite;
import java.awt.Color;
import java.awt.Image;


/**
 * Class to create the background of the GUI.
 */
public class BackGround extends GameConst implements Sprite  {
    private Image image;
    private Color color;

    /**
     * Constructor.
     * @param img image for background
     */
    public BackGround(Image img) {
        this.image = img;
        this.color = null;
    }

    /**
     * Constructor.
     * @param clr color for background.
     */
    public BackGround(Color clr) {
        this.color = clr;
        this.image = null;
    }
    @Override
    public void drawOn(DrawSurface d) {
        //draw image
        if (this.image != null) {
            d.drawImage(0, 0, this.image);
            //draw color
        } else {
            d.setColor(this.color);
            d.fillRectangle(0, 0, GUI_WIDTH, GUI_HEIGHT);
        }
    }

    @Override
    public void timePassed(double dt) {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
