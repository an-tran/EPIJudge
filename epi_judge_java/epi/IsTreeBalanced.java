package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    return isBalancedHelper(tree) >= 0;
  }

  public static int isBalancedHelper(BinaryTreeNode<Integer> tree) {

    if (tree == null) return 0;

    int left = isBalancedHelper(tree.left);
    int right = isBalancedHelper(tree.right);
    if (left < 0 || right < 0) {
      return -1;
    }
    if (Math.abs(left - right) > 1) return -1;

    return Math.max(left, right) + 1;
  }
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
