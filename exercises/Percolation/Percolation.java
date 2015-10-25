import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{

  private WeightedQuickUnionUF unionFind;
  private int n = 0;
  private int totalSize = 0;
  private boolean[][] sites;
  private int virtualTopSite = 0;
  private int virtualBottomSite = 0;

   public Percolation(int n) {
     if(n<=0){
       throw new IllegalArgumentException("parameter should be greater than 0");
     }
     this.n = n;
     this.totalSize = n * n;
     sites = new boolean[n][n];
     unionFind = new WeightedQuickUnionUF(totalSize + 2);
     virtualTopSite = totalSize;
     virtualBottomSite = totalSize + 1;
     connectVirtualSites();
   }

   public void open(int i, int j){
     if(areRowAndColumnIndicesInvalid(i, j)){
       throw new IndexOutOfBoundsException("i and j must be between 0 and " + n);
     }
     sites[i-1][j-1] = true;
     int index_i = i - 1;
     int index_j = j - 1;
     int centerSite = n * (index_j) + index_i;
     connectWithTopSite(index_i, index_j, centerSite);
     connectWithLeftSite(index_i, index_j, centerSite);
     connectWithRightSite(index_i, index_j, centerSite);
     connectWithBottomSite(index_i, index_j, centerSite);
   }
   public boolean isOpen(int i, int j) {
     if(areRowAndColumnIndicesInvalid(i, j)){
       throw new IndexOutOfBoundsException("i and j must be between 0 and " + n +
       ". values: i: " + i + ", j: " + j);
     }
     return sites[i-1][j-1];
   }

   public boolean isFull(int i, int j){
     if(areRowAndColumnIndicesInvalid(i, j)){
       throw new IndexOutOfBoundsException("i and j must be between 0 and " + n);
     }
     int site = n * (j - 1) + i - 1;
     return unionFind.connected(virtualTopSite, site);
   }

  private boolean areRowAndColumnIndicesValid(int i, int j) {
    if(i > 0 && i <= n && j > 0 && j <= n){
     return true;
    }
    return false;
  }

  private boolean areRowAndColumnIndicesInvalid(int i, int j) {
    return !areRowAndColumnIndicesValid(i, j);
  }
   public boolean percolates(){
     return unionFind.connected(virtualTopSite, virtualBottomSite);
   }

  private void connectWithTopSite(int i, int j, int centerSite){
    int topSite = n * (j - 1) + i;
    if(areRowAndColumnIndicesValid(i, j - 1)&& isOpen(i, j - 1)
                      && !unionFind.connected(centerSite, topSite)) {
      unionFind.union(centerSite, topSite);
    }
  }

  private void connectWithLeftSite(int i, int j, int centerSite){
    int leftSite = n * j + i - 1;
    if(areRowAndColumnIndicesValid(i - 1, j) && isOpen(i - 1, j)
                      && !unionFind.connected(centerSite, leftSite)) {
      unionFind.union(centerSite, leftSite);
    }
  }

  private void connectWithRightSite(int i, int j, int centerSite){
      int rightSite = n * j + i + 1;
      if(rightSite < n * n && areRowAndColumnIndicesValid(i + 1, j + 1) && isOpen(i + 1, j + 1)
                      && !unionFind.connected(centerSite, rightSite)) {
        unionFind.union(centerSite, rightSite);
      }
  }

  private void connectWithBottomSite(int i, int j, int centerSite){
      int bottomSite = n * (j + 1) + i;
      if(bottomSite < n * n && areRowAndColumnIndicesValid(i, j + 1) && isOpen(i, j + 1)
                      && !unionFind.connected(centerSite, bottomSite)) {
        unionFind.union(centerSite, bottomSite);
      }
  }

  private void connectVirtualSites(){
    for(int i = 0; i < n; i++) {
      unionFind.union(virtualTopSite, i);
    }
    for(int i = n * (n - 1); i < n * n; i++) {
      unionFind.union(virtualBottomSite, i);
    }
  }
}
