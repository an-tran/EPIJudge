package epi;

import java.util.Arrays;

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
    }
}
