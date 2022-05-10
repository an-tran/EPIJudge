package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class ReplaceAndRemove {

  public static int replaceAndRemove(int size, char[] s) {
    int firstb = 0;
    int na = 0;

    // move b to end
    // Example: a b b e d b c -> a e d c b b b
    int nb = 0;
    for (int i = 0; i < size; i++) {
      if (s[i] != 'b') {
        if (s[i] == 'a') na++;
        char tmp = s[firstb];
        s[firstb] = s[i];
        s[i] = tmp;
        firstb++;
      } else {
        nb++;
      }
    }


    // replace a with 2 d
    int replaceIdx = size + na - nb - 1; // mistake size + 2 * na - nb -1
    for (int i = size - nb - 1; i >= 0; i--) { // mistake i > 0
      if (s[i] == 'a') {
        s[replaceIdx--] = 'd';
        s[replaceIdx--] = 'd';
      } else {
        s[replaceIdx--] = s[i];
      }
    }
    return size + na - nb;
  }
  @EpiTest(testDataFile = "replace_and_remove.tsv")
  public static List<String>
  replaceAndRemoveWrapper(TimedExecutor executor, Integer size, List<String> s)
      throws Exception {
    char[] sCopy = new char[s.size()];
    for (int i = 0; i < size; ++i) {
      if (!s.get(i).isEmpty()) {
        sCopy[i] = s.get(i).charAt(0);
      }
    }

    Integer resSize = executor.run(() -> replaceAndRemove(size, sCopy));

    List<String> result = new ArrayList<>();
    for (int i = 0; i < resSize; ++i) {
      result.add(Character.toString(sCopy[i]));
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReplaceAndRemove.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
