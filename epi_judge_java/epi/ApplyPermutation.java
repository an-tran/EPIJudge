package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ApplyPermutation {
  public static void applyPermutation(List<Integer> perm, List<Integer> A) {
    int nextIdx = 0;
    int permIdx = -1;
    int val = 0;
    while (nextIdx < perm.size()) {
      if (nextIdx == -1) {
        nextIdx++;
        continue;
      }
      permIdx = nextIdx;
      while (perm.get(permIdx) != -1) {
        int targetIdx = perm.get(permIdx);
        Collections.swap(A, nextIdx, targetIdx);
        perm.set(permIdx, -1); // mark the location at then permIdx is already applied
        permIdx = targetIdx;
      }
      // update nextIdx
      nextIdx++;
    }
  }
  @EpiTest(testDataFile = "apply_permutation.tsv")
  public static List<Integer> applyPermutationWrapper(List<Integer> perm,
                                                      List<Integer> A) {
    applyPermutation(perm, A);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ApplyPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
