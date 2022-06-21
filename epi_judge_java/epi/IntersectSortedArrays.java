package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class IntersectSortedArrays {
  @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                       List<Integer> B) {
    List<Integer> ret = new ArrayList<>();
    int i = 0, j =0;
    Integer lastval = null;
    while (i < A.size() && j < B.size()) {
      if (A.get(i) < B.get(j)) {
        i++;
      } else if (A.get(i) > B.get(j)) {
        j++;
      } else {
        if (lastval == null || !A.get(i).equals(lastval)) {
          lastval = A.get(i);
          ret.add(lastval);
        }
        i++; j++;
      }
    }
    
    return ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntersectSortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
