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
    List<NodeValueExtra> nodeVals = new ArrayList<>();
    Deque<NodeValueExtra> queue = new LinkedList<>();
    int level = 1;
    NodeValueExtra root = new NodeValueExtra(tree, 1, 0);
    queue.offerLast(root);
    nodeVals.add(root);
    while (!queue.isEmpty()) {
      NodeValueExtra ele = queue.pollFirst();
      if (ele.node.left != null) {
        NodeValueExtra left = new NodeValueExtra(ele.node.left, ele.level + 1, ele.position*2);
        nodeVals.add(left);
        queue.offerLast(left);
      }
      if (ele.node.right != null) {
        NodeValueExtra right = new NodeValueExtra(ele.node.right, ele.level + 1, ele.position*2 + 1);
        nodeVals.add(right);
        queue.offerLast(right);
      }
    }

    int start = 1;
    int end = 1;
    level = 2 ;
    while (end < nodeVals.size()){
      if (nodeVals.get(end).level != level) {
        if (!isSymmetricAtLevel(nodeVals, start, end - 1, level)) {
          return false;
        }
        start = end;
        level++;
      }
      end++;
    }

    return isSymmetricAtLevel(nodeVals, start, end - 1, level);
  }

  public static boolean isSymmetricAtLevel(List<NodeValueExtra> nodes, int start, int end, int level) {
    long maxIndex = (long) Math.pow(2, level - 1) - 1;
    if (level == 1) return true;

    if ((end - start + 1) % 2 == 1) return false;
    while (start < end) {
      NodeValueExtra node0 = nodes.get(start++);
      NodeValueExtra node1 = nodes.get(end--);
      if (node0.node.data != node1.node.data ||
              (node0.position + node1.position) != maxIndex) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
