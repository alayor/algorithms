import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private Item[] array;
  private int N;

  public RandomizedQueue() {
    array = (Item[]) new Object[1];
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public int size() {
    return N;
  }

  public void enqueue(Item item) {
    if (item == null) {
      throw new NullPointerException();
    }
    if (N == array.length) {
      resize(2 * array.length);
    }
    array[N++] = item;
  }

  public Item dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    int random = StdRandom.uniform(N);
    exchange(random, N - 1);
    Item item = array[--N];
    array[N] = null;
    if (N > 0 && N == array.length / 4) {
      resize(array.length / 2);
    }
    return item;
  }

  public Item sample() {
    return array[StdRandom.uniform(N)];
  }

  public Iterator<Item> iterator() {
    return new Iterator<Item>() {
      public boolean hasNext(){
        return !isEmpty();
      }
      public Item next(){
        return dequeue();
      }
      public void remove(){
        throw new UnsupportedOperationException();
      }
    };
  }

  public static void main(String[] args) {
  }

  private void resize(int capacity) {
    Item[] copy = (Item[]) new Object[capacity];
    for (int i = 0; i < N; i++) {
      copy[i] = array[i];
    }
    array = copy;
  }

  private void exchange(int i, int j) {
    Item temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
