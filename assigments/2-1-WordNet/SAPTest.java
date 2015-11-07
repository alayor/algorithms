import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class SAPTest {

    public static void runTests() {
        testLength();
    }

    private static void testLength() {
        In in = new In("wordnet/digraph1.txt");
        Digraph digraph = new Digraph(in);
        SAP sap = new SAP(digraph);
        assert sap.length(3, 11) == 4;
    }

}