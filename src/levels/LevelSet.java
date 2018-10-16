package levels;

import java.util.List;

/**
 * Class to define the properties of a set.
 */
public class LevelSet {
    private String symbol;
    private String setName;
    private List<LevelInformation> levels;

    /**
     * Constructor.
     * @param symbol symbol of set.
     * @param setName name of set.
     * @param levels list of levels in set.
     */
    public LevelSet(String symbol, String setName, List<LevelInformation> levels) {
        this.symbol = symbol;
        this.setName = setName;
        this.levels = levels;
    }

    /**
     * Return name of set.
     * @return name of set.
     */
    public String getSetName() {
        return this.setName;
    }

    /**
     * Return symbol of set.
     * @return symbol of set.
     */
    public String getSetSymbol() {
        return this.symbol;
    }

    /**
     * Return list of levels in set.
     * @return levels in set
     */
    public List<LevelInformation> getLevelsForSet() {
        return this.levels;
    }

}
