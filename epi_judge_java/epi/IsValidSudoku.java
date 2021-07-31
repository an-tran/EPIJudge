package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class IsValidSudoku {
  @EpiTest(testDataFile = "is_valid_sudoku.tsv")

  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
    for (int i = 0; i < 9; i++) {
      if (hasDuplicate(partialAssignment, i, i, 0, 8)) {
        return false;
      }
    }

    for (int i = 0; i < 9; i++) {
      if (hasDuplicate(partialAssignment, 0, 8, i, i)) {
        return false;
      }
    }

    for (int i = 0; i < 3; i++) {  //  0 0 3 6
      for (int j = 0; j < 3; j++) {
        if (hasDuplicate(partialAssignment, i * 3, i * 3 + 2, j * 3, j * 3 + 2)) {
          return false;
        }
      }
    }
    return true;
  }

  private static boolean hasDuplicate(List<List<Integer>> partialAssignment, int rstart, int rend, int cstart, int cend) {
    List<Boolean> founded = new ArrayList<>(Collections.nCopies(10, Boolean.FALSE));
    for (int i = rstart; i <= rend; i++) {
      for (int j = cstart; j <= cend; j++) {
        int val = partialAssignment.get(i).get(j);
        if (founded.get(val) && val != 0) return true;
        else founded.set(val, true);
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
