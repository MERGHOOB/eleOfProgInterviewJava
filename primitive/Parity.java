
public class Parity {

	int cacheSize =  65536;
	public static int [] preComputedParity = new int[65536];

	public static short resParity(long x) {
		final int WORD_SIZE = 16;

		final int BIT_MASK = 0xFFFF;



		return (short) (
			 preComputedParity[(int) (x>>> (3*WORD_SIZE)) & BIT_MASK]
			 ^ preComputedParity[(int) (x>>>(2*WORD_SIZE)) & BIT_MASK]
			 ^ preComputedParity[(int) (x >>> WORD_SIZE) & BIT_MASK]
			 ^ preComputedParity[(int) x & BIT_MASK]
			);
	}

	public Parity() {
		for(int i = 0; i<cacheSize; i++) {
			preComputedParity[i] = getParity(i);
		}
	}
	private int getParity(int x) {
		int result = 0;
		while(x !=0) {
			result ^=1;
			x &=(x-1); // Drop the lowest set bit of x
		}
		return result;
	}


	public static void main(String [] args) {

		Parity p = new Parity();
		System.out.println("---");
		System.out.println(p.resParity(1000003));


	}

}

