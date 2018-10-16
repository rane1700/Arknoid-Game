package levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Class to fill blocksFromSymbolsFactory class with details.
 */
public class BlocksDefinitionReader {
    //map to arrange blocks details
    private TreeMap<String, String> blocksMap;
    //map to arrange spacer details
    private TreeMap<String, String> spacersMap;
    //block factory
    private static BlocksFromSymbolsFactory blocksFactory = new BlocksFromSymbolsFactory();

    /**
     * Constructor.
     */
    public BlocksDefinitionReader() {
        this.blocksMap = new TreeMap<>();
        this.spacersMap = new TreeMap<>();
    }

    /**
     * Method to return blocks factory after text data is taken from reader.
     * @param reader of the block definitions
     * @return factory of blocks
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BufferedReader br = new BufferedReader(reader);
        String data;
        List<String> blocks = new ArrayList<>();
        List<String> symbols = new ArrayList<>();
        List<String> blockSymbolsList;
        List<String> spacerSymbolList;
        try {
            data = br.readLine();
                while ((data = br.readLine()) != null) {
                    //ignore user notes
                        if (!data.startsWith("#") && !data.isEmpty()) {
                            //collect only relevant block or spacer data
                            if (data.startsWith("bdef symbol") || data.startsWith("default")) {
                                blocks.add(data);
                            } else if (data.startsWith("sdef symbol")) {
                                symbols.add(data);
                            }
                        }
                }
        } catch (IOException e) {
            System.err.println("Could not read file.");
            e.printStackTrace();
        } finally {
            //close reader stream
            try {
                br.close();
            } catch (IOException e) {
                System.err.println("Could not close reader.");
                e.printStackTrace();
            }
        }
        blockSymbolsList = blockSymbolsList(blocks);
        //add to factory each block symbol and its definition
        for (String s : blockSymbolsList) {
            blocksFactory.addToBlockMap(s, new MakeBlockForFactory(s, blocks));
        }
        spacerSymbolList = spacerSymbolList(symbols);
        //add to factory each spacer symbol and its definition
        for (String s : spacerSymbolList) {
            blocksFactory.addSpacer(s, new SpacersBySymbol(s, symbols).getWidth());
        }
        return blocksFactory;
    }

    /**
     * Method to take only the block symbols in block definition file.
     * @param blocks list of string of all the block infrastructure
     * @return string block symbols list
     */
    public static List<String> blockSymbolsList(List<String> blocks) {
        List<String> symbols = new ArrayList<>();
        String[] str;
        String[] str2;
        for (int i = 0; i < blocks.size(); i++) {
            //add only block symbols to list
            if (!blocks.get(i).startsWith("default")) {
                str = blocks.get(i).split(" ");
                str2 = str[1].split(":");
                //Allow only single character in block symbols
                if (str2[1].length() > 1) {
                    System.out.println("Symbol character is too long");
                    System.exit(0);
                }
                symbols.add(str2[1]);
            }
        }
        return symbols;
    }
    /**
     * Method to take only the spacers symbols in block definition file.
     * @param spacers list of string of all the spacers infrastructure
     * @return string spacers symbols list
     */
    public static List<String> spacerSymbolList(List<String> spacers) {
        List<String> symbols = new ArrayList<>();
        String[] str;
        String[] str2;
        for (int i = 0; i < spacers.size(); i++) {
            str = spacers.get(i).split(" ");
            str2 = str[1].split(":");
            //Allow only single character in spacer symbols
            if (str2[1].length() > 1) {
                System.out.println("Symbol character is too long");
                System.exit(0);
            }
            symbols.add(str2[1]);
        }
        return symbols;
    }

}
