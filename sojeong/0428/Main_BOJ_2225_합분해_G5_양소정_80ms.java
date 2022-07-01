package dp0428;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2225_합분해_G5_양소정_80ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int [][]dp = new int[K+1][N+1];
		for (int i = 1; i <= K; i++) {
			dp[i][0] =1;
		}
		
		for (int i = 1; i <= K; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = (dp[i][j-1] + dp[i-1][j])% 1000000000;
			}
		}
		
		System.out.println(dp[K][N]);
		
	}

}
