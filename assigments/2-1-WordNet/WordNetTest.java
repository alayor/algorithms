import java.util.*;

public class WordNetTest {

    public static void runTests() {
        testNouns();
        testIsNoun();
    }

    private static void testNouns() {
        WordNet wordNet = new WordNet("test-files/synsets.txt", "test-files/hypernyms.txt");
        Iterator<String> iterator = wordNet.nouns().iterator();
        Set<String> nouns = new HashSet<String>();
        while(iterator.hasNext()) {
            nouns.add(iterator.next());
        }
        assert nouns.size() == 4;
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
        assert wordNet.isNoun("1750ss") == false;
    }
}