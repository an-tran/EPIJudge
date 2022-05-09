package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {

  public static String intToString(int x) {
    StringBuilder sb = new StringBuilder();
    boolean negative = x < 0 ? true : false;
    int val = Math.abs(x);
    while (val != 0) {
      sb.append(Math.abs(val % 10));
      val /= 10;
    }
    sb.append(val);
    if (negative) sb.append("-");

    String s = sb.reverse().toString();
    return s;
  }
  public static int stringToInt(String s) {
    int sign = 1;
    int val = 0;
    int i = 0;
    if (s.charAt(0) == '-') {
      sign = -1;
      i = 1;
    } else if (s.charAt(0) == '+') {
      i = 1;
    }
    for (; i < s.length(); i++) {
      if (s.charAt(i) < '0' || s.charAt(i) > '9') throw new NumberFormatException();
      val = val * 10 + (s.charAt(i) -  '0');
    }
    return sign * val;
  }
  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (Integer.parseInt(intToString(x)) != x) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    intToString(Integer.MIN_VALUE);
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
