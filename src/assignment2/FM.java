package assignment2;

public class FM {
	
	public static int h1(int x) {
		return (2*x + 1)%32;
	}
	public static int h2(int x) {
		return (3*x + 7)%32;
	}
	public static int h3(int x) {
		return (4*x)%32;
	}
	
	public static boolean[] intToBitArray(int x, int arraySize) {
		boolean[] bits = new boolean[arraySize];
		for (int i = arraySize - 1; i >= 0; i--) {
	        bits[i] = (x & (1 << i)) != 0;
	    }
		return bits;
	}

	public static void main(String[] args) {
		int[] stream = new int[] {3, 1, 4, 1, 5, 9, 2, 6, 5};
		int arraySize = 32;
		boolean[] bitmap = new boolean[arraySize];
		
		for(int i = 0; i < stream.length; i++) {
			int v = stream[i];
			System.out.println(v);

			boolean[] bits = intToBitArray(v, arraySize);
			for(int j = 0; j < bits.length; j++) {
				if(bits[j]) {
					bitmap[j] = true;
					break;
				}
			}
			
			System.out.println("h1: " + h1(v));
			System.out.println("h2: " + h2(v));
			System.out.println("h3: " + h3(v));
			System.out.println();
		}
	}

}
