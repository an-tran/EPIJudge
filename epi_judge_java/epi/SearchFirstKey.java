package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")

  public static int searchFirstOfK(List<Integer> A, int k) {
    int idx = Collections.binarySearch(A, k);
    int ret = idx;
    while (idx >= 0) {
      List<Integer> subList = A.subList(0, idx);
      ret = idx;
      idx = Collections.binarySearch(subList, k);
    }

    return ret < 0 ? -1 : ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
