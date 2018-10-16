package levels;



import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;

/**
 * Class responsible for reading set details and providing the sufficient information for GUI.
 */
public class ArrangeLevelsSets {
    /**
     * Method user Reader and read sets path's and from these paths creates levels.
     * @param reader java.io.Reader
     * @return List of sets containing levels
     */
    public  List<LevelSet> fromReader(Reader reader) {
        List<LevelSet> levelSets = new ArrayList<>();
        String data;
        LineNumberReader numberReader = new LineNumberReader(reader);

        List<LevelInformation> levelsList = new ArrayList<>();
        String[] setsPais = null;
        try {
            while ((data = numberReader.readLine()) != null) {
                if (numberReader.getLineNumber() % 2 == 0) {
                    InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(data);
                    LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
                    levelsList = levelSpecificationReader.fromReader(new InputStreamReader(inputStream));
                    levelSets.add(new LevelSet(setsPais[0], setsPais[1], levelsList));
                } else {
                    setsPais = data.split("[:]");
                }
            }
        } catch (IOException e) {
            System.err.println("Oops! Couldn't read level set definition");
            e.printStackTrace();
        } finally {
            try {
                numberReader.close();
            } catch (IOException e) {
                System.err.println("Couldn't close input Stream");
                e.printStackTrace();
            }
        }

        return levelSets;
    }
}
