import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private boolean isSolvable;
    private int moves;
    private List<Board> boardsToSolution = new ArrayList<Board>();

    private static Comparator<Board> manhattanComparator = new Comparator<Board>() {
        @Override
        public int compare(Board o1, Board o2) {
            return Integer.compare(o1.manhattan(), o2.manhattan());
        }
    };

    public Solver(Board board) {
        if (board == null) {
            throw new NullPointerException();
        }
        MinPQ<Board> pq = new MinPQ<Board>(manhattanComparator);
        MinPQ<Board> pqTwin = new MinPQ<Board>(manhattanComparator);
        Board goalBoard = board;
        Board twinBoard = goalBoard.twin();
        Board previousTwin = null;
        Board previousBoard = null;
        boardsToSolution.add(goalBoard);
        while (!goalBoard.isGoal() && !twinBoard.isGoal()) {
            moves++;
            Iterator<Board> iterator = goalBoard.neighbors().iterator();
            while (iterator.hasNext()) {
                final Board neighbor = iterator.next();
                if (previousBoard == null || !previousBoard.equals(neighbor)) {
                    pq.insert(neighbor);
                }
            }
            Iterator<Board> iteratorTwin = twinBoard.neighbors().iterator();
            while (iteratorTwin.hasNext()) {
                final Board neighbor = iteratorTwin.next();
                if (previousBoard == null || !previousBoard.equals(neighbor)) {
                    pqTwin.insert(neighbor);
                }
            }
            previousBoard = goalBoard;
            goalBoard = pq.delMin();
            twinBoard = pqTwin.delMin();
            boardsToSolution.add(goalBoard);
        }
        if(goalBoard.isGoal()) {
            isSolvable = true;
        }
    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                return boardsToSolution.iterator();
            }
        };
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
    }
}
