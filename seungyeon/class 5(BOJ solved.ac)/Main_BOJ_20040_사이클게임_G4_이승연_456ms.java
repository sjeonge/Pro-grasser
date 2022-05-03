import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_20040_사이클게임_G4_이승연_456ms {
	private static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken()); // 점 개수 (3<=n<=500000)
		int m = Integer.parseInt(st.nextToken()); // 진행된 차례의 수 (3<=m<=1000000)
		
		int result = 0;
		
		makeSet(n);
		
		for(int i=1; i<=m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(!union(a, b)){
				result = i;
				break;
			}
		}
		
		System.out.println(result);
	}
	
	public static void makeSet(int n) {
		parent = new int[n];
		
		for(int i=0; i<n; i++) {
			parent[i] = i;
		}
	}
	
	public static int findSet(int a) {
		if(a == parent[a])  return a;
		
		return parent[a] = findSet(parent[a]);
	}
	
	public static boolean union(int a, int b) {
		int ap = findSet(a);
		int bp = findSet(b);
		
		if(ap == bp) return false; 
		
		parent[bp] = ap; 
		
		return true; 
	}
}
