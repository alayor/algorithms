import java.util.*;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private final int ID_INDEX = 0;
    private final int SYNSET_INDEX = 1;
    private Digraph _digraph;
    private final Map<String, List<Integer>> _nounSynsets = new HashMap<String, List<Integer>>();
    private final Map<Integer, String> _synsets = new HashMap<Integer, String>();
    private final Map<NounPair, Integer> nounPairLength = new HashMap<NounPair, Integer>();
    private final Map<NounPair, String> nounPairAncestor= new HashMap<NounPair, String>();
    private int _synsetsCount;

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
        String synsetName = row[SYNSET_INDEX];
        _synsets.put(synsetId, synsetName);
        String[] nouns = synsetName.split("\\s+");
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
            if (arr.length > 1) {
                String[] hypernyms = arr[1].split(",");
                for (String hypernym : hypernyms) {
                    _digraph.addEdge(Integer.parseInt(arr[0]), Integer.parseInt(hypernym));
                }
            }
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

    private String getSynsetName(int synsetId) {
        return _synsets.get(synsetId);
    }

    private List<Integer> getSynsetsForNoun(String noun) {
        return _nounSynsets.get(noun);
    }

    public boolean isNoun(String word) {
        return _nounSynsets.containsKey(word);
    }

    public int distance(String nounA, String nounB) {
        NounPair nounPair = new NounPair(nounA, nounB);
        Integer minDistance = nounPairLength.get(nounPair);
        List<Integer> synsetsA = _nounSynsets.get(nounA);
        List<Integer> synsetsB = _nounSynsets.get(nounB);
        if (synsetsA == null || synsetsB == null) {
            return -1;
        }
        if (minDistance == null) {
            minDistance = Integer.MAX_VALUE;
            SAP sap = new SAP(_digraph);
            int ancestor = -1;
            for (Integer synA : synsetsA) {
                for (Integer synB : synsetsB) {
                    int distance = sap.length(synA, synB);
                    if (distance < minDistance) {
                        minDistance = distance;
                        ancestor = sap.ancestor(synA, synB);
                    }
                }
            }
            nounPairLength.put(nounPair, minDistance);
            nounPairAncestor.put(nounPair, getSynsetName(ancestor));
        }
        return minDistance;
    }

    public String sap(String nounA, String nounB) {
        NounPair nounPair = new NounPair(nounA, nounB);
        String ancestorName = nounPairAncestor.get(nounPair);
        List<Integer> synsetsA = _nounSynsets.get(nounA);
        List<Integer> synsetsB = _nounSynsets.get(nounB);
        if (synsetsA == null || synsetsB == null) {
            return "";
        }
        if (ancestorName == null) {
            Integer minDistance = Integer.MAX_VALUE;
            SAP sap = new SAP(_digraph);
            int ancestor = -1;
            for (Integer synA : synsetsA) {
                for (Integer synB : synsetsB) {
                    int distance = sap.length(synA, synB);
                    if (distance < minDistance) {
                        minDistance = distance;
                        ancestor = sap.ancestor(synA, synB);
                    }
                }
            }
            ancestorName = getSynsetName(ancestor);
            nounPairLength.put(nounPair, minDistance);
            nounPairAncestor.put(nounPair, ancestorName);
        }
        return ancestorName;
    }

    private class NounPair {
        private final String nounA;
        private final String nounB;

        NounPair(String nounA, String nounB) {
            this.nounA = nounA;
            this.nounB = nounB;
        }

        public boolean equals(Object o) {
            if (o == null) return false;
            if (o instanceof NounPair == false) return false;
            NounPair that = (NounPair) o;
            return this.nounA.equals(that.nounA) && this.nounB.equals(that.nounB);
        }
    }

    public static void main(String[] args) {
        //WordNetTest.runTests();
    }
}