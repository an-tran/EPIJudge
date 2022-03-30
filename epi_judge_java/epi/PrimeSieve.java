package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class PrimeSieve {
  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
    // TODO - you fill in here.
    List<Integer> ret = new ArrayList<>();
    boolean[] notPrime = new boolean[n+1];
    Arrays.fill(notPrime, false);
    for (int i = 2; i < n + 1; i++) {
      if (notPrime[i]) continue;

      if (isPrime(i)) {
        ret.add(i);
        int multiple = i ;
        int c = 2;
        while (multiple * c <= n) {
          notPrime[multiple * c] = true;
          c++;
        }
      }
    }
    return ret;
  }

  private static boolean isPrime(int i) {
    for (int j = 2; j <= Math.sqrt(i); j++) {
      if (i % j == 0) return false;
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.printf("%b\n", 3 == Math.sqrt(9));
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
