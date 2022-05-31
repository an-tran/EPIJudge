package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchShiftedSortedArray {
  @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

  public static int searchSmallest(List<Integer> A) {
    int middle = A.size()/2;
    int size = A.size();
    int start = 0, end = A.size() - 1;
    System.out.println("size " + size);
    if (A.get(0) <= A.get(middle) && A.get(middle) <= A.get(A.size() - 1)) return 0; 
    while (true) {
      // Ony two element in the [start, end] range;
      // if (start == (end - 1)) return A.get(start) < A.get(end) ? start : end;

      if (A.get(Math.floorMod(middle - 1 , size)) > A.get(middle) && A.get(middle) < A.get((middle + 1) % size)) return middle;

      if (A.get(middle) > A.get(end)) {
        start = middle + 1;
      } else if (A.get(middle) < A.get(start)) {
        end = middle - 1;
      } else {
        return start;
      }
      middle = start + (end - start)/2;
      System.out.printf("%d %d %d\n", start, middle, end);
    }
  }

  public static void main(String[] args) {
    System.out.println(-1 % 2);
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
