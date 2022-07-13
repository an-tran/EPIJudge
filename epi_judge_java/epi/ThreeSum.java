package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
public class ThreeSum {
  @EpiTest(testDataFile = "three_sum.tsv")

  public static boolean hasThreeSum(List<Integer> A, int t) {
    // <1,5,8,13> 18
    Collections.sort(A);
    HashMap<Integer, Boolean> sum2e = new HashMap<>();
    for (int i = 0; i < A.size(); i++) {
      for (int j = 0; j < A.size(); j++) {
        // if (A.get(i) + A.get(j) <= t)
          sum2e.put(A.get(i) + A.get(j), true);
      }
    }
    for (int k = 0; k < A.size(); k++) {
      if (sum2e.containsKey(t - A.get(k))) return true; 
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ThreeSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
