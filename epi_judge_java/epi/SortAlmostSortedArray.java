package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
public class SortAlmostSortedArray {

  public static List<Integer>
  sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
    List<Integer> ret = new ArrayList<>();
    PriorityQueue<Integer> minheap = new PriorityQueue<>(k + 1);
    while (sequence.hasNext()) {
      minheap.add(sequence.next());
      if (minheap.size() > k) {
        ret.add(minheap.poll());
      }
    }
    while (!minheap.isEmpty()) {
      ret.add(minheap.poll());
    }

    return ret;
  }

  @EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
  public static List<Integer>
  sortApproximatelySortedDataWrapper(List<Integer> sequence, int k) {
    return sortApproximatelySortedData(sequence.iterator(), k);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortAlmostSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
