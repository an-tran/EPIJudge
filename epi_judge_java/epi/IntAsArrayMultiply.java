package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class IntAsArrayMultiply {
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    int sign = num1.get(0) * num2.get(0) > 0 ? 1 : -1;
    num1.set(0, Math.abs(num1.get(0)));
    num2.set(0, Math.abs(num2.get(0)));
    Collections.reverse(num1);
    Collections.reverse(num2);
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < num1.size() + num2.size(); i++) {
      result.add(0);
    }
    for (int i = 0; i < num2.size(); i++) {
      for (int j = 0; j < num1.size(); j++) {
        int mul = num1.get(j) * num2.get(i) + result.get(i + j);
        result.set(i + j, mul % 10);
        result.set(i + j + 1, result.get(i + j + 1) + mul / 10);
      }
    }
    for (int i = result.size() - 1; i >= 0 ; i--) {
      if (result.get(i) != 0) {
        break;
      }
      result.remove(i);
    }
    Collections.reverse(result);
    if (result.size() > 0) {
      result.set(0, result.get(0) * sign);
    } else  {
      result.add(0);
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
