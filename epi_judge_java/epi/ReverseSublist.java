package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    if (L == null ) return L;

    int headIdx = 1;
    ListNode<Integer> dummyNode = new ListNode<>(0, L);
    ListNode<Integer> subHead = dummyNode;
    while (headIdx++ < start) {
      subHead =subHead.next;
    }
    // headIdx = start

    ListNode<Integer> iter = subHead.next;
    while (start++ < finish) {
      ListNode<Integer> tmp = iter.next;
      iter.next = tmp.next;
      tmp.next = subHead.next;
      subHead.next = tmp;
    }

    return dummyNode.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
