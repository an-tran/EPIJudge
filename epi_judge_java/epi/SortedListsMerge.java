package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {
    // TODO - you fill in here.
    // loop (L1 != null || L2 != null)
    // compare L1.data vs L2.data
    //

    if (L1 == null) return L2;
    if (L2 == null) return L1;

    ListNode<Integer> head = null;
    if (L1.data <= L2.data) {
      head = L1;
      L1 = L1.next;
    } else {
      head = L2;
      L2 = L2.next;
    }
    ListNode<Integer> current = head;

    while (L1 != null || L2 != null) {
      if (L1 != null && L2 != null) {
        if (L1.data <= L2.data) {
          current.next = L1;
          L1 = L1.next;
        } else {
          current.next = L2;
          L2 = L2.next;
        }
        current = current.next;
        continue;
      }
      current.next = L1 == null ? L2 : L1;
      break;
    }
    return head;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
