package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
public class AbsentValueArray {
  private static int N_BUCKET = 1 << 16;

  public static int findMissingElement(Iterable<Integer> stream) {
    int[] buckets = new int[N_BUCKET];
    Iterator<Integer> it = stream.iterator();
    while (it.hasNext()) {
      int bucketIdx = it.next() >>> 16;
      ++buckets[bucketIdx]; 
    }

    for (int i = 0; i < N_BUCKET; i++) {
      if (buckets[i] == N_BUCKET) {
        continue;
      }

      BitSet bitvec = new BitSet();
      Iterator<Integer> subit = stream.iterator();
      while (subit.hasNext()) {
        int val = subit.next();
        if (i == (val >>> 16)) {
          bitvec.set(val & 0xFFFF);
        }
      }

      for (int j = 0; j < bitvec.length(); j++) {
        if (!bitvec.get(j)) {
          return i << 16 | j;
        }
      }
    }
    return 0;
  }

  @EpiTest(testDataFile = "absent_value_array.tsv")
  public static void findMissingElementWrapper(List<Integer> stream)
      throws Exception {
    try {
      int res = findMissingElement(stream);
      if (stream.stream().filter(a -> a.equals(res)).findFirst().isPresent()) {
        throw new TestFailure(String.valueOf(res) + " appears in stream");
      }
    } catch (IllegalArgumentException e) {
      throw new TestFailure("Unexpected no missing element exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AbsentValueArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
