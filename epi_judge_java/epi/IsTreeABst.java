package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeABst {
  public static class SubTreeResult {
    boolean isbst;
    BinaryTreeNode<Integer> minval;
    BinaryTreeNode<Integer> maxval;

    public SubTreeResult(boolean isbst, BinaryTreeNode<Integer> leftmost, BinaryTreeNode<Integer> rightmost) {
      this.isbst = isbst;
      minval = leftmost;
      maxval = rightmost;
    }
  }

  @EpiTest(testDataFile = "is_tree_a_bst.tsv")
  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    SubTreeResult ret = isSubTreeBST(tree);
    return ret.isbst;
  }

  public static SubTreeResult isSubTreeBST(BinaryTreeNode<Integer> tree) {
    if (tree == null) return new SubTreeResult(true, null, null);

    SubTreeResult leftTree = null, rightTree = null;
    leftTree = isSubTreeBST(tree.left);
    rightTree = isSubTreeBST(tree.right);

    if (!leftTree.isbst || (leftTree.maxval != null && tree.data.compareTo(leftTree.maxval.data) < 0)) {
      return new SubTreeResult(false, null, null);
    }

    if (!rightTree.isbst || (rightTree.minval != null && tree.data.compareTo(rightTree.minval.data) > 0)) {
      return new SubTreeResult(false, null, null);
    }

    SubTreeResult ret = new SubTreeResult(true, leftTree.minval != null ? leftTree.minval : tree, rightTree.maxval != null ? rightTree.maxval : tree);
    return ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
