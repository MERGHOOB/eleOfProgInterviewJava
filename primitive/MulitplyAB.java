
public class MulitplyAB {


    public static long mulitiply(long x, long y) {

        long sum = 0;

        while (x != 0) {

            if ((x & 1) != 0) { // is lsb is not zero

                sum = add(sum, y);
            }

            x >>>= 1;
            y <<= 1;
        }

        return sum;

    }

    public static long add(long a, long b) {
        long sum = 0, carryin = 0, k = 1, tempA = a, tempB = b;

        while (tempA != 0 || tempB != 0) {
            long ak = a & k;
            long bk = b & k;

            long carryout = (ak & bk) | (ak & carryin) | (bk & carryin);
            sum |= (ak ^ bk ^ carryin);
            carryin = carryout << 1;
            k <<= 1;
            tempB >>>= 1;
            tempA >>>= 1;

        }
        return sum | carryin;
    }

    public static void main(String[] args) {


        System.out.println(mulitiply(7, 3));


    }
}