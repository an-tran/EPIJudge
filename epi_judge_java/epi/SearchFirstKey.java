package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")

  public static int searchFirstOfK(List<Integer> A, int k) {
    int left = 0, right = A.size() - 1;
    int middle = left + (right - left)/2;
    int ret = -1;
    while (left <= right ) {
      if (A.get(middle) < k) {
        left = middle + 1;
        middle = left + (right - left)/2;
      } else if (A.get(middle) == k) {
        right = middle - 1;
        ret = middle;
        middle = left + (right - left)/2;
      } else {
        right = middle - 1;
        middle = left + (right - left)/2;
      }
    }

    return ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
