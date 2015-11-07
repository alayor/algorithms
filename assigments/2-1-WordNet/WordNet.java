import edu.princeton.cs.algs4.In;
import java.util.*;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private final int ID_INDEX = 0;
    private final int SYNSET_INDEX = 1;
    Digraph _digraph;
    Map<String, List<Integer>> _nounSynsets = new HashMap<String, List<Integer>>();
    int _synsetsCount;

    public WordNet(String synsets, String hypernyms) {
        In synsetsFile = new In(synsets);
        In hypernymsFile = new In(hypernyms);
        fillNounSynsetsMap(synsetsFile);
        addEdgesToDigraph(hypernymsFile);
    }

    private void fillNounSynsetsMap(In synsetsFile) {
        while(synsetsFile.hasNextLine()) {
            String[] row = synsetsFile.readLine().split(",");
            addSynsetIdToNoun(row);
            _synsetsCount++;
        }
    }

    private void addSynsetIdToNoun(String[] row) {
        Integer synsetId = Integer.parseInt(row[ID_INDEX]);
        String[] nouns = row[SYNSET_INDEX].split("\\s+");
        for (String noun : nouns) {
            List<Integer> synsets = _nounSynsets.get(noun);
            if(synsets == null) synsets = new ArrayList<Integer>();
            synsets.add(synsetId);
            _nounSynsets.put(noun, synsets);
        }
    }

    private void addEdgesToDigraph(In hypernymsFile) {
        _digraph = new Digraph(_synsetsCount);
        while(hypernymsFile.hasNextLine()) {
            String[] arr = hypernymsFile.readLine().split(",");
            _digraph.addEdge(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
        }
    }

    public Iterable<String> nouns() {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return _nounSynsets.keySet().iterator();
            }
        };
    }

    public boolean isNoun(String word) {
        return _nounSynsets.containsKey(word);
    }

    public static void main(String[] args) {
        WordNetTest.runTests();
    }
}