import java.util.HashMap;
import java.util.Scanner;

class HW4 {
	public static int n, k;
	public static int[] c;
	
	public static void main(String[] args) {
		long a1 = 101;
		long p1 = 2147483647;	// 2^31-1

		long a2 = 107;
		long p2 = 2047483717;	// another bloody large prime
		
		Scanner in = new Scanner(System.in);
		in.useDelimiter("[^0-9]+");
		n = in.nextInt();
		k = in.nextInt();
		if(k > n){
			System.out.println(-1);
			in.close();
			return;
		}
		c = new int[n];
		for(int i = 0; i < n; i++){
			c[i] = in.nextInt();
		}
		in.close();

		// first hashes
		HashMap<Long, Integer> hashes1 = new HashMap<Long, Integer>();
		long[] first1 = firstHash(p1, a1);
		long h1 = first1[0], a1_k1p = first1[1];
		hashes1.put(h1, 0);
		HashMap<Long, Integer> hashes2 = new HashMap<Long, Integer>();
		long[] first2 = firstHash(p2, a2);
		long h2 = first2[0], a2_k1p = first2[1];
		hashes2.put(h2, 0);
		
		// rolling hash
		int winI = n, winJ = n;
		for(int j = 1; j < n-k+1; j++){
			// update hash
			h1 = updateHash(h1, j, p1, a1, a1_k1p);
			h2 = updateHash(h2, j, p2, a2, a2_k1p);
			Integer i1 = hashes1.put(h1, j);
			Integer i2 = hashes2.put(h2, j);
			// check
			boolean match = false;
			if(i1 != null){
				hashes1.put(h1, i1);
				match = true;
			}
			if(i2 != null){
				hashes2.put(h2, i2);
				match &= true;
			}
			if(match && i1.equals(i2)){
				if(i1 < winI){
					winI = i1;
					winJ = j;
				}
			}
		}
		
		if(winI == n){
			System.out.println(-1);
		}
		else{
			System.out.println((winI+1) + " " + (winJ+1));
		}
	}
	
	public static long[] firstHash(long p, long a){
		long result = 0;
		long a_i = 0;
		for(int i = 0; i < k; i++){
			if(i == 0){ a_i = 1; }
			else{ a_i = (a_i*a) % p; }
			result = (result + (c[k-1 - i]*a_i)%p) % p;
		}
		return new long[]{result, a_i};
	}
	
	public static long updateHash(long h, int j, long p, long a, long a_k1p){
		long first = ((c[j-1]%p) * a_k1p) % p ;
		h = ((h - first)%p + p) % p;
		h = (h * a) % p;
		h = h + c[j+k-1];
		return h;
	}

}