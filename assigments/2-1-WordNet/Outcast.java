import java.util.*;

public class Outcast {

    private final WordNet _wordNet;

    public Outcast(WordNet wordNet) {
        _wordNet = wordNet;
    }

    public String outcast(String[] nouns) {
        Map<String, Integer> nounsDistance = new HashMap<String, Integer>();
        for(String noun : nouns) {
            int distance = 0;
            for(String otherNoun : nouns) {
                distance += _wordNet.distance(noun, otherNoun);
            }
            nounsDistance.put(noun, distance);
        }
        int maxDistance = 0;
        String outcast = "";
        for (String key : nounsDistance.keySet()) {
            int distance = nounsDistance.get(key);
            if (distance > maxDistance) {
                maxDistance = distance;
                outcast = key;
            }
        }
        return outcast;
    }

    public static void main(String[] args) {
        //OutcastTest.runTests();
    }
}