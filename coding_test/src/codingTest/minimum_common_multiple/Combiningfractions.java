package minimum_common_multiple;

import java.util.*;

public class Combiningfractions {

    public static void main(String[] args) {
        int[] nums = {18, 42, 68};
        int lcm = lcm(nums);
        System.out.println("최소공배수: " + lcm);
    }

    public static int lcm(int[] nums) {
        Map<Integer, Integer> primeFactors = new HashMap<>();

        for (int num : nums) {
            Map<Integer, Integer> factors = primeFactorize(num);

            for (int factor : factors.keySet()) {
                int currentMax = primeFactors.getOrDefault(factor, 0);
                int newMax = Math.max(currentMax, factors.get(factor));

                primeFactors.put(factor, newMax);
            }
        }

        int lcm = 1;
        for (int factor : primeFactors.keySet()) {
            lcm *= Math.pow(factor, primeFactors.get(factor));
        }

        return lcm;
    }

    public static Map<Integer, Integer> primeFactorize(int num) {
        Map<Integer, Integer> factors = new HashMap<>();

        for (int i = 2; i <= Math.sqrt(num); i++) {
            while (num % i == 0) {
                factors.put(i, factors.getOrDefault(i, 0) + 1);
                num /= i;
            }
        }

        if (num > 1) {
            factors.put(num, factors.getOrDefault(num, 0) + 1);
        }

        return factors;
    }
}
