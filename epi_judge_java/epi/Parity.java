package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Parity {
  @EpiTest(testDataFile = "parity.tsv")
  public static short parity(long x) {
    // TODO - you fill in here.
    short count = 0;
    while (x != 0) {
      long y = x & ~(x - 1);
      if (y > 0) {
        count++;
        x = x/(y * 2);
      }
    }
    return (short) (count % 2);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
