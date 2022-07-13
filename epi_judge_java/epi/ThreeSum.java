package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

<<<<<<< HEAD
import java.util.Collections;
import java.util.HashMap;
=======
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
>>>>>>> 648601435fcf7752e3e45bd992136d909702ee92
import java.util.List;
public class ThreeSum {
  @EpiTest(testDataFile = "three_sum.tsv")

  public static boolean hasThreeSum(List<Integer> A, int t) {
<<<<<<< HEAD
    // <1,5,8,13> 18
    Collections.sort(A);
    HashMap<Integer, Boolean> sum2e = new HashMap<>();
    for (int i = 0; i < A.size(); i++) {
      for (int j = 0; j < A.size(); j++) {
        if (A.get(i) + A.get(j) <= t)
          sum2e.put(A.get(i) + A.get(j), true);
      }
    }
    for (int k = 0; k < A.size(); k++) {
      if (sum2e.containsKey(t - A.get(k))) return true; 
=======
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
>>>>>>> 648601435fcf7752e3e45bd992136d909702ee92
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
