package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;
public class TreeFromPreorderInorder {
  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")

  public static BinaryTreeNode<Integer>
  binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
    return buildTree(preorder, inorder, 0, preorder.size() - 1, 0, inorder.size() - 1);
  }

  private static BinaryTreeNode<Integer> buildTree(List<Integer> preorder, List<Integer> inorder, int preStart, int preEnd, int inStart, Integer inEnd) {
    if (inStart > inEnd || preStart > preEnd ) return  null;
    BinaryTreeNode<Integer> root;
    root = new BinaryTreeNode<>(preorder.get(preStart), null, null);
    try {
      int rootIdx = inStart;
      int rightPredStart = preStart;
      while (inorder.get(rootIdx) != root.data) {
        rootIdx++;
        rightPredStart++;
      }

      root.left = buildTree(preorder, inorder, preStart + 1, rightPredStart, inStart, rootIdx - 1);
      root.right = buildTree(preorder, inorder, rightPredStart + 1, preEnd, rootIdx + 1, inEnd);

    } catch (Exception ex) {
      return null;
    }

    return root;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
//    binaryTreeFromPreorderInorder(Arrays.asList(1, 2, 3), Arrays.asList(3, 2, 1));
//    binaryTreeFromPreorderInorder(Arrays.asList(3, 2, 1), Arrays.asList(3, 2, 1));
  }
}
