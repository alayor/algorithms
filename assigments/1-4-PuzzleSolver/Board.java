import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board {

    private final int[][] _blocks;

    public Board(int[][] blocks) {
        _blocks = copyBlocks(blocks);
    }

    private int[][] copyBlocks(int[][] blocks) {
        if (blocks == null || blocks.length < 1) {
            return new int[0][0];
        }
        int dimension = blocks[0].length;
        int[][] result = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                result[i][j] = blocks[i][j];
            }
        }
        return result;
    }

    public int dimension() {
        if (_blocks == null || _blocks.length < 1) {
            return 0;
        }
        return _blocks[0].length;
    }

    public int hamming() {
        int hamming = 0;
        int dimension = dimension();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int numberToCompare = i * dimension + j + 1;
                if (numberToCompare < dimension * dimension) {
                    if (_blocks[i][j] != numberToCompare) {
                        hamming++;
                    }
                }
            }
        }
        return hamming;
    }

    public int manhattan() {
        int manhattan = 0;
        int dimension = dimension();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int numberToCompare = i * dimension + j + 1;
                if (numberToCompare < dimension * dimension) {
                    int currentNumber = _blocks[i][j];
                    if (currentNumber != numberToCompare) {
                        int distance = getDistance(numberToCompare, i, j);
                        manhattan += distance;
                    }
                }
            }
        }
        return manhattan;
    }

    private int getDistance(int currentNumber, int currentI, int currentJ) {
        int dimension = dimension();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (_blocks[i][j] == currentNumber) {
                    return Math.abs(j - currentJ) + Math.abs(i - currentI);
                }
            }
        }
        return 0;
    }

    public boolean isGoal() {
        int dimension = dimension();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int numberToCompare = i * dimension + j + 1;
                if (numberToCompare < dimension * dimension) {
                    if (_blocks[i][j] != numberToCompare) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Board twin() {
        Block block1 = getNextNonBlankBlock(0, 0);
        final int dimension = dimension();
        int nextI = block1.getI() < dimension - 1 ? block1.getI() + 1 : block1.getI();
        int nextJ = block1.getJ() < dimension - 1 ? block1.getJ() + 1 : block1.getJ();
        Block block2 = getNextNonBlankBlock(nextI, nextJ);
        int[][] copiedBlocks = copyBlocks(_blocks);
        exchange(copiedBlocks, block1, block2);
        return new Board(copiedBlocks);
    }

    private Block getNextNonBlankBlock(int currentI, int currentJ) {
        int dimension = dimension();
        for (int i = currentI; i < dimension; i++) {
            for (int j = currentJ; j < dimension; j++) {
                if (_blocks[i][j] != 0) {
                    return new Block(i, j);
                }
            }
        }
        return new Block(0, 0);
    }

    private void exchange(int[][] blocks, Block block1, Block block2) {
        int temp = blocks[block1.getI()][block1.getJ()];
        blocks[block1.getI()][block1.getJ()] = blocks[block2.getI()][block2.getJ()];
        blocks[block2.getI()][block2.getJ()] = temp;
    }

    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }

        Board that = (Board) y;
        int dimension = dimension();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (_blocks[i][j] != that._blocks[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        final List<Board> neighbors = getNeighbors();
        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                return neighbors.iterator();
            }
        };
    }

    private List<Board> getNeighbors() {
        List<Board> neighbors = new ArrayList<Board>();
        Block zeroBlock = getBlankBlock();
        int dimension = dimension();
        if (zeroBlock.getI() >= 1) {
            int[][] blocks = copyBlocks(_blocks);
            exchange(blocks, zeroBlock, new Block(zeroBlock.getI() - 1, zeroBlock.getJ()));
            neighbors.add(new Board(blocks));
        }
        if (zeroBlock.getI() < dimension - 1) {
            int[][] blocks = copyBlocks(_blocks);
            exchange(blocks, zeroBlock, new Block(zeroBlock.getI() + 1, zeroBlock.getJ()));
            neighbors.add(new Board(blocks));
        }
        if(zeroBlock.getJ() >= 1) {
            int[][] blocks = copyBlocks(_blocks);
            exchange(blocks, zeroBlock, new Block(zeroBlock.getI(), zeroBlock.getJ() - 1));
            neighbors.add(new Board(blocks));
        }
        if(zeroBlock.getJ() < dimension - 1) {
            int[][] blocks = copyBlocks(_blocks);
            exchange(blocks, zeroBlock, new Block(zeroBlock.getI(), zeroBlock.getJ() + 1));
            neighbors.add(new Board(blocks));
        }
        return neighbors;
    }

    private Block getBlankBlock() {
        int dimension = dimension();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (_blocks[i][j] == 0) {
                    return new Block(i, j);
                }
            }
        }
        return new Block(0, 0);
    }

    public String toString() {
        int dimension = dimension();
        StringBuilder s = new StringBuilder();
        s.append(dimension + "\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                s.append(String.format("%2d ", _blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {

    }

    private class Block {
        private int i;
        private int j;

        public Block(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    }
}
