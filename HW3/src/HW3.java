import java.util.Scanner;

class HW3 {

	public static void main(String[] args) {
		System.out.print("Minimize\nobj:");
		boolean first = true;
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= n; j++){
				int w = in.nextInt();
				if(i == j){ continue; }
				System.out.print(((first)?" ":" + ") + w + " x"+i+"_"+j);
				first = false;
			}
		}
		in.close();
		
		System.out.println("\nSubject to");
		// max one out
		for(int i = 1; i <= n; i++){
			first = true;
			for(int j = 1; j <= n; j++){
				if(i == j){ continue; }
				System.out.print(((first)?"":" + ") + "x"+i+"_"+j);
				first = false;
			}
			System.out.println(" <= 1");
		}
		// max one in
		for(int i = 1; i <= n; i++){
			first = true;
			for(int j = 1; j <= n; j++){
				if(i == j){ continue; }
				System.out.print(((first)?"":" + ") + "x"+j+"_"+i);
				first = false;
			}
			System.out.println(" <= 1");
		}
		// use n-1 edges
		first = true;
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= n; j++){
				if(i == j){ continue; }
				System.out.print(((first)?"":" + ") + "x"+i+"_"+j);
				first = false;
			}
		}
		System.out.println(" = "+(n-1));
		// no cycles
		for(int i = 1; i <= n; i++){
			System.out.println("u"+i+" => 1");
			System.out.println("u"+i+" <= "+n);
			for(int j = 1; j <= n; j++){
				if(i == j){ continue; }
				System.out.println("u"+i + " - " + "u"+j + " + " + n+" x"+i+"_"+j + " <= "+(n-1));
			}
		}
		// binary
		System.out.println("Binary");
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= n; j++){
				if(i == j){ continue; }
				System.out.print(" x"+i+"_"+j);
			}
		}
		System.out.println();
	}
}
/*
test1.in = 3 0 1 4 1 0 1 4 1 0

test2.in = 5 0 7 8 10 1 7 0 8 8 5 8 8 0 10 10 10 8 10 0 7 1 5 10 7 0

test3.in = 5 0 1 9 9 9 1 0 9 9 9 9 9 0 1 1 9 9 1 0 1 9 9 1 1 0

test4.in = 20 0 5 4 7 8 5 7 8 4 6 3 5 1 1 9 8 4 6 1 9 8 0 3 5 2 7 3 6 4 7 5 2 1 3 8 4 6 8 3 1 4 9 0 4 7 8 2 9 8 3 2 7 1 9 7 3 8 5 1 4 9 8 7 0 2 7 5 1 2 9 3 4 1 2 9 3 5 7 7 2 8 9 4 5 0 8 6 1 7 2 3 4 8 7 3 4 7 5 4 2 7 6 1 8 3 0 4 7 3 2 4 9 1 8 6 3 4 5 7 8 9 2 6 3 4 5 0 4 8 9 3 6 2 5 7 9 8 8 5 4 7 8 5 7 8 4 6 0 5 1 1 9 8 4 6 1 9 8 6 3 5 2 7 3 6 4 7 5 0 1 3 8 4 6 8 3 1 4 9 7 4 7 8 2 9 8 3 2 7 0 9 7 3 8 5 1 4 9 8 7 6 2 7 5 1 2 9 3 4 1 0 9 3 5 7 7 2 8 9 4 5 9 8 6 1 7 2 3 4 8 7 0 4 7 5 4 2 7 6 1 8 3 7 4 7 3 2 4 9 1 8 6 0 4 5 7 8 9 2 6 3 4 5 1 4 8 9 3 6 2 5 7 9 0 8 5 4 7 8 5 7 8 4 6 3 5 1 1 9 8 4 6 1 9 0 6 3 5 2 7 3 6 4 7 5 2 1 3 8 4 6 8 3 1 4 0 7 4 7 8 2 9 8 3 2 7 1 9 7 3 8 5 1 4 9 8 0 6 2 7 5 1 2 9 3 4 1 2 9 3 5 7 7 2 8 9 4 0 9 8 6 1 7 2 3 4 8 7 3 4 7 5 4 2 7 6 1 8 0 7 4 7 3 2 4 9 1 8 6 3 4 5 7 8 9 2 6 3 4 0
*/
