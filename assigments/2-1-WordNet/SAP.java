import java.util.*;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

public class SAP {

    private final Digraph _diagraph;
    private final Map<NodePair, Integer> nodePairLength = new HashMap<NodePair, Integer>();
    private final Map<NodePair, Integer> nodePairAncestor = new HashMap<NodePair, Integer>();

    public SAP(Digraph digraph) {
        _diagraph = new Digraph(digraph);
    }

    public int length(int v, int w) {
        NodePair nodePair = new NodePair(v, w);
        Integer length =  nodePairLength.get(nodePair);
        if(length == null) {
            SAPAux SAPAux = new SAPAux(_diagraph, v, w);
            length = SAPAux.getLength();
            nodePairLength.put(nodePair, length);
            nodePairAncestor.put(nodePair, SAPAux.getCommonAncestor());
        }
       return length;
    }

    public int ancestor(int v, int w) {
        NodePair nodePair = new NodePair(v, w);
        Integer ancestor =  nodePairAncestor.get(nodePair);
        if(ancestor == null) {
            SAPAux SAPAux = new SAPAux(_diagraph, v, w);
            ancestor = SAPAux.getCommonAncestor();
            nodePairAncestor.put(nodePair, ancestor);
            nodePairLength.put(nodePair, SAPAux.getLength());
        }
        return ancestor;
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return new SAPAux(_diagraph, v, w).getLength();
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return new SAPAux(_diagraph, v, w).getCommonAncestor();
    }

    private class NodePair {
        private final int v;
        private final int w;

        NodePair(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public boolean equals(Object o) {
            if (o == null) return false;
            if (o instanceof NodePair == false) return false;
            NodePair that = (NodePair) o;
            return this.v == that.v && this.w == that.w;
        }
    }

    private class SAPAux {
        private final Digraph _digraph;
        private final BreadthFirstDirectedPaths _directedPathsV;
        private final BreadthFirstDirectedPaths _directedPathsW;
        private boolean[] marked;
        private int _minLength = Integer.MAX_VALUE;
        private int _commonAncestor = -1;

        SAPAux(Digraph digraph, int v, int w) {
            _digraph = digraph;
            marked = new boolean[digraph.V()];
            _directedPathsV = new BreadthFirstDirectedPaths(_digraph, v);
            _directedPathsW = new BreadthFirstDirectedPaths(_digraph, w);
            dfs(digraph, digraph.V() - 1);
        }

        SAPAux(Digraph digraph, Iterable<Integer> v, Iterable<Integer> w) {
            _digraph = digraph;
            marked = new boolean[digraph.V()];
            _directedPathsV = new BreadthFirstDirectedPaths(_digraph, v);
            _directedPathsW = new BreadthFirstDirectedPaths(_digraph, w);
            dfs(digraph, digraph.V() - 1);
        }

        private void dfs(Digraph digraph, int s) {
            if (marked[s]) {
                return;
            }
            for (int w : digraph.adj(s)) {
                dfs(digraph, w);
                marked[w] = true;

                if (_directedPathsV.hasPathTo(w) && _directedPathsW.hasPathTo(w)) {
                    int length = _directedPathsV.distTo(w) + _directedPathsW.distTo(w);
                    if (length < _minLength) {
                        _minLength = length;
                        _commonAncestor = w;
                    }
                }
            }
        }

        public int getLength() {
            return _minLength == Integer.MAX_VALUE ? -1 : _minLength;
        }

        public int getCommonAncestor() {
            return _commonAncestor;
        }
    }

    public static void main(String[] args) {
        //SAPTest.runTests();
    }
}