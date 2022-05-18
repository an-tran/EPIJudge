package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.List;
public class CircularQueue {


  public static class Queue {
    Integer[] queue;
    int capacity;
    int head = 0;
    int tail = 0;
    int _size = 0;
    public Queue(int capacity) {
      queue = new Integer[capacity];
      this.capacity = capacity;
    }

    public void enqueue(Integer x) {
      // update tail
      // increase queue capacity if needed
      if (_size < capacity) {
        queue[tail] = x;
        tail = (tail + 1) % capacity;
      } else {
        Integer[] newqueue = new Integer[capacity * 2];
        int copylen = Math.max(capacity, tail)  - head;
        System.arraycopy(queue, head, newqueue, 0, copylen);
        if (tail <= head) {
          System.arraycopy(queue, 0, newqueue, copylen, tail);
        }
        queue = newqueue;
        head = 0;
        tail = capacity;
        queue[tail] = x;
        capacity = queue.length;
        tail = (tail + 1) % capacity;
      }
      _size++;
//      System.out.printf("Enque %d:  queue %s\n", x, toString());
      return;
    }
    public Integer dequeue() {
      // update head
      if (size() == 0) {
        throw new IllegalStateException("dequeue(): queue is empty");
      }
      Integer headval = queue[head];
      head = (head + 1) % capacity;
      _size--;

//      System.out.printf("Deque %d:  queue %s\n", headval, toString());
      return headval;
    }

    public int size() {
      return _size;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("[");
      for (int i = head; i < Math.max(capacity, tail); i++)
      {
        sb.append(queue[i]);
        sb.append(" ");
      }

      if (tail <= head) {
        for (int i = 0; i < tail; i++) {
          sb.append(queue[i]);
          sb.append(" ");
        }
      }

      return sb.toString().trim() + "]";
    }
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }

    @Override
    public String toString() {
      return op;
    }
  }

  @EpiTest(testDataFile = "circular_queue.tsv")
  public static void queueTester(List<QueueOp> ops) throws TestFailure {
    Queue q = new Queue(1);
    int opIdx = 0;
    for (QueueOp op : ops) {
      switch (op.op) {
      case "Queue":
        q = new Queue(op.arg);
        break;
      case "enqueue":
        q.enqueue(op.arg);
        break;
      case "dequeue":
        int result = q.dequeue();
        if (result != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, result);
        }
        break;
      case "size":
        int s = q.size();
        if (s != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, s);
        }
        break;
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CircularQueue.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
