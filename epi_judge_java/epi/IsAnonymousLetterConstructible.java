package epi;
import java.util.HashMap;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsAnonymousLetterConstructible {
  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

  public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                          String magazineText) {
    Map<Character, Integer> charCount = new HashMap<>();
    for (int i = 0; i < magazineText.length(); i++) {
      charCount.put(magazineText.charAt(i), charCount.getOrDefault(magazineText.charAt(i), 0) + 1);
    }

    for (int i = 0 ; i < letterText.length(); i++) {
      if (!charCount.containsKey(letterText.charAt(i)) || charCount.get(letterText.charAt(i)) == 0) return false;
      charCount.put(letterText.charAt(i), charCount.get(letterText.charAt(i)) - 1);
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
