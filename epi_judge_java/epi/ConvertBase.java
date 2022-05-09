package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;

public class ConvertBase {
  @EpiTest(testDataFile = "convert_base.tsv")

  public static String convertBase(String numAsString, int b1, int b2) {
    // Lesson: forget to test 'A01'
    String digits = "0123456789ABCDF";
    long numDecimal = 0;
    // Convert to decimal
    for (int i = numAsString.charAt(0) == '-' ? 1 : 0; i < numAsString.length(); i++) {
      if (numAsString.charAt(i) < '0' || numAsString.charAt(i) > '9') {
        numDecimal = numDecimal * b1 + (numAsString.charAt(i) - 'A' + 10);
      } else {
        numDecimal = numDecimal * b1 + (numAsString.charAt(i) - '0');
      }
    }

    // convert to base b2
    StringBuilder sb = new StringBuilder();
    long quotient = numDecimal;
    do {
      sb.append(digits.charAt((int) quotient % b2));
      quotient /= b2;
    } while (quotient != 0);
    if (numAsString.charAt(0) == '-') {
      sb.append('-');
    }
    return sb.reverse().toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
