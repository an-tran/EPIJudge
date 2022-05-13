package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    if (L == null ) return L;

    int p1Id = 1;
    ListNode<Integer> dummyNode = new ListNode<>(0, L);
    ListNode<Integer> p1 = dummyNode.next;
    ListNode<Integer> p2 = p1.next;
    while (p1Id < finish && (p1 != null && p2 != null)) {
      if (p1Id >= start) {
        ListNode<Integer> tmp = p2.next;
        p2.next = p1;
        p1 = p2;
        p2 = tmp;
      } else {
        if (p1Id == start - 1) {
          dummyNode = p1;
        }
        p1 = p2;
        p2 = p2.next;
      }
      p1Id++;
    }

    dummyNode.next.next = p2;
    dummyNode.next = p1;

    return start == 1 ? p1 : L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
