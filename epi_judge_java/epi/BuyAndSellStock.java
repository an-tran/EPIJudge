package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
    // Invariants
    // low: current lowest price
    // i: current index
    // profitMax: current max profit at [i-1]
    // prices[i] < low => update new low
    // prices[i] - low >  profitMax => update profitMax
    double low = prices.get(0);
    double high = Double.NEGATIVE_INFINITY;
    double profitMax = 0d;
    for (int i = 1; i < prices.size(); i++) {
      if (Double.compare(prices.get(i),low) < 0)
        low = prices.get(i);
      else {
        if (profitMax < (prices.get(i) - low)) {
          profitMax = prices.get(i) - low;
        }
      }
    }

    return profitMax;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
