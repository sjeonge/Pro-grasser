import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2225_합분해_G5_이승연_104ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 1<=N<=200
		int K = Integer.parseInt(st.nextToken()); // 1<=K<=200
		
		// K개로 N 만들기 
		int[][] dp = new int[K+1][N+1];
		
		for(int i=0; i<=K; i++) {
			dp[i][0] = 1; // i개로 0 만들기 -> 1
		}
		
		for(int i=1; i<=N; i++) {
			dp[1][i] = 1; // 1개로 i 만들기 -> 1
		}
		
		for(int i=2; i<=K; i++) { // i개로 j 만들기  
			for(int j=1; j<=N; j++) {
				int k = 0;
				int l = i-1; 
				while(k<=j) {
					dp[i][j] += dp[l][k++];
					if(dp[i][j] >= 1000000000) dp[i][j] %= 1000000000;
				}
			}
		}
	
		System.out.println(dp[K][N]);
	}
}
