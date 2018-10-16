package levels;

import java.util.List;
import java.util.TreeMap;

/**
 * Class to gather information about spacer specification.
 */
public class SpacersBySymbol {
    private int spacerWidth;
    private String symbol;
    private List<String> spacerListString;
    private TreeMap<String, String> spacerMap;

    /**
     * Constructor.
     * @param symbolOfSpacer symbol of spacer.
     * @param spacerList list of spacers.
     */
    public SpacersBySymbol(String symbolOfSpacer, List<String> spacerList) {
        symbol = symbolOfSpacer;
        spacerListString = spacerList;
        spacerMap = new TreeMap<>();
        prepareSymbolDefinition();

    }

    /**
     * Take the relevant spacer line and pull out its width to a map.
     */
    public void prepareSymbolDefinition() {
        String str = null;
        for (int i = 0; i < spacerListString.size(); i++) {
            if (spacerListString.get(i).startsWith("sdef symbol:" + this.symbol)) {
                str = this.spacerListString.get(i);
                break;
            }
        }
        //Split the string so it will fit a map.
        String[] pairs = str.split(":");
            this.spacerMap.put("width", pairs[2]);
        if (Integer.parseInt(this.spacerMap.get("width")) > 0) {
            this.spacerWidth = Integer.parseInt(this.spacerMap.get("width"));
        } else {
            System.out.println("spacer width must be positive");
            System.exit(0);
        }

    }

    /**
     * Get width of spacer.
     * @return spacer width.
     */
    public int getWidth() {
        return this.spacerWidth;
    }

}
