import java.lang.System;
import java.util.Iterator;

public class BoardTest {

    public static void runTests() {
        testDimension();
        testHamming3Dim();
        testHamming4Dim();
        testManhattan3Dim();
        testManhattan4Dim();
        testIsGoal();
        testTwin();
        testEquals();
        testNeighbors();
        testToString();
    }

    private static void testToString() {
        int[][] blocks = new int[][]{//
            {1, 2, 3},//
            {4, 5, 6},//
            {7, 8, 0}//
        };
        Board board = new Board(blocks);
        assert board.toString().equals(
            "3\n" +
                " 1  2  3 \n" +
                " 4  5  6 \n" +
                " 7  8  0 \n") : board.toString();

        blocks = new int[][]{//
            {1, 2, 3, 4},//
            {5, 6, 7, 8},//
            {9, 10, 11, 12},//
            {13, 14, 15, 0}//
        };
        board = new Board(blocks);
        assert board.toString().equals(
            "4\n" +
                " 1  2  3  4 \n" +
                " 5  6  7  8 \n" +
                " 9 10 11 12 \n" +
                "13 14 15  0 \n") : board.toString();
    }

    private static void testNeighbors() {
        int[][] blocks = new int[][]{//
            {1, 2, 3},//
            {4, 5, 6},//
            {7, 8, 0}//
        };
        Board board = new Board(blocks);

        Iterator<Board> iterator = board.neighbors().iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        assert count == 2 : count;

        blocks = new int[][]{//
            {1, 0, 3},//
            {4, 2, 5},//
            {6, 7, 8}//
        };
        board = new Board(blocks);
        iterator = board.neighbors().iterator();
        count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        assert count == 3 : count;

        blocks = new int[][]{//
            {1, 2, 3},//
            {4, 0, 5},//
            {6, 7, 8}//
        };
        board = new Board(blocks);
        iterator = board.neighbors().iterator();
        count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        assert count == 4 : count;
    }

    private static void testEquals() {
        int[][] blocks1 = new int[][]{//
            {1, 2, 3},//
            {4, 5, 6},//
            {7, 8, 0}//
        };
        Board board1 = new Board(blocks1);

        int[][] blocks2 = new int[][]{//
            {1, 2, 3},//
            {4, 5, 6},//
            {7, 8, 0}//
        };
        Board board2 = new Board(blocks2);
        assert board1.equals(board2);
    }

    private static void testTwin() {
        int[][] blocks = new int[][]{//
            {1, 2, 3},//
            {4, 5, 6},//
            {7, 0, 8}};//

        Board board = new Board(blocks);
        Board twin = board.twin();
        System.out.println(twin);
        assert twin != null;

        blocks = new int[][]{//
            {0, 1, 2}, //
            {3, 4, 5}, //
            {6, 7, 8}};

        board = new Board(blocks);
        twin = board.twin();
        assert twin != null;

        blocks = new int[][]{//
            {1, 5, 3}, //
            {4, 0, 6}, //
            {7, 8, 2}};

        board = new Board(blocks);
        twin = board.twin();
        assert twin != null;
    }

    private static void testIsGoal() {
        int[][] blocks = new int[][]{{1, 2, 3}, {4, 0, 5}, {6, 7, 8}};

        Board board = new Board(blocks);
        boolean isGoal = board.isGoal();
        assert isGoal == false : isGoal;

        blocks = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

        board = new Board(blocks);
        isGoal = board.isGoal();
        assert isGoal == true : isGoal;

        blocks = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 0, 14, 15}};

        board = new Board(blocks);
        isGoal = board.isGoal();
        assert isGoal == false : isGoal;

        blocks = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};

        board = new Board(blocks);
        isGoal = board.isGoal();
        assert isGoal == true : isGoal;
    }

    private static void testManhattan3Dim() {
        int[][] blocks = new int[][]{{1, 2, 3}, {4, 0, 5}, {6, 7, 8}};
        Board board = new Board(blocks);
        int manhattan = board.manhattan();
        assert manhattan == 6 : manhattan;

        blocks = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        board = new Board(blocks);
        manhattan = board.manhattan();
        assert manhattan == 0 : manhattan;

        blocks = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
        board = new Board(blocks);
        manhattan = board.manhattan();
        assert manhattan == 12 : manhattan;
    }

    private static void testManhattan4Dim() {
        int[][] blocks = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
        Board board = new Board(blocks);
        int manhattan = board.manhattan();
        assert manhattan == 0 : manhattan;

        blocks = new int[][]{{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}};
        board = new Board(blocks);
        manhattan = board.manhattan();
        assert manhattan == 24 : manhattan;
    }

    private static void testDimension() {
        int[][] blocks = new int[3][3];
        Board board = new Board(blocks);
        int dimension = board.dimension();
        assert dimension == 3 : dimension;
    }

    private static void testHamming3Dim() {
        int[][] blocks = new int[][]{{1, 2, 3}, {4, 0, 5}, {6, 7, 8}};
        Board board = new Board(blocks);
        int hamming = board.hamming();
        assert hamming == 4 : hamming;

        blocks = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
        board = new Board(blocks);
        hamming = board.hamming();
        assert hamming == 8 : hamming;
    }

    private static void testHamming4Dim() {
        int[][] blocks = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 0, 15}};
        Board board = new Board(blocks);
        int hamming = board.hamming();
        assert hamming == 1 : hamming;

        blocks = new int[][]{{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}};
        board = new Board(blocks);
        hamming = board.hamming();
        assert hamming == 15 : hamming;
    }
}
