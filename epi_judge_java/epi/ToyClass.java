package epi;

import java.util.Arrays;
import java.util.function.BiFunction;

public class ToyClass {
    public static void main(String[] args) {
        int[] unsort = new int[]{ 3, 2, 5, 4};
        System.out.println(Arrays.toString(unsort));
        Arrays.sort(unsort);
        System.out.println(Arrays.toString(unsort));
        System.out.println("c".compareTo("d"));
        System.out.println("c".compareTo("C"));
        System.out.println('0');
        System.out.println('1');
        System.out.println('9' - '0');
        System.out.println(Integer.MAX_VALUE + 1);
        System.out.printf("%x %n", Integer.MIN_VALUE);
        System.out.printf("%x %n", Integer.MAX_VALUE);
        System.out.printf("%x %n", -1);

        String a = "D0032";
        String b = "D0033";
        String c = "D0032";

        System.out.println("===================");
        measureString((s1, s2) -> s1.equals(s2), a, b, "Equals ");
        measureString((s1, s2) -> s1.equalsIgnoreCase(s2), a, b, "EqualsIngoreCase ");
        measureString((s1, s2) -> s1.equals(s2), a, c,"Equals ");
        measureString((s1, s2) -> s1.equalsIgnoreCase(s2), a, c,"EqualsIngoreCase ");
    }

    public static void measureString(BiFunction<String, String, Boolean> compFunc, String s1, String s2, String msg) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            compFunc.apply(s1, s2);
        }
        System.out.println(msg + "Elapsed time: \t\t" + (System.currentTimeMillis() - start) + " ms");
    }

}
