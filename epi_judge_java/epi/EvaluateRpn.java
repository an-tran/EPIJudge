package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;

public class EvaluateRpn {
  @EpiTest(testDataFile = "evaluate_rpn.tsv")

  public static int eval(String expression) {
    // TODO - you fill in here.
    // 1. parse string form end to begin and put to stack
    // -6,12,+
    final String SEP = ",";
    String[] tokens = expression.split(",");
    Deque<String> stack = new LinkedList<>();

    for (int i = tokens.length - 1; i >= 0; i--) {
      stack.offerFirst(tokens[i]);
    }

    // 2. evaluate
    // 1,2,*,6,3,/,+
    Deque<Integer> operands = new LinkedList<>();

    int left = 0;
    int right = 0;
    while (!stack.isEmpty()) {
      String ele = stack.pollFirst();
      switch (ele) {
        case "+":
          right = operands.pollFirst();
          left = operands.pollFirst();
          operands.offerFirst(left + right);
          break;
        case "-":
          right = operands.pollFirst();
          left = operands.pollFirst();
          operands.offerFirst(left - right);
          break;
        case "*":
          right = operands.pollFirst();
          left = operands.pollFirst();
          operands.offerFirst(left * right);
          break;
        case "/":
          right = operands.pollFirst();
          left = operands.pollFirst();
          operands.offerFirst(left / right);
          break;
        default:
          operands.offerFirst(Integer.parseInt(ele));
          break;
      }
    }

    return operands.pollFirst();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
