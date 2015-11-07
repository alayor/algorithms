import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class SAPTest {

    public static void runTests() {
        testLength();
        testAncestor();
    }

    private static void testLength() {
        In in = new In("wordnet/digraph1.txt");
        Digraph digraph = new Digraph(in);
        SAP sap = new SAP(digraph);
        assert sap.length(3, 11) == 4;
        assert sap.length(9, 12) == 3;
        assert sap.length(7, 2) == 4;
        assert sap.length(1, 6) == -1;
    }

    private static void testAncestor() {
        In in = new In("wordnet/digraph1.txt");
        Digraph digraph = new Digraph(in);
        SAP sap = new SAP(digraph);
        assert sap.ancestor(3, 11) == 1;
        assert sap.ancestor(9, 12) == 5;
        assert sap.ancestor(7, 2) == 0;
        assert sap.ancestor(1, 6) == -1;
    }

}