package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.List;
public class AbsentValueArray {

  public static int findMissingElement(Iterable<Integer> stream) {
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    long sum = 0;
    for (Integer n : stream ){
      sum += n;
      min = Math.min(min, n);
      max = Math.max(max, n);
    }
    long fullSum = (max - min + 1) * (max + min)/2;

    return (int) (fullSum - sum);
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
