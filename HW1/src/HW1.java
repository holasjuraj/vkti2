import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

class HW1 {
	
	int N = 100;
	PriorityQueue<Edge> E;
	PriorityQueue<Edge> open;
	
	public HW1(){
		// initialize
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		in.nextLine();
		E = new PriorityQueue<Edge>(N*N, EdgeComparator);
		open = new PriorityQueue<Edge>(N*N, EdgeComparator);
		for(int u = 0; u < N; u++){
			for(int v = 0; v < u; v++){
				int weight = in.nextInt();
				E.add(new Edge(v, u, weight));
			}
			in.nextLine();
		}
		in.close();
		
		// find minimum spanning tree
		int[] vDegs = new int[N];
		int v = 0;
		HashSet<Integer> usedV = new HashSet<Integer>(N*2);
		usedV.add(v);
		
		while(usedV.size() < N){
			open.addAll(takeOutgoingEdges(E, v));
			Edge newE = open.poll();
			int u;
			if(usedV.contains(newE.v1)){
				u = newE.v1; v = newE.v2;
			}
			else{
				u = newE.v2; v = newE.v1;
			}
			usedV.add(v);
			vDegs[v]++;
			vDegs[u]++;
			takeOutgoingEdges(open, v);
		}
		
		// find maximal degree
		int maxI = 0, maxD = 0;
		for(int i = 0; i < N; i++){
			if(vDegs[i] > maxD){
				maxD = vDegs[i];
				maxI = i;
			}
		}
		
		// print
		for(int i = 0; i < N; i++){
			int d = vDegs[i];
			if(i != maxI){ d -= 2; }
			System.out.print("" + d + " ");
		}
	}
	
	ArrayList<Edge> takeOutgoingEdges(Iterable<Edge> edges, int v){
		ArrayList<Edge> result = new ArrayList<Edge>(N);
		for(Iterator<Edge> it = edges.iterator(); it.hasNext(); ){
			Edge e = it.next();
			if(e.hasVertex(v)){
				result.add(e);
				it.remove();
			}
		}
		return result;
	}
	
	class Edge{
		int v1, v2, weight;
		public Edge(int v1, int v2, int weight){
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}
		public boolean hasVertex(int v){
			return v==v1 || v==v2;
		}
	}
	
	Comparator<Edge> EdgeComparator = new Comparator<Edge>() {
		public int compare(Edge arg0, Edge arg1) {
			return arg0.weight - arg1.weight;
		}
	};

	public static void main(String[] args) {
		new HW1();
	}

}
