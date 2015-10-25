import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

  private Node first, last;
  private int size;

  public Deque() {
    first = null;
    last = null;
  }

  public boolean isEmpty() {
    return first == null || last == null;
  }

  public int size() {
    return this.size;
  }

  public void addFirst(Item item) {
    if (item == null) {
      throw new NullPointerException();
    }
    Node newFirst = new Node();
    newFirst.item = item;
    if (isEmpty()) {
      last = newFirst;
    } else {
      newFirst.next = first;
      first.previous = newFirst;
    }
    first = newFirst;
    size++;
  }

  public void addLast(Item item) {
    if (item == null) {
      throw new NullPointerException();
    }
    Node newLast = new Node();
    newLast.item = item;

    if (isEmpty()) {
      first = newLast;
    } else {
      newLast.previous = last;
      last.next = newLast;
    }
      last = newLast;
      size++;
  }

  public Item removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    Item item = first.item;
    Node oldFirst = first;
    first = oldFirst.next;
    oldFirst.next = null;
    if (isEmpty()) {
      last = null;
    } else {
      first.previous = null;
    }
    size--;
    return item;
  }

  public Item removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    Item item = last.item;
    Node oldLast = last;
    last = oldLast.previous;
    oldLast.previous = null;
    if (isEmpty()) {
      first = null;
    } else {
      last.next = null;
    }
    size--;
    return item;
  }

  public Iterator<Item> iterator() {
    return new Iterator<Item>() {
      public boolean hasNext() {
        return !isEmpty();
      }
      public Item next() {
        return removeFirst();
      }
      public void remove(){
        throw new UnsupportedOperationException();
      }
    };
  }

  public static void main(String[] args) {
  }

  private class Node {
    Item item;
    Node next;
    Node previous;
  }
}
