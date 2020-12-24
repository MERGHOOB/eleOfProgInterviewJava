import java.util.*;


public class SameWeightClosestInteger {


    /**
     * 92: (1011100)base2 == there are 4 1's so weight is 4
     * <p>
     * to get the sameweight closest integer,
     * <p>
     * <p>
     * one approach : to use all check values for x-1, x+1, x-2, x+2 .....
     * <p>
     * this approach is costly in case of input like 2pow30 => the next pow is 2 pow29
     * it will compute the value from 2pow29 to 2pow30 and 2pow30 to 2pow29-1
     * <p>
     * <p>
     * now go with the LSB, flip that with rightmost different bit then LSB.
     * it may produce wrong result consider 111 ==> 1110 but the closest result for 111 ==> 1011
     * <p>
     * so what should we do, if we focus on that we should flip the nearest bit which differes
     * <p>
     * consider this : lets bit index k1 > k2; abs value difference flipping this: 2 pow k1 - 2powk2, to minimize this
     * k1 should be small and k2 be as maximum as possible such that k1>k2
     * <p>
     * this means smallest k1 will be the rightmost bit that's different from LSB and k2 must be very next bit.
     * in summary the correct approach is to swap the two right most consecutive bits that differe
     * <p>
     * <p>
     * Assuming that we are using non-negative value;
     */

    private static final int unsignedBits = 63;

    public int getClosestIntegerOfSameWeight(int x) {

        for (int i = 0; i < unsignedBits - 1; i++) {

            int statusOfIthBit = (x >>> i) & 1;
            int statusOfIplus1Bit = (x >>> (i + 1)) & 1;

            if (statusOfIthBit != statusOfIplus1Bit) {
                long ithMask = 1L << i;
                long iPlus1Mask = 1L << (i + 1);

                x ^= ithMask | iPlus1Mask; // xor with both mask to flip the value of x
                return x;
            }


        }
        throw new IllegalArgumentException("All bits are 0s or 1s");

    }

}