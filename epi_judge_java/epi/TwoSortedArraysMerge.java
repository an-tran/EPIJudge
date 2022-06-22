package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class TwoSortedArraysMerge {

  public static void mergeTwoSortedArrays(List<Integer> A, int m,
                                          List<Integer> B, int n) {
    Collections.rotate(A, -m);;
    for (int i = A.size() - m, j = 0, start = 0; (i < A.size() || j < n) && start < A.size() ; start++) {
      if (i < A.size() && j < n) {
        if (A.get(i) <= B.get(j)) {
          A.set(start, A.get(i++));
        } else {
          A.set(start, B.get(j++));
        } 
      } else if (i < A.size()) {
        A.set(start, A.get(i++));
      } else if (j < n) {
        A.set(start, B.get(j++));
      } else {
        A.set(start, null);
      }
    }
    return;
  }

  @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
  public static List<Integer>
  mergeTwoSortedArraysWrapper(List<Integer> A, int m, List<Integer> B, int n) {
    mergeTwoSortedArrays(A, m, B, n);
    return A;
  }

  public static void main(String[] args) {
    // List<Integer> arr = Arrays.asList(-1, 2, 3, 0);
    // Collections.rotate(arr, -3);
    // System.out.println(arr.toString());
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TwoSortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
