package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SortedArraysMerge {
  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")

  public static List<Integer>
  mergeSortedArrays(List<List<Integer>> sortedArrays) {
    PriorityQueue<List<Integer>> maxheap = new PriorityQueue<>(sortedArrays.size(), (Comparator<List<Integer>>) (a, b) -> Integer.compare(b.get(b.size() - 1), a.get(a.size() - 1)));

    for (List<Integer> l : sortedArrays) {
      maxheap.add(l);
    }

    List<Integer> ret = new ArrayList<>();
    while (maxheap.size() > 0) {
      List<Integer> l = maxheap.poll();
      ret.add(l.remove(l.size() - 1));
      if (l.size() > 0) maxheap.add(l);
    }


    Collections.reverse(ret);
    return ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
