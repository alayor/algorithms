import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  private double[] openSitesWhenPercolates;
  private int times = 0;

  public static void main(String[] args) {
    PercolationStats percolationStats =
    new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    System.out.printf("mean\t\t\t\t = %.10f\n", percolationStats.mean());
    System.out.printf("stddev\t\t\t\t = %.10f\n", percolationStats.stddev());
    System.out.printf("95%% confidence interval\t\t = %.10f, %.10f\n",
     percolationStats.confidenceLo(),  percolationStats.confidenceHi());
  }

   public PercolationStats(int n, int times) {
     if(n <= 0 || times <= 0) {
      throw new IllegalArgumentException("n and times must be greater than 0");
     }
     this.times = times;
     openSitesWhenPercolates = new double[times];
     for(int i = 0; i < times; i++) {
       openSitesWhenPercolates[i] = (double)runPercolation(n) / (n * n);
     }
   }
   public double mean(){
     return StdStats.mean(openSitesWhenPercolates);
   }
   public double stddev() {
      return StdStats.stddev(openSitesWhenPercolates);
   }
   public double confidenceLo(){
     return mean() - (1.96 * stddev()) / Math.sqrt(times);
   }
   public double confidenceHi(){
     return mean() + (1.96 * stddev()) / Math.sqrt(times);
   }

   private int runPercolation(int n){
     int openSites = 0;
     Percolation percolation = new Percolation(n);
     while(!percolation.percolates()){
       int i = StdRandom.uniform(1, n + 1);
       int j = StdRandom.uniform(1, n + 1);
       if(!percolation.isOpen(i, j)) {
         percolation.open(i, j);
         openSites++;
       }
     }
     return openSites;
   }
}
