package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class SpiralOrdering {
  @EpiTest(testDataFile = "spiral_ordering.tsv")

  public static List<Integer>
  matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    // TODO - you fill in here.
    int nr = squareMatrix.size();
    List<Integer> ret = new ArrayList<>();
    int i = 0, j = 0;
    while (nr > 0 ) {
      int n = 0;
      while (n++ < nr) {
        ret.add(squareMatrix.get(i).get(j++));
      }
      j--;
      n = nr - 1;
      while (n-- > 0) {
        ret.add(squareMatrix.get(++i).get(j));
      }

      n = nr - 1;
      while (n-- > 0) {
        ret.add(squareMatrix.get(i).get(--j));
      }
      n = nr - 2;
      while (n-- > 0) {
        ret.add(squareMatrix.get(--i).get(j));
      }
      nr = nr - 2;
      j++;
    }
    return ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrdering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
