package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class KthLargestInArray {
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(1, A) returns 3, findKthLargest(2, A) returns 2,
  // findKthLargest(3, A) returns 1, and findKthLargest(4, A) returns -1.
  @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  public static int findKthLargest(int k, List<Integer> A) {
    int upperBound = Integer.MAX_VALUE;
    int ret = 0;
    while (k-- > 0) {
      ret = findMax(A, 0, A.size() - 1, upperBound);
      upperBound = ret;
    }
    return ret;
  }

  public static int findMax(List<Integer> A, int start, int end, int upbound) {
    if (start == end) return A.get(start);
    if (start > end) return Integer.MIN_VALUE;
    int middle = start + (end - start)/2;
    int maxVal = Math.min(A.get(middle), upbound);
    if (A.get(middle) >= upbound) {
      maxVal = Integer.MIN_VALUE;
    }
    int left =  findMax(A, start, middle - 1, upbound);
    int right = findMax(A, middle + 1, end, upbound);
    maxVal = Math.max(maxVal, left >= upbound ? Integer.MIN_VALUE : left);
    maxVal = Math.max(maxVal, right >= upbound ? Integer.MIN_VALUE : right);
    return maxVal;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
