package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Parity {
  @EpiTest(testDataFile = "parity.tsv")
  public static short parity(long x) {
    // TODO - you fill in here.
    short count = 0;
//    while (x != 0) {
//      long y = x & ~(x - 1);
//      if (y > 0) {
//        count++;
//        x = x/(y * 2);
//      }
//    }
    short result = 0;
    while (x != 0) {
      result ^= 1;
      x &= (x - 1);
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
