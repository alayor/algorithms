import edu.princeton.cs.algs4.StdOut;

public class PuzzleSolverTest {

    public static void runTests() {
        testSolver();
    }

    private static void testSolver() {
        int[][] blocks = new int[][] {//
            {1, 2, 3}, //
            {4, 5, 6}, //
            {0, 7, 8}
        };
        Board board = new Board(blocks);
        Solver solver = new Solver(board);
        int moves = solver.moves();
        assert solver.isSolvable() == true;
        assert moves == 2;

        for (Board b : solver.solution()) {
            StdOut.println(b);
        }

        System.out.println("Number of moves: " + solver.moves());
    }
}
