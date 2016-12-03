package assignment1;

public class Bloom {

	public static void main(String[] args) {
		int n = 20;
		int[] x = new int[] {14, 8, 22, 9, 6};
		
		int[] y = new int[n];
		
		for(int i : x) {
			int h1 = (((3*i + 1)%29)%n);
			int h2 = (((5*i + 7)%23)%n);
			y[h1] = 1;
			y[h2] = 1;
		}
		
		for(int i : y) {
			System.out.print(i + " ");
		}
	}

}
