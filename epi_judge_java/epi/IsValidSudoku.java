package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;
public class IsValidSudoku {
  @EpiTest(testDataFile = "is_valid_sudoku.tsv")

  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
    boolean[] set = new boolean[10];
    Arrays.fill(set, false);
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        int idx = partialAssignment.get(i).get(j);
        if (idx == 0) continue;
        if (set[idx]) return false;
        else set[idx] = true;
      }
      Arrays.fill(set, false);
    }

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        int idx = partialAssignment.get(j).get(i);
        if (idx == 0) continue;
        if (set[idx]) return false;
        else set[idx] = true;
      }
      Arrays.fill(set, false);
    }

    for (int i = 0; i < 9; i+=3) {  //  0 0 3 6
      for (int j = 0; j < 9; j+=3) { //
        for (int k = i; k < 3 + i; k++) {
          for (int l = j; l < 3 + j; l++) {
            int idx = partialAssignment.get(k).get(l);
            if (idx == 0) continue;
            if (set[idx]) return false;
            else set[idx] = true;
          }
        }
        Arrays.fill(set, false);
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
