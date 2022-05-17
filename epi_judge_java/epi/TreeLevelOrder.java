package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class TreeLevelOrder {
  @EpiTest(testDataFile = "tree_level_order.tsv")

  public static List<List<Integer>>
  binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    // record len at each level
    // put tree to data queue
      if (tree == null) return Collections.emptyList();
    List<List<Integer>> ret = new ArrayList<>();
    Deque<BinaryTreeNode<Integer>> queue = new LinkedList<>();
    BinaryTreeNode<Integer> lastNode = tree;
    List<Integer> currentLevel = new ArrayList<>();

    queue.offerLast(tree);
    while (!queue.isEmpty()) {
        BinaryTreeNode<Integer> node = queue.pollFirst();
        if (node.left != null) queue.offerLast(node.left);
        if (node.right != null) queue.offerLast(node.right);
        currentLevel.add(node.data);
        if (node == lastNode) {
          ret.add(currentLevel);
          currentLevel = new ArrayList<>();
          lastNode = queue.peekLast()
          ;
        }

    }

    return ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
