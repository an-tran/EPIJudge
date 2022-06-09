package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LruCache {
  public static class Book {
    Integer isbn;
    Integer price;

    public Book(Integer isbn, Integer value) {
      this.isbn = isbn;
      this.price = value;
    }
  }

  public static class BiListNode<T> {
    public T data;
    public BiListNode<T> prev;
    public BiListNode<T> next;

    public BiListNode(T data, BiListNode<T> prev, BiListNode<T> next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
    }
  }

  private final int capacity;
  private Deque<Book> queue = new LinkedList<Book>();
  private Map<Integer, BiListNode<Book>> map = new HashMap<>();
  private BiListNode<Book> head = new BiListNode<LruCache.Book>(null, null, null);
  private BiListNode<Book> tail = new BiListNode<LruCache.Book>(null, null, null);
  private int size = 0;

  LruCache(final int capacity) {
     this.capacity = capacity;
     head.next = tail;
     tail.prev = head;
    }

  private void moveNodeToFirst(BiListNode<Book> node) {
    node.prev.next = node.next;
    if (node.next != null) {
      node.next.prev = node.prev;
    }
    node.next = head.next;
    if (head.next != null) {
      head.next.prev = node;
    }
    head.next = node;
    node.prev = head;
  }

  public Integer lookup(Integer key) {
    BiListNode<Book> node = map.get(key);
    if (node == null) {
      return -1;
    }

    moveNodeToFirst(node);
    return node.data.price;
  }

  public void insert(Integer key, Integer value) {
    if (!map.containsKey(key)) {
      BiListNode<Book> newnode = new BiListNode<LruCache.Book>(new Book(key, value), null, null);
      map.put(key, newnode);
      newnode.next = head.next;
      if (head.next != null) head.next.prev = newnode;
      head.next = newnode;
      newnode.prev = head;
      size++;
      if (size > capacity) {
        erase(tail.prev.data.isbn);
      }
    } else {
      moveNodeToFirst(map.get(key));
    }


    return;
  }
  public Boolean erase(Object key) {
    if (size == 0) return false;
    if (!map.containsKey(key)) return false;
    BiListNode<Book> remNode = map.remove(key);
    remNode.prev.next = remNode.next;
    remNode.next.prev = remNode.prev;
    remNode.prev = null;
    remNode.next = null;
    size--;
    return true;
  }
  
  @EpiUserType(ctorParams = {String.class, int.class, int.class})
  public static class Op {
    String code;
    int arg1;
    int arg2;

    public Op(String code, int arg1, int arg2) {
      this.code = code;
      this.arg1 = arg1;
      this.arg2 = arg2;
    }
  }

  @EpiTest(testDataFile = "lru_cache.tsv")
  public static void lruCacheTester(List<Op> commands) throws TestFailure {
    if (commands.isEmpty() || !commands.get(0).code.equals("LruCache")) {
      throw new RuntimeException("Expected LruCache as first command");
    }
    LruCache cache = new LruCache(commands.get(0).arg1);
    for (Op op : commands.subList(1, commands.size())) {
      int result;
      switch (op.code) {
      case "lookup":
        result = cache.lookup(op.arg1);
        if (result != op.arg2) {
          throw new TestFailure("Lookup: expected " + String.valueOf(op.arg2) +
                                ", got " + String.valueOf(result));
        }
        break;
      case "insert":
        cache.insert(op.arg1, op.arg2);
        break;
      case "erase":
        result = cache.erase(op.arg1) ? 1 : 0;
        if (result != op.arg2) {
          throw new TestFailure("Erase: expected " + String.valueOf(op.arg2) +
                                ", got " + String.valueOf(result));
        }
        break;
      default:
        throw new RuntimeException("Unexpected command " + op.code);
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LruCache.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
