package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    // TODO - you fill in here.
    if (L == null ) return L;

    ListNode<Integer> head = L;
    ListNode<Integer> p1 = L;
    ListNode<Integer> p2 = p1.next;

    int p1Id = 1;
    ListNode<Integer> dummyNode = null;
    while (p1Id  < finish) {
      if (p1Id < start) {
        if (p1Id == start -1) {
          dummyNode = p1;
        }
        p1 = p2;
        p2 = p2.next;
        p1Id++;
        continue;
      }

      ListNode<Integer> tmp = p2.next;
      p2.next = p1;
      p1 = p2;
      p2 = tmp;
      p1Id++;
    }

    if (dummyNode != null) {
      dummyNode.next.next = p2;
      dummyNode.next = p1;
    } else {
      head.next = p2;
    }

    return start == 1 ? p1 : head;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
