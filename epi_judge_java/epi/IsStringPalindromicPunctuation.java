package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsStringPalindromicPunctuation {
  @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")

  public static boolean isPalindrome(String s) {
    // compare alpha numeric letter
    // case insensitive
    // A man, a plan, a canal, Panama
    String lows = s.toLowerCase();
    int i = 0, j = lows.length() - 1;
    while (i < j) {
      if (Character.isLetterOrDigit(lows.charAt(i)) &&
              Character.isLetterOrDigit(lows.charAt(j))) {
        if (lows.charAt(i) != lows.charAt(j)) return false;
        i++;
        j--;
      }

      if (!Character.isLetterOrDigit(lows.charAt(i))) i++;
      if (!Character.isLetterOrDigit(lows.charAt(j))) j--;
    }
    return true;
  }

  public static void main(String[] args) {
//    isPalindrome("7}y");
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
