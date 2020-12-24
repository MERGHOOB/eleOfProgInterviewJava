
public class BitReverser {


	private static int cacheSize = 66000;
	private static int [] preComputedReverse = new int[cacheSize];
	public static long reverse(long x) {

		final int WORD_SIZE = 16;
		final int BIT_MASK = 0xFFFF;

		return preComputedReverse[(int) (x & BIT_MASK)] << 3*WORD_SIZE
				| preComputedReverse[(int)(x >>> WORD_SIZE) & BIT_MASK] <<2*WORD_SIZE
				| preComputedReverse[(int)(x >>> 2*WORD_SIZE) & BIT_MASK] <<WORD_SIZE
				| preComputedReverse[(int)(x >>> 3*WORD_SIZE) & BIT_MASK] ;
	}

	public BitReverser() {
		for(int i = 0; i<cacheSize; i++) {
			preComputedReverse[i] = reverseInt(i);
		}
	}
	public static int reverseInt(int x) {
		
		int i  = 15, j = 0;
		while( i > j) {

			if(((x >>> i) & 1) != ((x>>>j) &1)) {
				// i th and jth bit differes
				// we will flip them
				int bitMask = (1 << i) | (1<< j);
				x ^= bitMask;
			}

			j++; i--;
		}
		return x;

	}


	public static void main(String [] args) {

		
	}
}