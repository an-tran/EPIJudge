package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class KLargestValuesInBst {
  @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")

  public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
    List<Integer> ret = new ArrayList<>();
    findKLargest(tree, k, ret);
    return ret;
  }
  
  private static void findKLargest(BstNode<Integer> tree, int k, List<Integer> ret) {
    // if (ret.size() == k) return;
    if (tree == null) return;
    findKLargest(tree.right, k, ret);
    if (ret.size() == k) return;
    ret.add(tree.data);
    findKLargest(tree.left, k, ret);
  }

  @EpiTestComparator
  public static boolean comp(List<Integer> expected, List<Integer> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestValuesInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
