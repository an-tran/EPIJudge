package epi;
import java.util.HashMap;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsStringPermutableToPalindrome {
  @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

  public static boolean canFormPalindrome(String s) {
    Map<Character, Integer> charMap = new HashMap<>();
    for (int i = 0; i < s.length(); i++){
      charMap.put(s.charAt(i), charMap.getOrDefault(s.charAt(i), 0) + 1);
    }

    int oddEntry = 0;
    for (Integer c : charMap.values()) {
      if ((c % 2) == 1) oddEntry++;
    }
    
    return oddEntry <= 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
