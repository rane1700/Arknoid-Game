package graphics;

import biuoop.DrawSurface;
import game.Collidable;
import game.GameLevel;
import game.Sprite;
import game.Velocity;
import indicators.HitListener;
import indicators.HitNotifier;



//import java.awt.*;
import  java.awt.Image;
import  java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


/**
 * This class defines and creates new block, draw the block and makes the ball
 * movement.
 */


public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color;
    private String hitsNumString;
    private Integer numOfHits;
    private List<HitListener> hitListeners;
   // private Image image;
    private TreeMap<Integer, String> fillsMap;
    private Color stroke;
    private TreeMap<Integer, Image> imageFillMap;
    private TreeMap<Integer, Color> colorsFillMap;
    /**
     * Constructor.
     * @param rect rectangle shape of block
     * @param clr color of block
     * @param numOfHits number of times ball was hitted
     * @param stroke color for stroke
     */
    public Block(Rectangle rect, Color clr, Integer numOfHits, Color stroke) {
        this.rect = rect;
        this.color = clr;
        this.numOfHits = numOfHits;
        hitListeners = new ArrayList<HitListener>();
        this.fillsMap = null;
        this.stroke = stroke;
        this.colorsFillMap = null;
        this.imageFillMap = null;
    }

    /**
     * Copy constructor.
     * @param block block object to copy.
     */
    public Block(Block block) {
        this.rect = block.rect;
        this.color = block.color;
        this.numOfHits = block.numOfHits;
        hitListeners = new ArrayList<HitListener>();
        this.fillsMap = block.fillsMap;
        this.stroke = block.stroke;
        this.colorsFillMap = block.colorsFillMap;
        this.imageFillMap = block.imageFillMap;
    }

    /**
     * Constructor with specified fills for block due to block's num of hits.
     * @param rect Block rectangle
     * @param imageFill block image fill map
     * @param colorFill block color fill mao
     * @param numOfHits block num of hits
     * @param stroke block's stroke
     */
    public Block(Rectangle rect, TreeMap<Integer, Image> imageFill, TreeMap<Integer, Color> colorFill,
                 Integer numOfHits, Color stroke) {
        this.rect = rect;
        this.imageFillMap = imageFill;
        this.colorsFillMap = colorFill;
        this.numOfHits = numOfHits;
        hitListeners = new ArrayList<HitListener>();
        this.stroke = stroke;
    }

    /**
     * This method add a hit to the list of the ball's hits.
     *
     * @param hl the hit to add.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * This method removes a hit to the list of the ball's hits.
     *
     * @param hl the hit to remove.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * This method notify when the ball hit a block.
     *
     * @param hitter the ball that hit the points
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * This is a list of points the ball hits.
     *
     * @return list of hit points.
     */
    public List<HitListener> getHitPoints() {
        return this.hitListeners;
    }

    /**
     * Method to return rectangle.
     *
     * @return rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Method to change velocity when ball hits the block.
     *
     * @param hitter bla
     * @param collisionPoint  where ball collides with rectangle
     * @param currentVelocity velocity of hitting ball
     * @return new velocity after hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity veloc;
        // Check if the hit is on corner
        if (((collisionPoint.getX() == rect.getLowerLeft().getX())
                && (collisionPoint.getY() == rect.getLowerLeft().getY()))
                || ((collisionPoint.getX() == rect.getLowerRight().getX())
                && (collisionPoint.getY() == rect.getLowerRight().getY()))
                || ((collisionPoint.getX() == rect.getUpperLeft().getX())
                && (collisionPoint.getY() == rect.getUpperLeft().getY()))
                || ((collisionPoint.getX() == rect.getUpperRight().getX())
                && (collisionPoint.getY() == rect.getUpperRight().getY()))) {
            veloc = new Velocity(-1 * currentVelocity.getDx(), -1 * currentVelocity.getDy());
            // ball hit the left side of block
        } else if (collisionPoint.getX() == rect.getLeftLine().getStart().getX()) {
            veloc = new Velocity(-1 * Math.abs(currentVelocity.getDx()), currentVelocity.getDy());
            // ball hits right side of block
        } else if (collisionPoint.getX() == rect.getRightLine().getStart().getX()) {
            veloc = new Velocity(Math.abs(currentVelocity.getDx()), currentVelocity.getDy());
            // ball hits upper side of block
        } else if (collisionPoint.getY() == rect.getUpperLine().getStart().getY()) {
            veloc = new Velocity(currentVelocity.getDx(), -1 * Math.abs(currentVelocity.getDy()));
            // ball hits lower side of block
        } else {
            veloc = new Velocity(currentVelocity.getDx(), Math.abs(currentVelocity.getDy()));
        }
        if (numOfHits != null && numOfHits > 0) {
            // reduce number of hits permitted on block
            this.numOfHits -= 1;
        }
        this.notifyHit(hitter);
        // return new velocity
        return veloc;
    }

    /**
     * Method to draw the block.
     *
     * @param surface gui surface
     */
    public void drawOn(DrawSurface surface) {
        fillForBlock(surface);
        if (this.stroke != null) {
            surface.setColor(this.stroke);
            surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                    (int) this.getCollisionRectangle().getUpperLeft().getY(),
                    (int) this.getCollisionRectangle().getWidth(),
                    (int) this.getCollisionRectangle().getHeight());
        }
    }

    /**
     * Methdd to draw the inner fill for block - image or color.
     * @param surface surface of GUI
     */
    private void fillForBlock(DrawSurface surface) {
        //This is a simple block with no special fill
        if (this.colorsFillMap == null && this.imageFillMap == null) {
            surface.setColor(this.color);
            surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                    (int) this.rect.getWidth(), (int) this.rect.getHeight());

        //There is a color fill demand for current block num of hits.
        } else if (this.colorsFillMap != null && this.colorsFillMap.containsKey(this.numOfHits)) {
            surface.setColor(this.colorsFillMap.get(this.numOfHits));
            surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                    (int) this.rect.getWidth(), (int) this.rect.getHeight());
            //There is an image fill demand for current block num of hits.
        } else if (this.imageFillMap.containsKey(this.numOfHits)) {
            surface.drawImage((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                    this.imageFillMap.get(this.numOfHits));
        } else {
            //Address the default num of hits fill request - at position '0'.
            if (this.colorsFillMap != null && this.colorsFillMap.containsKey(0)) {
                surface.setColor(this.colorsFillMap.get(0));
                surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                        (int) this.rect.getWidth(), (int) this.rect.getHeight());
            } else {
                surface.drawImage((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                        this.imageFillMap.get(0));
            }
        }
    }
    /**
     * Empty method.
     * @param dt the dt parameter of speed
     */
    public void timePassed(double dt) {
    }

    /**
     * Method to add block to game.
     *
     * @param g is the game variable
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Removing sprite from game.
     *
     * @param gameLevel bla
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * This method returns the number of the times that the block hatted.
     *
     * @return number of the events that it's happened
     */
    public Integer getNumOfHits() {
        return this.numOfHits;
    }

}
