package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class SpiralOrdering {
  @EpiTest(testDataFile = "spiral_ordering.tsv")

  public static List<Integer>
  matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    int nr = squareMatrix.size();
    int n = 0;
    List<Integer> ret = new ArrayList<>();
    int dirInx = 0;
    int[][] direction = new int[][] { new int[] {0, 1}, new int[] {1, 0}, new int[]{0, -1}, new int[]{-1, 0} };
    int i = 0, j = 0;
    while (n < nr * nr ) {
      ret.add(squareMatrix.get(i).get(j));
      squareMatrix.get(i).set(j, 0);
      int[] dir = direction[dirInx];
      int ni = dir[0] + i;
      int nj = dir[1] + j;
      if ((dirInx == 3 && ni <= 0) || nj < 0 || ni >= nr || nj >= nr || squareMatrix.get(ni).get(nj) == 0) {
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
