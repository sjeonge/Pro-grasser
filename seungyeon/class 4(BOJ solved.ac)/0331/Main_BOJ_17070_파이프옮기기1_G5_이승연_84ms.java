import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BOJ_17070_파이프옮기기1_G5_이승연_84ms {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 집의 크기 (3<=N<=16)
		
		char[][] house = new char[N][N];

		for(int i=0; i<N; i++) {
			house[i] = br.readLine().replace(" ", "").toCharArray();
		}

		int[][][] dp = new int[N][N][3];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				Arrays.fill(dp[i][j], -1);							
			}
		}
		
		int[][] dir = {{0, 1}, {1, 0}, {1, 1}}; // 가로, 세로, 대각선
		
		System.out.println(dfs(0, 1, house, dp, N-1, 0, dir));
	}
	
	public static int dfs(int cur_r, int cur_c, char[][] house, int[][][] dp, int n, int d, int[][] dir) {
		if(cur_r == n && cur_c == n) return 1;
		
		if(dp[cur_r][cur_c][d] != -1) return dp[cur_r][cur_c][d];
		
		int nr;
		int nc; 
		
		int cnt = 0;
		
		switch(d) {
		case 0: // 가로
			// 가로 
			nr = cur_r + dir[0][0];
			nc = cur_c + dir[0][1];
			if(nr>=0 && nr<=n && nc>=0 && nc<=n) {
				if(house[nr][nc] == '0') {
					cnt += dfs(nr, nc, house, dp, n, 0, dir);					
				}
			}
			
			break;
		case 1: // 세로 
			// 세로 
			nr = cur_r + dir[1][0];
			nc = cur_c + dir[1][1];
			if(nr>=0 && nr<=n && nc>=0 && nc<=n) {
				if(house[nr][nc] == '0') {
					cnt += dfs(nr, nc, house, dp, n, 1, dir);					
				}
			}
			
			break; 
		case 2: // 대각선 
			// 가로
			nr = cur_r + dir[0][0];
			nc = cur_c + dir[0][1];
			if(nr>=0 && nr<=n && nc>=0 && nc<=n) {
				if(house[nr][nc] == '0') {
					cnt += dfs(nr, nc, house, dp, n, 0, dir);					
				}
			}
			
			// 세로
			nr = cur_r + dir[1][0];
			nc = cur_c + dir[1][1];
			if(nr>=0 && nr<=n && nc>=0 && nc<=n) {
				if(house[nr][nc] == '0') {
					cnt += dfs(nr, nc, house, dp, n, 1, dir);					
				}
			}
			
			break;
		}
		
		// 대각선 
		nr = cur_r + dir[2][0];
		nc = cur_c + dir[2][1];
		if(nr>=0 && nr<=n && nc>=0 && nc<=n) {
			if(house[nr][nc] == '0' && house[nr-1][nc] == '0' && house[nr][nc-1] == '0') {
				cnt += dfs(nr, nc, house, dp, n, 2, dir);					
			}
		}
		
		return dp[cur_r][cur_c][d] = cnt;
	}
}
