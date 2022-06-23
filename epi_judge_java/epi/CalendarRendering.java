package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
public class CalendarRendering {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Event {
    public int start, finish;

    public Event(int start, int finish) {
      this.start = start;
      this.finish = finish;
    }
  }

  private static class Endpoint {
    public int time;
    public boolean isStart;

    Endpoint(int time, boolean isStart) {
      this.time = time;
      this.isStart = isStart;
    }
  }

  @EpiTest(testDataFile = "calendar_rendering.tsv")

  public static int findMaxSimultaneousEvents(List<Event> A) {
    Collections.sort(A, (o1, o2) -> Integer.compare(o1.start, o2.start));
    PriorityQueue<Integer> minQ = new PriorityQueue<>();
    int ret = 0;
    for (int i = 0; i < A.size(); i++) {
      while (!minQ.isEmpty()) {
        if (A.get(i).start > minQ.peek()) {
          minQ.poll();
        } else {
          break;
        }
      }

      minQ.add(A.get(i).finish);
      ret = Math.max(ret, minQ.size());
    }
    return ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CalendarRendering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
