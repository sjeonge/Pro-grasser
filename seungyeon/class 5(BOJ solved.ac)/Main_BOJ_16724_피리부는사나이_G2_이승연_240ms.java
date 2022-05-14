import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_16724_피리부는사나이_G2_이승연_240ms {
	private static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 지도의 행의 수 (1<=N<=1000)
		int M = Integer.parseInt(st.nextToken()); // 지도의 열의 수 (1<=M<=1000)
		
		char[][] map = new char[N][M];
		
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		makeSet(N*M);
		
		int result = 0; 
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				int idx = i*M + j;
				int next_idx = findNext(i, j, map, N, M);
				if(next_idx == -1) result++; // 범위를 넘어가는 경우 
				else if(!union(idx, next_idx)) result++; // union이 불가능한 경우 -> cycle이 만들어지는 경우
			}
		}
		
		System.out.println(result);
	}
	
	public static void makeSet(int n) {
		parents = new int[n];
		
		for(int i=0; i<n; i++) {
			parents[i] = i;
		}
	}
	
	public static int findSet(int a) {
		if(a == parents[a]) return a; 
		
		return parents[a] = findSet(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false; 
		
		parents[aRoot] = b;
		return true; 
	}
	
	public static int findNext(int r, int c, char[][] map, int N, int M) {
		int nr = r; 
		int nc = c;
		
		if(map[nr][nc] == 'U') {
			nr -= 1;
		} else if (map[nr][nc] == 'D') {
			nr += 1;
		} else if (map[nr][nc] == 'L') {
			nc -= 1;
		} else if (map[nr][nc] == 'R') {
			nc += 1;
		}
		
		if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
			return -1; 
		} else {
			return nr*M + nc;
		}
	}
}
