public class OutcastTest {
    public static void runTests() {
        testOutcast();
    }

    private static void testOutcast() {
        WordNet wordnet = new WordNet("wordnet/synsets.txt", "wordnet/hypernyms.txt");
        Outcast outcast = new Outcast(wordnet);
        String[] nouns  = new String[] {"horse", "zebra", "cat", "bear", "table"};
        String outcastNoun = outcast.outcast(nouns);
        assert outcastNoun.equals("table") : outcastNoun ;

        nouns  = new String[] {"water", "soda", "bed", "orange_juice", "milk", "apple_juice", "tea", "coffee"};
        outcastNoun = outcast.outcast(nouns);
        assert outcastNoun.equals("bed") : outcastNoun ;
    }
}