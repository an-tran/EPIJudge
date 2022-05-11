package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class ReverseWords {

  public static void reverseWords(char[] input) {
    // alices likes bob
    // bob sekil secila
    int i =0, j = input.length - 1;
    while (i < j) {
      char tmp = input[i];
      input[i] = input[j];
      input[j] = tmp;
      i++; j--;
    }

    int wordEndIdx = 0;
    i = 0;
    j = 0;
    while (wordEndIdx < input.length) {
      while (wordEndIdx < input.length && !Character.isSpace(input[wordEndIdx])) {
        wordEndIdx++;
      }
      j = wordEndIdx - 1;
      while (i < j) {
        char tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
        i++; j--;
      }
      while (wordEndIdx < input.length && Character.isSpace(input[wordEndIdx])) {
        wordEndIdx++;
      }
      i = wordEndIdx;
    }
    return;
  }
  @EpiTest(testDataFile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
