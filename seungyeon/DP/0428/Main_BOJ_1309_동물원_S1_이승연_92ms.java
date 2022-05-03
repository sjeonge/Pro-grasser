import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1309_동물원_S1_이승연_92ms {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 우리의 크기 (1<=N<=100000)
		
		int[][] dp = new int[N][3];
		
		dp[0][0] = 1;
		dp[0][1] = 1;
		dp[0][2] = 1;
		
		for(int i=1; i<N; i++) {
			int j = i-1;
			dp[i][0] = (dp[j][0]+dp[j][1]+dp[j][2])%9901;
			dp[i][1] = (dp[j][0]+dp[j][2])%9901;
			dp[i][2] = (dp[j][0]+dp[j][1])%9901;
		}
		
		int n = N-1;
		System.out.println((dp[n][0]+dp[n][1]+dp[n][2])%9901);
	}
}
