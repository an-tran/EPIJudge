package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestorWithParent {

  public static BinaryTree<Integer> lca(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    int d0 = nodeDepth(node0);
    int d1 = nodeDepth(node1);
    int diff = Math.abs(d0 - d1);
    BinaryTree<Integer> deeperNode = d0 > d1 ? node0 : node1;
    BinaryTree<Integer> lowerNode = d0 > d1 ? node1 : node0;
    while (diff-- > 0) {
      deeperNode = deeperNode.parent;
    }

    while (deeperNode != null && lowerNode != null) {
      if (deeperNode == lowerNode) {
        return deeperNode;
      }
      deeperNode = deeperNode.parent;
      lowerNode = lowerNode.parent;
    }
    return null;
  }

  public static int nodeDepth (BinaryTree<Integer> node) {
    int depth = 0;
    while (node != null) {
      depth++;
      node = node.parent;
    }
    return depth;
  }

  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> lca(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
