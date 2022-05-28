package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {
  @EpiTest(testDataFile = "int_square_root.tsv")

  public static int squareRoot(int k) {
    int start =  0, end = k;
    int middle = k/2;
    if (k < 2) return k;

    while (start < middle) {
      double sr = Math.sqrt(k);
      if ((double) middle < sr) {
        start = middle;
        middle = middle + (end - middle)/2;
      } else if ((double) middle == sr) {
        return middle;
      } else {
        end = middle;
        middle = start + (middle - start)/2;
      }

    }
    return middle;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
