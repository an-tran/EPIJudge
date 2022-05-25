package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class IsTreeSymmetric {

  public static class NodeValueExtra {
    BinaryTreeNode<Integer> node;
    Integer level;
    Integer position;

    public NodeValueExtra(BinaryTreeNode<Integer> node,  Integer level, Integer position) {
      this.node = node;
      this.level = level;
      this.position = position;
    }
  }

  @EpiTest(testDataFile = "is_tree_symmetric.tsv")
  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    if (tree == null) return true;
    return isSubTreeSymmetric(tree.left, tree.right);
  }

  private static boolean isSubTreeSymmetric(BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right) {
    if (left == null && right == null) return true;
    if (left == null && right != null) return false;
    if (left != null && right == null) return false;
    if (left.data != right.data) return false;
    return isSubTreeSymmetric(left.left, right.right) && isSubTreeSymmetric(left.right, right.left);
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
