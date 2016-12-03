package assignment1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Frequent {

	// test values of m=50,500,5000,50000
	static int[] m = new int[] {50, 500, 5000, 50000};


	// test values of stdev=1,2,3 (2, 3, 4 in java)
	static int[] stdev = new int[] {2, 3, 4};

	// test values of k=3,4,5,6
	static int[] k = new int[] {3, 4, 5, 6};
	
	static int[] R;
	
	static HashMap<Integer, Integer> hm;
	static HashMap<Integer, Integer> hm2;
	static Iterator<Entry<Integer, Integer>> it;
	
	static void createInput(int[] R, int mu, int STDEV) {
		for(int i = 0; i < R.length; i++) {
			int value = ThreadLocalRandom.current().nextInt(mu - STDEV, mu + STDEV + 1);
			R[i] = value;
			//			System.out.print(value + "  ");
		}
	}
	

	static void calculateFrequencies() {
		hm = new HashMap<Integer, Integer>();

		for(int i = 0; i < R.length; i++) {
			int v = R[i];

			if(hm.containsKey(v)) {
				hm.put(v, hm.get(v) + 1);
			}
			else {
				hm.put(v, 1);
			}
		}
	}
	
	private static void estimateFrequency(int K) {
		hm2 = new HashMap<Integer, Integer>();

		for(int i = 0; i < R.length; i++) {
			int v = R[i];

			if(hm2.containsKey(v)) {
				hm2.put(v, hm2.get(v) + 1);
			}
			else if(hm2.size() < K - 1) {
				hm2.put(v, 1);
			}
			else {
				Set<Integer> keys = hm2.keySet();
				Iterator<Integer> kit = keys.iterator();
				while(kit.hasNext()) {
					int key = kit.next();
					if(hm2.get(key) == 1) {
						kit.remove();
					}
					else {
						hm2.put(key, hm2.get(key) - 1);
					}
				}
			}
		}
	}
	
	private static void calculateErrors(float mk) {
		int fpCounter = 0;
		float errorAvg = 0;

		it = hm2.entrySet().iterator();
		
		while(it.hasNext()) {
			Entry<Integer, Integer> e = it.next();
			int key = e.getKey();
			int value = e.getValue();
			int realValue = hm.get(key);
			int diff = Math.abs(realValue - value);
			errorAvg += diff;
			
			if(value <= mk) {
				fpCounter++;
			}
		}

		System.out.println("amount of false positives: " + fpCounter);
		System.out.println("average error: " + errorAvg/hm2.size());
	}

	public static void main(String[] args) {
		for(int M : m) {
			for(int STDEV : stdev) {
				for(int K : k) {

					float mk = M/K;
					R = new int[M];
					int mu = 100;

					System.out.println("---------------------------------------");
					System.out.println("m = " + M + ", k = " + K + ", stdev = " + STDEV);

					createInput(R, mu, STDEV);
					
					calculateFrequencies();
//					printActual();
					
					estimateFrequency(K);
//					printEstimates();

					calculateErrors(mk);

					System.out.println();
				}
			}
		}
	}


	static void printEstimates() {
		System.out.println();
		System.out.println("approx freq");

		it = hm2.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Integer, Integer> e = it.next();
			System.out.println(e.getKey() + " : " + e.getValue());
		}
	}


	static void printActual() {
		System.out.println();
		System.out.println();
		System.out.println("real frequencies");

		it = hm.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Integer, Integer> e = it.next();
			System.out.println(e.getKey() + " : " + e.getValue());
		}
	}
}
