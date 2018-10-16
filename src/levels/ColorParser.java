package levels;

import java.awt.Color;

/**
 * Class to parse string to a color.
 */
public class ColorParser {
    /**
     * Figure out type of color.
     * @param fill string of color
     * @return Color object.
     */
    public Color matchFill(String fill) {
        //RGB color
        if (fill.contains("RGB")) {
            return getRgbColor(fill);
        } else {
            return getColor(fill);
        }
    }

    /**
     * Get RGB type color.
     * @param fill string rgb color fill.
     * @return RGB color.
     */
    private Color getRgbColor(String fill) {
        //Toss irrelevant data.
        fill = fill.replaceAll("[A-Za-z()]", "");
        String[] values = fill.split(",");
        return new Color(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]));
    }

    /**
     * Get color from string.
     * @param fill string color fill.
     * @return Color object.
     */
    private Color getColor(String fill) {
        fill = fill.replaceAll("[()]", "");
        fill = fill.substring(5);
        if (fill.equals("black")) {
            return Color.BLACK;
        } else if (fill.equals("cyan")) {
            return Color.CYAN;
        } else if (fill.equals("yellow")) {
            return Color.yellow;
        } else if (fill.equals("red")) {
            return Color.red;
        } else if (fill.equals("blue")) {
            return Color.blue;
        } else if (fill.equals("pink")) {
            return Color.pink;
        } else if (fill.equals("orange")) {
            return Color.orange;
        } else if (fill.equals("gray")) {
            return Color.gray;
        } else if (fill.equals("green")) {
            return Color.green;
        } else {
            return Color.lightGray;
        }
    }
}
