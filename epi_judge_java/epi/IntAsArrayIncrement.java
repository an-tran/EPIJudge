package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    // TODO - you fill in here.
    Integer[] sum = new Integer[A.size() + 1];
    int carry = 1;
    for (int i = A.size() - 1; i >= 0; i--) {
      int p = A.get(i) + carry;
      if (p < 10) {
        carry = 0;
        sum[i+1] = p;
      } else {
        carry = 1;
        sum[i+1] = 0;
      }
    }
    if (carry == 1) {
      sum[0] = 1;
      return Arrays.asList(sum);
    } else {
      return Arrays.asList(Arrays.copyOfRange(sum, 1, sum.length));
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
