package levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class accepts a reader as a parameter.
 * It returns respectively a list of levels out of this reader.
 */
public class LevelSpecificationReader {
    //List of the levels as strings.
    private ArrayList<ArrayList<String>> listOLists = new ArrayList<ArrayList<String>>();
    //list of the levels as Level Information Objects
    private List<LevelInformation> levels;
    /**
     * Consturctor.
     */
    public LevelSpecificationReader() {
        listOLists = new ArrayList<>();
        levels = new ArrayList<>();
    }

    /**
     * Method which gets a reader and returns list of levels.
     * @param reader to read from text file
     * @return Level Information list.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        addToLevelList(reader);
        for (int i = 0; i < listOLists.size(); i++) {
            this.levels.add(new CreateLevel(listOLists.get(i)).getLevelInformation());
        }
        return this.levels;
    }

    /**
     * Adding the details from the text file into an array of arrays of strings.
      * @param reader to read from text file.
     */
    public void addToLevelList(java.io.Reader reader) {
        String data;
        ArrayList<String> level = new ArrayList<String>();
        BufferedReader br = new BufferedReader(reader);
        try {
                while ((data = br.readLine()) != null) {
                    //Insignificant data
                    if (data.equals("")) {
                        continue;
                    }
                    if (!(data.matches("END_LEVEL")) && !data.equals("START_LEVEL")) {
                        if (!data.startsWith("#") && !data.isEmpty()) {
                            level.add(data);
                        }
                        //End of reading for level.
                    } else if (data.matches("END_LEVEL")) {
                        this.listOLists.add(new ArrayList<String>(level));
                        level.clear();
                    }
                }
        } catch (IOException e) {
            System.err.println("Could not read file.");
            e.printStackTrace();
        } finally {
            try {
                //close the reader
                br.close();
            } catch (IOException e) {
                System.err.println("Could not close reader.");
                e.printStackTrace();
            }
        }
    }

}
