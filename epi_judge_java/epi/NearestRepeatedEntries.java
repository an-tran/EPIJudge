package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class NearestRepeatedEntries {

  private static class Pair {
    int x;
    int y;
    int minDist = Integer.MAX_VALUE;
    public Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public void setData(int x, int y) {
      this.x = x;
      this.y = y;
      minDist = Math.min(minDist, y - x);
    }
  }

  @EpiTest(testDataFile = "nearest_repeated_entries.tsv")
  public static int findNearestRepetition(List<String> paragraph) {
    int ret = Integer.MAX_VALUE;
    Map<String, Pair> wordDist = new HashMap<>();
    for (int i = 0 ; i < paragraph.size(); i++) {
      String word = paragraph.get(i);

      if (wordDist.containsKey(word)) {
        Pair minPair = wordDist.get(word);
        minPair.setData(minPair.y, i);
        ret = Math.min(ret, minPair.minDist);
      } else {
        wordDist.put(word, new Pair(i, i));
      }
    }

    return ret == Integer.MAX_VALUE ? -1 : ret;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
