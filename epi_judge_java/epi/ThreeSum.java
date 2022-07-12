package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class ThreeSum {
  @EpiTest(testDataFile = "three_sum.tsv")

  public static boolean hasThreeSum(List<Integer> A, int t) {
    // <1, 4 , 5, 7> => 6, 10 (not 8)
    // 3 nummber & t
    // 2 number & ( t - a )
    // reduce t & reduce size of A
    Collections.sort(A);
    int start = 0, end = A.size() -1; 
    return hasThreeSumHelper(A, start, end, t, 3);
  }
  
  public static boolean hasThreeSumHelper(List<Integer> A, int start, int end, int t, int count) {
    if (count <= 0 && t != 0) return false; 
    for (; start <= end; start++) {
      if (t == A.get(start)) {
        if (count == 1) return true;
      }
      boolean ret = hasThreeSumHelper(A, start, end, t - A.get(start), count - 1);
      if (ret) return ret;
    }
    return false;
  }

  public static void main(String[] args) {
    hasThreeSum(Arrays.asList(0, 2, -2), 0);
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ThreeSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
