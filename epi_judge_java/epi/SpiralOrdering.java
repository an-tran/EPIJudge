package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class SpiralOrdering {
  @EpiTest(testDataFile = "spiral_ordering.tsv")

  public static List<Integer>
  matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    int size = squareMatrix.size();
    List<Integer> ret = new ArrayList<>();
    int n = 0, dirInx = 0, i = 0, j = 0;
    int[][] direction = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
    while (n < size ) {
      ret.add(squareMatrix.get(i).get(j));
      squareMatrix.get(i).set(j, 0);
      int ni = direction[dirInx][0] + i;
      int nj = direction[dirInx][1] + j;
      if (ni < 0 || nj < 0 || ni >= size || nj >= size || squareMatrix.get(ni).get(nj) == 0) {
        dirInx = (dirInx + 1) % 4;
      }
      i = direction[dirInx][0] + i;
      j = direction[dirInx][1] + j;
      n++;
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
