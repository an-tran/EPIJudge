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
    int start = 0, end = A.size() - 1;
    int next = 1;
    int pivotIdx = rand.nextInt(A.size());
    while (true) { //TODO: update found
      Collections.swap(A, start, pivotIdx);
      int tail = end;
      while (next <= tail) {
        if (A.get(tail) <= A.get(start)) {
          Collections.swap(A, next, tail);
          next++;
        } else {
          tail--;
        }
      }
      int biggerLen = end - tail;
      if (biggerLen == k -1) {
        return A.get(start);
      } else if (biggerLen > k - 1) {
        start = next;
        if (biggerLen == 1) {
          return A.get(start);
        }
        pivotIdx = start + rand.nextInt(biggerLen - 1);
      } else {
        k = k - biggerLen - 1;
        start++;
        end = tail;
        if (start >= end) {
          System.out.println("Invalid state, k = " + k);
          return A.get(start);
        }
        pivotIdx = start + rand.nextInt(end - start);
      }
      next = start + 1;

    }
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
