package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DeleteKthLastFromList {
  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
    ListNode<Integer> dummy = new ListNode<>(0, L);
    ListNode<Integer> iter = L;
    while (k > 0) {
      iter = iter.next;
      k--;
    }

    while (iter != null) {
      dummy = dummy.next;
      iter = iter.next;
    }

    ListNode<Integer> knode = dummy.next;
    dummy.next = knode.next;

    // dummy -> 1 2 3 4 5 6 7
    return knode == L ? dummy.next : L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
