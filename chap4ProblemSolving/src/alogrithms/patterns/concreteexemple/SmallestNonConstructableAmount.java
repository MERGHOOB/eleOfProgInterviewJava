package alogrithms.patterns.concreteexemple;

import java.util.Arrays;

/**
 * Given an array of positive integers (representing coins), find the smallest value that cannot be constructed from those integers.
 * Reference: Elements of Programming Interviews in java, page 31
 */
public class SmallestNonConstructableAmount {


    static int smallestNonConstructableAmount(int[] coins) {

        if (coins.length == 0) {
            return 0;
        }
        Arrays.sort(coins);
        int maxConstructableValue = 0;

        for (int coin : coins) {
            if (coin > maxConstructableValue + 1) {
                break;
            }
            maxConstructableValue += coin;
        }


        return maxConstructableValue + 1;
    }

}
