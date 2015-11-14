import java.util.*;

public class WordNetTest {

    public static void runTests() {
        testNouns();
        testIsNoun();
        testDistance();
        testAncestor();
    }

    private static void testNouns() {
        WordNet wordNet = new WordNet("test-files/synsets.txt", "test-files/hypernyms.txt");
        Iterator<String> iterator = wordNet.nouns().iterator();
        Set<String> nouns = new HashSet<String>();
        while(iterator.hasNext()) {
            nouns.add(iterator.next());
        }
        assert nouns.size() == 5;
        assert nouns.contains("'hood");
        assert nouns.contains("hola");
        assert nouns.contains("15_May_Organization");
        assert nouns.contains("1750s");
    }

    private static void testIsNoun() {
        WordNet wordNet = new WordNet("test-files/synsets.txt", "test-files/hypernyms.txt");
        assert wordNet.isNoun("1750s");
        assert wordNet.isNoun("hola");
        assert wordNet.isNoun("15_May_Organization");
        assert wordNet.isNoun("'hood");
        assert wordNet.isNoun("Alonso");
        assert wordNet.isNoun("1750ss") == false;
    }

    private static void testDistance() {
        WordNet wordNet = new WordNet("wordnet/synsets.txt", "wordnet/hypernyms.txt");
        double distance = wordNet.distance("worm", "bird");
        assert distance == 12 : distance;

        distance = wordNet.distance("white_marlin", "mileage");
        assert distance == 23 : distance;

        distance = wordNet.distance("Black_Plague", "black_marlin");
        assert distance == 33 : distance;

        distance = wordNet.distance("American_water_spaniel", "histology");
        assert distance == 32 : distance;

        distance = wordNet.distance("Brown_Swiss", "barrel_roll");
        assert distance == 29 : distance;
    }

    private static void testAncestor() {
        WordNet wordNet = new WordNet("wordnet/synsets.txt", "wordnet/hypernyms.txt");
        String ancestor = wordNet.sap("worm", "bird");
        assert ancestor.equals("physical_entity") : ancestor;
    }
}