package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
import java.util.Random;
public class KthLargestInArray {
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(1, A) returns 3, findKthLargest(2, A) returns 2,
  // findKthLargest(3, A) returns 1, and findKthLargest(4, A) returns -1.
  @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  public static int findKthLargest(int k, List<Integer> A) {
    Random rand = new Random();
    int start = 0;
    int end = A.size() - 1;
    while (start <= end) {
      int pivotIdx = start + rand.nextInt(end - start + 1);
      Collections.swap(A, start, pivotIdx);
      int nextIdx = start;
      int i = start + 1;
      while (i <= end) {
        if (A.get(i) >= A.get(start)) {
          Collections.swap(A, i, ++nextIdx);
        }
        i++;
      }
      Collections.swap(A, start, nextIdx);
      if (nextIdx == k - 1) return A.get(nextIdx);
      if (nextIdx > k - 1) {
        end = nextIdx - 1;
      } else  {
        start = nextIdx + 1;
      }
    }

    // return A.get(start);
    return -1;
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
