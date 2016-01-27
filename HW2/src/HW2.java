import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

class HW2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		ArrayList<HashSet<Integer>> dupli = new ArrayList<HashSet<Integer>>(3);
		for(int i = 0; i < 3; i++){ dupli.add(new HashSet<Integer>(n)); }
		for(int i = 1; i < n+1; i++){
			int x = in.nextInt(),
				y = in.nextInt();
			dupli.get(y).add(x);
		}		
		in.close();
		n = 0;
		ArrayList<ArrayList<Integer>> stvorce = new ArrayList<ArrayList<Integer>>(3);
		for(int i = 0; i < 3; i++){
			stvorce.add(new ArrayList<Integer>(dupli.get(i)));
			Collections.sort(stvorce.get(i));
			n += stvorce.get(i).size();
		}
		int[] index = new int[3];
		int[] lastPick = new int[3];
		for(int i = 0; i < 3; i++){ lastPick[i] = -42; }
		
		int count = 0;
		for(int i = 0; i < n; i++){
			int[] x = new int[3];
			for(int j = 0; j < 3; j++){
				if(index[j] >= stvorce.get(j).size()){
					x[j] = Integer.MAX_VALUE; }
				else{ x[j] = stvorce.get(j).get(index[j]); }
			}
			
			if(x[0] <= Math.min(x[1], x[2])){
				index[0]++;
				if(x[0] > lastPick[0]+1 && x[0] > lastPick[1]+1){
					lastPick[0] = x[0];
					count++;
				}
			}
			else if(x[1] <= x[2]){
				index[1]++;
				if(x[1] > lastPick[0]+1 && x[1] > lastPick[1]+1 && x[1] > lastPick[2]+1 &&
					(x[1] <= x[0]-2 || x[1] <= x[2]-2)
				){
					lastPick[1] = x[1];
					count++;
				}
			}
			else{
				index[2]++;
				if(x[2] > lastPick[1]+1 && x[2] > lastPick[2]+1){
					lastPick[2] = x[2];
					count++;
				}
			}
		}
		
		System.out.println(count);
	}
}