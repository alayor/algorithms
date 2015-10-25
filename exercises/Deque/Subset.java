import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {

  public static void main(String[] args) {
    int k = Integer.parseInt(args[0]);
    RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
    int count = 0;
    while (!StdIn.isEmpty()) {
      randomizedQueue.enqueue(StdIn.readString());
      count++;
      if (count > k) {
        randomizedQueue.dequeue();
      }
    }
    Iterator<String> iterator = randomizedQueue.iterator();
    while (iterator.hasNext()) {
      StdOut.println(iterator.next());
    }
  }
}
