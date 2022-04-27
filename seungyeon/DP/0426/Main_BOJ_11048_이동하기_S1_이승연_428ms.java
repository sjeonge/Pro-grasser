import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11048_이동하기_S1_이승연_428ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 미로 가로 크기 (1<=N<=1000)
		int M = Integer.parseInt(st.nextToken()); // 미로 세로 크기 (1<=M<=1000)
		
		int[][] grid = new int[N+1][M+1]; 
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				grid[i][j] += Math.max(Math.max(grid[i][j-1], grid[i-1][j]), grid[i-1][j-1]);
			}
		}
		
		System.out.println(grid[N][M]);
	}
}
