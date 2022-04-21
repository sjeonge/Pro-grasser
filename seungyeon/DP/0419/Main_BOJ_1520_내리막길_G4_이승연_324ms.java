import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1520_내리막길_G4_이승연_324ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int M = Integer.parseInt(st.nextToken()); // 지도 세로 크기(1<=M<=500)
		int N = Integer.parseInt(st.nextToken()); // 지도 가로 크기(1<=N<=500)
		
		int[][] map = new int[M][N];
		
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[M][N];
		
		for(int i=0; i<M; i++) Arrays.fill(dp[i], -1);
		
		System.out.println(dfs(0, 0, dp, map, dr, dc, M-1, N-1));
	
	}
	
	public static int dfs(int cur_r, int cur_c, int[][] dp, int[][] map, int[] dr, int[] dc, int m, int n) {
		if(cur_r == m && cur_c == n) return 1; // 끝
		
		if(dp[cur_r][cur_c] != -1) return dp[cur_r][cur_c]; // 이전에 계산해놓은게 있으면 다시 할 필요 X (memoization)
		
		dp[cur_r][cur_c] = 0; 

		for(int d=0; d<4; d++) {
			int nr = cur_r + dr[d];
			int nc = cur_c + dc[d];
			
			if(nr>=0 && nr<=m && nc>=0 && nc<=n) {
				if(map[cur_r][cur_c] > map[nr][nc]) {
					dp[cur_r][cur_c] += dfs(nr, nc, dp, map, dr, dc, m, n);
				}
			}
		}
		
		return dp[cur_r][cur_c];
	}
}
