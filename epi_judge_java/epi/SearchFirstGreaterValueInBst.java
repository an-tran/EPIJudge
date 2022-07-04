package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SearchFirstGreaterValueInBst {

  public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree,
                                                       Integer k) {
    return findHelper(tree, k, null);
  }

  private static BstNode<Integer> findHelper(BstNode<Integer> tree, Integer k, BstNode<Integer> greaterThanK) {
    if (tree == null) return greaterThanK;

    if (tree.data <= k) {
      return findHelper(tree.right, k, greaterThanK);
    } else if (tree.data > k) {
      if (greaterThanK == null) greaterThanK = tree;
      else greaterThanK = greaterThanK.data <= tree.data ? greaterThanK : tree;
      
      return findHelper(tree.left, k, greaterThanK);
    }

    return greaterThanK;
  }

  @EpiTest(testDataFile = "search_first_greater_value_in_bst.tsv")
  public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree,
                                                 Integer k) {
    BstNode<Integer> result = findFirstGreaterThanK(tree, k);
    return result != null ? result.data : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstGreaterValueInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
