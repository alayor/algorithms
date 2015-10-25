public class SolverTest {
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

        assert solver.isSolvable() == true;
        assert solver.moves() == 2;
    }
}