package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class DoTerminatedListsOverlap {

  public static ListNode<Integer>
  overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
    int len0 = 0;
    int len1 = 0;
    ListNode<Integer> head0 = l0;
    ListNode<Integer> head1 = l1;
    while (l0 != null) {
      len0++;
      l0 = l0.next;
    }
    while (l1 != null) {
      len1++;
      l1 = l1.next;
    }

    ListNode<Integer> longlist = len0 > len1 ? head0 : head1;
    ListNode<Integer> shortlist = len0 > len1 ? head1 : head0;
    int longlen = Math.max(len0, len1);
    int shortlen = Math.min(len0, len1);
    while (shortlen > 0 ) {
      if (longlen > shortlen) {
        longlist = longlist.next;
        longlen--;
      } else {
        if (longlist == shortlist) return longlist;
        longlist = longlist.next;
        shortlist = shortlist.next;
        shortlen--; longlen--;
      }
    }
    return null;
  }

  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
